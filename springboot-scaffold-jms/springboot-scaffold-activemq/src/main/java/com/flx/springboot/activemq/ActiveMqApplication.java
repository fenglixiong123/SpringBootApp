package com.flx.springboot.activemq;

import com.flx.springboot.scaffold.web.core.annotation.EnableExceptionHandle;
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
@EnableJms
@EnableExceptionHandle
@SpringBootApplication
public class ActiveMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActiveMqApplication.class,args);
        log.info("=========>ActiveMqApplication Start<=======");
    }

}
