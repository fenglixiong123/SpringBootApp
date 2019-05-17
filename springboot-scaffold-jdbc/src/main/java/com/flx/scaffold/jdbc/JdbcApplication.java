package com.flx.scaffold.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Fenglixiong
 * @Create 2019.01.22 15:45
 * @Description
 **/
@Slf4j
@SpringBootApplication
public class JdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class,args);
        log.info(">>>>>>>>>>JdbcApplication init<<<<<<<<<<");
    }

}
