package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.session;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.utils.NettyAttrUtil;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.Getter;

/**
 * netty会话管理
 * @author 
 *
 */
public class SessionHolder {
	
    /**
     * 广播消息
     * 所有用户的通道
     */
    @Getter
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 私聊实现
     * 用户id--->用户通道
     */
    @Getter
    public static Map<Long, Channel> userMap = new ConcurrentHashMap<>();

    /**
     * 群聊实现
     * 群id--->群聊人员通道
     */
    @Getter
    public static Map<Long, Map<Long,Channel>> groupMap = new ConcurrentHashMap<>();

    /**
     * 添加会话
     * @param userId
     * @param channel
     */
    public static void addSession(Long userId,Channel channel){
        channelGroup.add(channel);
        userMap.put(userId,channel);
    }

    /**
     * 清除会话信息
     * @param channel
     */
    public static void removeSession(Channel channel) {
        Long userId = NettyAttrUtil.getUserId(channel);
        // 清除会话信息
        channelGroup.remove(channel);
        userMap.remove(userId);
    }
}
