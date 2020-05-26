package com.flx.springboot.scaffold.aop.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/25 14:15
 * @Description:
 */
@Slf4j
@Aspect
@Component
public class BaseAspect {

    @Pointcut("execution(* com.flx.springboot.scaffold.aop.service.*.*(..))")
    public void point(){

    }

    @Before("point()")
    public void before(){
        log.info("method before...");
    }

    @AfterReturning("point()")
    public void afterReturning(){
        log.info("method afterReturning...");
    }

    @AfterThrowing("point()")
    public void afterThrowing(){
        log.info("method afterThrowing...");
    }

}
