package com.flx.springboot.scaffold.schedule.quartz.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/18 15:52
 * @Description:
 */
@Slf4j
public class SayHelloTask extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("----孤舟蓑笠翁，独钓寒江雪----"+new Date());
    }
}
