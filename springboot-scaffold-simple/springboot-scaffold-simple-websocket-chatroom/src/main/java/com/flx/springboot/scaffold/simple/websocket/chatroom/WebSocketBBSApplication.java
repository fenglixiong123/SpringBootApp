package com.flx.springboot.scaffold.simple.websocket.chatroom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Author Fenglixiong
 * @Create 2018.11.20 10:07
 * @Description
 **/
@Slf4j
@ServletComponentScan
@SpringBootApplication
public class WebSocketBBSApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketBBSApplication.class,args);
        log.info("===========>WebSocketBBSApplication Run<============");
    }

}
