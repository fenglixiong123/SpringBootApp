package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/8 19:17
 * @Description:
 */
public class WebChatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg.trim());
    }

}
