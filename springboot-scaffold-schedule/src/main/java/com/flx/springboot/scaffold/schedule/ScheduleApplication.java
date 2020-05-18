package com.flx.springboot.scaffold.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/18 14:37
 * @Description:
 */
@Slf4j
@SpringBootApplication
public class ScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class,args);
        log.info("ScheduleApplication start>>>>>>>>");
    }

}
