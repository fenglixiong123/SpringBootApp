package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.handler;

import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.service.ServerHandlerService;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.utils.DateUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import static com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.session.SessionHolder.channelGroup;


/**
 * @Author: Fenglixiong
 * @Date: 2021/4/8 17:12
 * @Description: 用来处理客户端和服务端的会话生命周期事件（握手、建立连接、断开连接、收消息等）
 */
@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * 表示连接建立，一旦连接，第一个被执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将该客户加入聊天信息推送给其他在线的客户端
        //该方法会遍历所有的channel，并发送消息
        channelGroup.writeAndFlush(DateUtils.getCurrentTime()+" [用户]"+channel.remoteAddress()+" 加入聊天~\n");
    }

    /**
     * 当该连接分配到具体的worker线程后，该回调会被调用。
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    /**
     * 表示channel处于活动状态,提示上线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(DateUtils.getCurrentTime()+" [用户]"+ctx.channel().remoteAddress()+" 上线~");
    }

    /**
     * 服务器读取客户端发来的消息，并转发到指定客户
     * @param ctx
     * @param msg 客户端发来的消息，WebSocket消息 or Http消息
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ServerHandlerService service = ServerHandlerService.build();
        if(msg instanceof HttpRequest){
            service.handHttpRequest(ctx,(HttpRequest)msg);
        }else if(msg instanceof WebSocketFrame){
            service.handWebSocketFrame(ctx,(WebSocketFrame)msg);
        }
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    /**
     * 表示channel处于非活动状态，提示下线
     * 当连接断开时，该回调会被调用，说明这时候底层的TCP连接已经被断开了。
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(DateUtils.getCurrentTime()+" [用户]"+ctx.channel().remoteAddress()+" 离线~");
    }

    /**
     * 当连接关闭后，释放绑定的worker线程；
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    /**
     * 表示连接断开
     * 会自动从channelGroup中删除下线的人
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(DateUtils.getCurrentTime()+" [用户]"+channel.remoteAddress()+" 离开聊天\n");
    }
}
