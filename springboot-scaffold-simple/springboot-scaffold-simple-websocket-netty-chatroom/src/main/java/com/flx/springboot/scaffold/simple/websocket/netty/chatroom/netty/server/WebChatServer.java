package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server;

import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.constant.WebConstant;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.initializer.ServerChannelInitializer;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.server.service.ServerService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/8 16:50
 * @Description:
 */
public class WebChatServer {

    public static void main(String[] args) throws InterruptedException {

        new WebChatServer(WebConstant.port).run();

    }

    private final int port;

    public WebChatServer(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(8);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            ChannelFuture future = serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ServerChannelInitializer())
                    .bind(port)
                    .sync();
            //创建一个定长线程池，支持定时及周期性任务执行
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
            ServerService webChatServerService = new ServerService();
            //定时任务:扫描所有的Channel，关闭失效的Channel
            executorService.scheduleAtFixedRate(webChatServerService::scanNotActiveChannel,
                    3, 60, TimeUnit.SECONDS);

            //定时任务:向所有客户端发送Ping消息
            executorService.scheduleAtFixedRate(webChatServerService::sendPing,
                    3, 50, TimeUnit.SECONDS);
            future.addListener((ChannelFutureListener) future1 -> {
                if (future1.isSuccess()) {
                    System.out.println("Server start ok ~");
                }
            }).sync();
            future.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}
