package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.service;

import com.alibaba.fastjson.JSONObject;
import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.constant.WebConstant;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.entity.WebMessage;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.enums.ChatTypeEnum;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.enums.MsgTypeEnum;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.message.MessagePush;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.utils.DateUtils;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.utils.NettyAttrUtil;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.utils.RequestParamUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.session.SessionHolder.*;
import static com.flx.springboot.scaffold.simple.websocket.netty.chatroom.utils.HttpUtils.sendTextHttpResponse;

/**
 * @Author Fenglixiong
 * @Create 2021/4/9 2:38
 * @Description 具体处理服务器读取数据的请求
 **/
@Slf4j
public class ServerHandlerService {

    //Websocket握手
    private WebSocketServerHandshaker handShaker;
    //WebSocket握手工厂类
    private WebSocketServerHandshakerFactory handShakerFactory = new WebSocketServerHandshakerFactory(WebConstant.websocketUrl, null, false);

    public static ServerHandlerService build(){
        return new ServerHandlerService();
    }

    /**
     * 处理客户端向服务端发起 http 握手请求的业务
     * WebSocket在建立握手时，数据是通过HTTP传输的。但是建立之后，在真正传输时候是不需要HTTP协议的。
     *
     * WebSocket 连接过程：
     * 首先，客户端发起http请求，经过3次握手后，建立起TCP连接；http请求里存放WebSocket支持的版本号等信息，如：Upgrade、Connection、WebSocket-Version等；
     * 然后，服务器收到客户端的握手请求后，同样采用HTTP协议回馈数据；
     * 最后，客户端收到连接成功的消息后，开始借助于TCP传输信道进行全双工通信。
     *
     * @param ctx
     * @param request
     */
    public void handHttpRequest(ChannelHandlerContext ctx, HttpRequest request) {
        Channel channel = ctx.channel();
        //如果请求失败
        if(!request.decoderResult().isSuccess()){
            sendTextHttpResponse(channel,"请求失败！");
            return;
        }
        //如果不是客户端向服务器发起的请求
        if(!request.headers().get("Upgrade").equals("websocket")){
            sendTextHttpResponse(channel,"非法客户端！");
            return;
        }
        //新建一个握手
        handShaker = handShakerFactory.newHandshaker(request);
        //如果握手失败，提示版本不支持
        if(handShaker==null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            return;
        }
        //执行握手
        ChannelFuture future = handShaker.handshake(channel, request);
        if(future.isSuccess()){
            Map<String, String> params = RequestParamUtil.urlSplit(request.uri());
            Long userId = Long.parseLong(params.get("userId"));
            NettyAttrUtil.setUserId(channel, userId);
            NettyAttrUtil.refreshLastHeartBeatTime(channel);
            addSession(userId,channel);
            log.info("握手成功，客户端请求uri：{}", request.uri());
            //推送用户上线消息
            // 推送用户上线消息，更新客户端在线用户列表
//            Set<String> userList = channelMap.keySet();
//            WebMessage msg = new WebMessage();
//            Map<String, Object> ext = new HashMap<>();
//            ext.put("userList", userList);
//            msg.setExt(ext);
//            msg.setBizType(ChatTypeEnum.LIST.name());
//            msg.setMsgType(MsgTypeEnum.TEXT.name());
//            MessagePush.publishMsg(msg);
        }
    }

    /**
     * 处理WebSocket请求
     * @param ctx
     * @param frame
     */
    public void handWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        Channel channel = ctx.channel();
        //判断是否是关闭websocket的指令
        if(frame instanceof CloseWebSocketFrame){
            handShaker.close(channel,((CloseWebSocketFrame) frame).retain());
            removeSession(channel);
            return;
        }
        //判断是否是Ping消息
        if(frame instanceof PingWebSocketFrame){
            channel.writeAndFlush(new PingWebSocketFrame(frame.content().retain()));
            return;
        }
        //判断是否是Pone消息
        if(frame instanceof PongWebSocketFrame){
            channel.writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
        }
        //判断是否是二进制消息，如果是二进制消息，抛出异常
        if (!(frame instanceof TextWebSocketFrame)) {
            System.out.println("目前我们不支持二进制消息");
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            throw new RuntimeException("【" + this.getClass().getName() + "】不支持消息");
        }
        // 获取并解析客户端向服务端发送的 json 消息
        String message = ((TextWebSocketFrame) frame).text();
        log.info("消息：{}", message);
        JSONObject jsonMsg = JSONObject.parseObject(message);
        try {
            String uuid = UUID.randomUUID().toString();
            String time = DateUtils.getCurrentTime();
            jsonMsg.put("id", uuid);
            jsonMsg.put("sendTime", time);

            String chatType = jsonMsg.getString("chatType");
            switch (chatType){
                case "GROUP":
                    Long groupId = jsonMsg.getLong("groupId");
                    MessagePush.pushGroupMsg(groupId,jsonMsg);
                    break;
                case "PRIVATE":
                    Long sendUserId = jsonMsg.getLong("senderUserId");
                    Long receiveUserId = jsonMsg.getLong("receiverUserId");
                    MessagePush.pushPersonMsg(receiveUserId,jsonMsg);
                    break;
                case "PING"://
                    break;
                case "PONG"://pone
                    // 更新心跳时间
                    NettyAttrUtil.refreshLastHeartBeatTime(channel);
                    break;
                case "SYSTEM":
                    //向连接上来的客户端广播消息
                    MessagePush.pushAllMsg(jsonMsg);
            }
        }catch (Exception e){
            log.error("转发消息失败！");
            e.printStackTrace();
        }
    }




}
