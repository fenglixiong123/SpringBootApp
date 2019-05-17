package com.flx.springboot.scaffold.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @Author Fenglixiong
 * @Create 2019.01.11 18:25
 * @Description
 **/
@Slf4j
@EnableJms //ActiveMq
@SpringBootApplication
public class JmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmsApplication.class,args);
        log.info("=========>JmsApplication Start<=======");
    }

}
