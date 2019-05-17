package com.flx.springboot.scaffold.simple.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Fenglixiong
 * @Create 2018.11.19 16:21
 * @Description
 **/
@Slf4j
@SpringBootApplication
public class WebSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class,args);
        log.info("=======>WebSocketApplication Run<======");
    }

}
