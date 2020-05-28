package com.flx.springboot.scaffold.aop.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
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

    /**
     *     String toString();         //连接点所在位置的相关信息
     *     String toShortString();     //连接点所在位置的简短相关信息
     *     String toLongString();     //连接点所在位置的全部相关信息
     *     Object getThis();         //返回AOP代理对象
     *     Object getTarget();       //返回目标对象
     *     Object[] getArgs();       //返回被通知方法参数列表
     *     Signature getSignature();  //返回当前连接点签名
     *     SourceLocation getSourceLocation();//返回连接点方法所在类文件中的位置
     *     String getKind();        //连接点类型
     *     StaticPart getStaticPart(); //返回连接点静态部分
     * @param joinPoint
     */
    @AfterReturning("point()")
    public void afterReturning(JoinPoint joinPoint){
        log.info("method afterReturning...");
    }

    @AfterThrowing("point()")
    public void afterThrowing(){
        log.info("method afterThrowing...");
    }

}
