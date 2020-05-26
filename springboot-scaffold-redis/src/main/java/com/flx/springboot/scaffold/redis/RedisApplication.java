package com.flx.springboot.scaffold.redis;

import com.flx.springboot.scaffold.exception.annotation.EnableExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/26 16:21
 * @Description:
 */
@Slf4j
@SpringBootApplication
@EnableExceptionHandler
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class,args);
        log.info("RedisApplication run ...");
    }

}
