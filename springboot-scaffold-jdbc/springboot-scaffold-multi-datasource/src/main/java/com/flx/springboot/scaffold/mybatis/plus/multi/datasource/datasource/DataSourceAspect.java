package com.flx.springboot.scaffold.mybatis.plus.multi.datasource.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * 如果方法上存在TargetDataSource注解，则根据注解内容进行数据源切换
 *
 * @author fenglixiong
 * @date2018-10-30-15:11
 * 注解方式实现数据源切换
 */
@Component
@Aspect
@Slf4j
public class DataSourceAspect {
    /**
     * 切换放在mapper接口的方法上，所以这里要配置AOP切面的切入点
     */
    @Pointcut("execution( * com.flx.springboot.scaffold.*.dao.*.*(..))")
    public void dataSourcePointCut() {
    }

    @Before("dataSourcePointCut()")
    public void before(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?>[] clazz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = clazz[0].getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource data = clazz[0].getAnnotation(TargetDataSource.class);
                String dataSourceName = data.value();
                DynamicDataSource.putDataSource(dataSourceName);
                log.debug("current thread " + Thread.currentThread().getName() + " add " + dataSourceName + " to ThreadLocal");
            } else {
                log.debug("switch datasource fail,use default");
            }
        } catch (Exception e) {
            log.error("current thread " + Thread.currentThread().getName() + " add data to ThreadLocal error", e);
        }
    }

    /**
     * 执行完切面后，将线程共享中的数据源名称清空
     *
     * @param joinPoint
     */
    @After("dataSourcePointCut()")
    public void after(JoinPoint joinPoint) {
        DynamicDataSource.removeDataSource();
    }
}
