package com.flx.springboot.scaffold.schedule.config;

import com.flx.springboot.scaffold.schedule.task.SayHelloTask;
import com.flx.springboot.scaffold.schedule.task.SayHiTask;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/18 14:42
 * @Description: JobDetail以及SimpleTrigger
 */
@Slf4j
@Configuration
public class QuartzConfiguration {

    /**
     * 定义任务类
     * @return
     */
    @Bean
    public JobDetail sayHelloJobDetail(){
        return JobBuilder.newJob(SayHelloTask.class)
                .withIdentity("hello")
                .withDescription("say hello job")
                .storeDurably()
                .build();
    }

    @Bean
    public JobDetail sayHiJobDetail(){
        return JobBuilder.newJob(SayHiTask.class)
                .withIdentity("hi")
                .withDescription("say hi job")
                .storeDurably()
                .build();
    }

    @Bean
    public SimpleTrigger sayHelloSimpleTrigger(){
        //设置触发器类型
        //设置时间间隔、是否重复、触发频率等
        SimpleScheduleBuilder ssb = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever();
        //Schedule调度Trigger执行对应的Job
        return TriggerBuilder.newTrigger()
                .withSchedule(ssb)
                .forJob(sayHelloJobDetail())
                .withIdentity("sayHelloSimpleTrigger")
                .withDescription("simple类型的触发器")
                .build();
    }

    @Bean
    public CronTrigger sayHiCronTrigger(){
        CronScheduleBuilder csb = CronScheduleBuilder.cronSchedule("0 */1 * * * ?")
                .withMisfireHandlingInstructionDoNothing();
        return TriggerBuilder.newTrigger()
                .withSchedule(csb)
                .forJob(sayHiJobDetail())
                .withIdentity("sayHiCronTrigger")
                .withDescription("corn类型的触发器")
                .startNow()
                .build();
    }

}
