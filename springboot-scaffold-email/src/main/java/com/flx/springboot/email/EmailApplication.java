package com.flx.springboot.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author: Fenglixiong
 * @Date: 2019/12/3 18:19
 * @Description:
 */
@Slf4j
@EnableAsync
@SpringBootApplication
public class EmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class,args);
        log.info("EmailApplication start...");
    }

}
