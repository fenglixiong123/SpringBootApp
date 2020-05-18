package com.flx.springboot.scaffold.schedule.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/18 19:15
 * @Description:
 */
@Slf4j
@Component
@EnableScheduling
public class SimpleSchedule {

    @Scheduled(cron = "*/2 * * * * *")
    public void runTask(){

        log.info("Spring Simple task run ... ");

    }

}
