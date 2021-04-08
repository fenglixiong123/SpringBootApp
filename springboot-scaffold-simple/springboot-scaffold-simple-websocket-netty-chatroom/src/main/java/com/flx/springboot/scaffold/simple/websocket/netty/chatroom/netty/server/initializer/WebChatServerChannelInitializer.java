package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.initializer;

import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.handler.WebChatServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Author Fenglixiong
 * @Create 2021/4/9 1:51
 * @Description
 **/
public class WebChatServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //请求解码器
        socketChannel.pipeline().addLast("http-codec", new HttpServerCodec());
        //将多个消息转换成单一的消息对象
        socketChannel.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
        //支持异步发送大的码流，一般用于发送文件流
        socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        //加入业务处理
        pipeline.addLast(new WebChatServerHandler());
    }
}
