package com.flx.springboot.scaffold.logger;

import com.flx.springboot.scaffold.logger.common.redis.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @Author Fenglixiong
 * @Create 2018.11.29 14:06
 * @Description
 **/
@Slf4j
@ComponentScan(excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = RedisConfig.class))
@SpringBootApplication
public class LoggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoggerApplication.class,args);
        log.info("=========>LoggerApplication Start<=======");
    }

}
