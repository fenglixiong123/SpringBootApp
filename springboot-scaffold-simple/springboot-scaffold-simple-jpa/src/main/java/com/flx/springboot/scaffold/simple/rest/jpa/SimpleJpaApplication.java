package com.flx.springboot.scaffold.simple.rest.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Fenglixiong
 * @Create 2018.11.15 17:50
 * @Description
 **/
@Slf4j
@SpringBootApplication
public class SimpleJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleJpaApplication.class,args);
        log.info("==========>SimpleJpaApplication start<=========");
    }

}
