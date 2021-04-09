package com.flx.springboot.scaffold.simple.websocket.netty.chatroom;

import com.flx.springboot.scaffold.flyway.annotation.EnableFlyway;
import com.flx.springboot.scaffold.mybatis.plus.autoconfig.EnableMyBatisPlus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/26 20:07
 * @Description:
 */
@Slf4j
@EnableFlyway
@EnableMyBatisPlus
@SpringBootApplication
public class NettyWebChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyWebChatApplication.class,args);
        log.info("NettyWebChatApplication run ...");
    }

}
