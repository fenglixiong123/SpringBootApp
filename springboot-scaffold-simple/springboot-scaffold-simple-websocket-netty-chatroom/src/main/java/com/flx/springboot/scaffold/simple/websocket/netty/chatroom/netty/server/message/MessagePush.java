package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.message;

import com.alibaba.fastjson.JSONObject;
import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.entity.WebMessage;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.session.SessionHolder;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/9 11:13
 * @Description: 发送消息的工具类
 */
public class MessagePush {

    public static void pushPersonMsg(Long userId,JSONObject message){
        pushPersonMsg(userId,message.toJSONString());
    }

    public static void pushPersonMsg(Long userId,WebMessage message){
        pushPersonMsg(userId,JsonUtils.toJsonMsg(message));
    }

    public static void pushGroupMsg(Long groupId,JSONObject message){
        pushGroupMsg(groupId,message.toJSONString());
    }

    public static void pushGroupMsg(Long groupId,WebMessage message){
        pushGroupMsg(groupId,JsonUtils.toJsonMsg(message));
    }

    public static void pushAllMsg(JSONObject message){
        pushAllMsg(message.toJSONString());
    }

    public static void pushAllMsg(WebMessage message){
        pushAllMsg(JsonUtils.toJsonMsg(message));
    }


    /**
     * 发送私聊消息
     * @param userId 用户id
     * @param message 消息主体
     */
    public static void pushPersonMsg(Long userId,String message){
        Map<Long, Channel> userMap = SessionHolder.getUserMap();
        Channel channel = userMap.get(userId);
        if(channel!=null&&channel.isOpen()){
            channel.writeAndFlush(new TextWebSocketFrame(message));
        }
    }

    /**
     * 发送群聊消息
     * @param groupId 群id
     * @param message 消息主体
     */
    public static void pushGroupMsg(Long groupId,String message){
        Map<Long, Map<Long, Channel>> groupMap = SessionHolder.getGroupMap();
        Map<Long, Channel> channels = groupMap.get(groupId);
        for (Map.Entry<Long,Channel> entry:channels.entrySet()){
            Channel channel = entry.getValue();
            if(channel!=null && channel.isOpen()){
                channel.writeAndFlush(new TextWebSocketFrame(message));
            }
        }
    }

    /**
     * 向所有用户推送webSocket消息
     * @param message 消息主体
     */
    public static void pushAllMsg(String message){
        ChannelGroup channelGroup = SessionHolder.getChannelGroup();
        channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }

}
