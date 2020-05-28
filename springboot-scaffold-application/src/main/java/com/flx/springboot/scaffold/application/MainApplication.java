package com.flx.springboot.scaffold.application;

import com.flx.springboot.email.annotation.EnableEmail;
import com.flx.springboot.email.enums.EmailMode;
import com.flx.springboot.scaffold.exception.annotation.EnableExceptionHandler;
import com.flx.springboot.scaffold.redis.annotation.EnableRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/22 17:19
 * @Description: 讲解springboot的运行原理
 */
@Slf4j
@EnableRedis
@SpringBootApplication
@EnableExceptionHandler
@EnableEmail(emailMode = EmailMode.SIMPLE)
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
        log.info("MainApplication run ...");
    }

}
