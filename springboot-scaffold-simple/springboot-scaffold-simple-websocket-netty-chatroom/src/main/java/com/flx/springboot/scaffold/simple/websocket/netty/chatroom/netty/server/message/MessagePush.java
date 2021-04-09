package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.message;

import com.alibaba.fastjson.JSONObject;
import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.entity.WsMessage;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.session.SessionHolder;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/9 11:13
 * @Description: 发送消息的工具类
 */
public class MessagePush {

    public static void publishMsg(JSONObject message){
        publishMsg(message.toJSONString());
    }

    public static void publishMsg(WsMessage message){
        publishMsg(JsonUtils.toJsonMsg(message));
    }

    public static void publishMsg(String message){
        publishMsg(new TextWebSocketFrame(message));
    }

    public static void publishMsg(TextWebSocketFrame frame){
        ChannelGroup channelGroup = SessionHolder.getChannelGroup();
        channelGroup.writeAndFlush(frame);
    }

}
