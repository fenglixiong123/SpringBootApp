package com.flx.springboot.scaffold.mybatis.plus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/26 20:07
 * @Description:
 */
@Slf4j
@SpringBootApplication
public class MyBatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusApplication.class,args);
        log.info("MyBatisPlusApplication run ...");
    }

}
