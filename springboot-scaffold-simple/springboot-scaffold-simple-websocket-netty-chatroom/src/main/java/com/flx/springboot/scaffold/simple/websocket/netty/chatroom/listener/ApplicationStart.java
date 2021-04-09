package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: Fenglixiong
 * @Date: 2020/10/29 16:45
 * @Description: 应用启动后执行
 */
@Slf4j
@Component
public class ApplicationStart implements ApplicationRunner {

//    @Autowired
//    private FlywayConfiguration flywayConfiguration;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("+++++++++++++++++++++++++++++++++");
        log.info("++                             ++");
        log.info("++       WebChat成功启动！       ++");
        log.info("++                             ++");
        log.info("+++++++++++++++++++++++++++++++++");

//        flywayConfiguration.init();

    }

}
