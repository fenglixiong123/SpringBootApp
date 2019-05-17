package com.flx.springboot.rbac;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Fenglixiong
 * @Create 2019.02.14 17:01
 * @Description
 **/
@Slf4j
@SpringBootApplication
public class RbacApplication {

    public static void main(String[] args) {
        SpringApplication.run(RbacApplication.class,args);
        log.info(">>>>>>>>>>RbacApplication init<<<<<<<<<<");
    }

}
