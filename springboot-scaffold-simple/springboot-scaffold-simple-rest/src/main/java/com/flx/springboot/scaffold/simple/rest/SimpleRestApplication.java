package com.flx.springboot.scaffold.simple.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Fenglixiong
 * @Create 2018.11.07 16:27
 * @Description
 * 如果在这里配置了包扫描则原来的包扫描策略会失效
 **/
@Slf4j
@ComponentScan(basePackages = {"com.flx.springboot.scaffold.*"})
@SpringBootApplication
public class SimpleRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleRestApplication.class,args);
        log.info("=======>SimpleRestApplication start<======");
    }

}
