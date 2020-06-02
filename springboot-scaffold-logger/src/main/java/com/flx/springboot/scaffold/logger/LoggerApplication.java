package com.flx.springboot.scaffold.logger;

import com.flx.springboot.scaffold.exception.annotation.EnableExceptionHandler;
import com.flx.springboot.scaffold.redis.annotation.EnableRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Fenglixiong
 * @Create 2018.11.29 14:06
 * @Description
 **/
@Slf4j
@EnableRedis
@SpringBootApplication
@EnableExceptionHandler
public class LoggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoggerApplication.class,args);
        log.info("=========>LoggerApplication Start<=======");
    }

}
