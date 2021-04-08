package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * netty会话管理
 * @author 
 *
 */
public class SessionHolder {
	
    /**
     * 存储每个客户端接入进来时的 channel 对象
     * 主要用于使用 writeAndFlush 方法广播信息
     */
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 用于客户端和服务端握手时存储用户id和netty Channel对应关系
     */
    public static Map<String, Channel> channelMap = new ConcurrentHashMap<>();

    /**
     * 清除会话信息
     * @param channel
     */
    public static void clearSession(Channel channel) {
        String userId = NettyAttrUtil.getUserId(channel);
        // 清除会话信息
        channelGroup.remove(channel);
        channelMap.remove(userId);
    }

}
