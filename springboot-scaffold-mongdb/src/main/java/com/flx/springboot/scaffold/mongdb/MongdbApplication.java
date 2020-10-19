package com.flx.springboot.scaffold.mongdb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Fenglixiong
 * @Date: 2020/10/14 16:56
 * @Description:
 */
@Slf4j
@SpringBootApplication
public class MongdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongdbApplication.class,args);
        log.info("MongdbApplication start ...");
    }

}
