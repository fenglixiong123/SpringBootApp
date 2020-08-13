package com.flx.springboot.scaffold.annotation.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: Fenglixiong
 * @Date: 2020/8/13 17:31
 * @Description: Bean处理器，用来处理所有注册的bean，在其初始化前后进行执行某些指定的动作
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyBeanPostProcessor implements BeanPostProcessor {
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("studentService")) {
            log.info("MyBeanPostProcessor postProcessBeforeInitialization...beanName = " + beanName + ",bean = " + bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("studentService")) {
            log.info("MyBeanPostProcessor postProcessAfterInitialization...beanName = " + beanName + ",bean = " + bean);
        }
        return bean;
    }
}
