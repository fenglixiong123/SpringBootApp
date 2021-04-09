package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/9 10:53
 * @Description:
 */
public class HttpUtils {

    /**
     * 响应text内容
     * @param channel
     * @param message
     */
    public static void sendJsonHttpResponse(Channel channel, String message){
        sendHttpResponse(channel,message,"application/json");
    }

    /**
     * 响应json内容
     * @param channel
     * @param message
     */
    public static void sendTextHttpResponse(Channel channel, String message){
        sendHttpResponse(channel,message,"text/plain");
    }

    /**
     * 向客户端发送http响应
     * @param channel 客户端的通道
     * @param message 向客户端响应的内容
     * @param contentType http响应内容格式
     */
    public static void sendHttpResponse(Channel channel, String message ,String contentType){
        ByteBuf content = Unpooled.copiedBuffer(message, CharsetUtil.UTF_8);
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,contentType);
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
        channel.writeAndFlush(response);
        content.release();
    }

}
