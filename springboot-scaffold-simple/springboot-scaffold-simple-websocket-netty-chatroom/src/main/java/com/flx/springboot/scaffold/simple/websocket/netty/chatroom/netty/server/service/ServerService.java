package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.service;

import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.entity.WebMessage;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.enums.ChatTypeEnum;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.enums.MsgTypeEnum;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.message.MessagePush;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.session.SessionHolder;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.utils.NettyAttrUtil;
import io.netty.channel.Channel;

import java.util.Map;

/**
 * @Author Fenglixiong
 * @Create 2021/4/9 4:02
 * @Description
 **/
public class ServerService {

    /**
     * 广播 ping 信息
     */
    public void sendPing() {
        WebMessage webMessage = new WebMessage();
        webMessage.setChatType(ChatTypeEnum.ping.name());
        webMessage.setMsgType(MsgTypeEnum.text.name());
        MessagePush.pushAllMsg(webMessage);
    }

    /**
     * 从缓存中移除Channel，并且关闭Channel
     */
    public void scanNotActiveChannel() {
        Map<Long, Channel> channelMap = SessionHolder.getUserMap();
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
