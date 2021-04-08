package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.service;

import com.alibaba.fastjson.JSONObject;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.entity.WsMessage;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.enums.BizTypeEnum;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.utils.NettyAttrUtil;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.utils.SessionHolder;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;

import static com.flx.springboot.scaffold.simple.websocket.netty.chatroom.utils.SessionHolder.channelGroup;

/**
 * @Author Fenglixiong
 * @Create 2021/4/9 4:02
 * @Description
 **/
public class WebChatServerService {

    /**
     * 广播 ping 信息
     */
    public void sendPing() {
        WsMessage webSocketMessage = new WsMessage();
        webSocketMessage.setBizType(BizTypeEnum.PING.name());
        String message = JSONObject.toJSONString(webSocketMessage);
        TextWebSocketFrame tws = new TextWebSocketFrame(message);
        channelGroup.writeAndFlush(tws);
    }

    /**
     * 从缓存中移除Channel，并且关闭Channel
     */
    public void scanNotActiveChannel() {
        Map<String, Channel> channelMap = SessionHolder.channelMap;
        // 如果这个直播下已经没有连接中的用户会话了，删除频道
        if (channelMap.size() == 0) {
            return;
        }
        for (Channel channel : channelMap.values()) {
            long lastHeartBeatTime = NettyAttrUtil.getLastHeartBeatTime(channel);
            long intervalMillis = (System.currentTimeMillis() - lastHeartBeatTime);
            if (!channel.isOpen()
                    || !channel.isActive()
                    || intervalMillis > 90000L) {
                channelMap.remove(channel);
                SessionHolder.channelGroup.remove(channel);
                if (channel.isOpen() || channel.isActive()) {
                    channel.close();
                }
            }
        }
    }

}
