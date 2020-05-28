package com.flx.springboot.scaffold.annotation;

import com.flx.springboot.scaffold.exception.annotation.EnableExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/25 17:49
 * @Description:
 */
@Slf4j
@EnableScheduling
@SpringBootApplication
@EnableExceptionHandler //开启全局异常处理能力
public class AnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnnotationApplication.class,args);
        log.info("AnnotationApplication init ...");
    }

}
