package com.flx.springboot.scaffold.aop;

import com.flx.springboot.scaffold.exception.annotation.EnableExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/25 14:06
 * @Description:
 */
@Slf4j
@SpringBootApplication
@EnableExceptionHandler
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class,args);
        log.info("AopApplication run ...");
    }

}
