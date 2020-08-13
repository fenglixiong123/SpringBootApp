package com.flx.springboot.scaffold.annotation.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: Fenglixiong
 * @Date: 2020/8/13 18:53
 * @Description:
 * bean工厂的bean属性处理容器，说通俗一些就是可以管理我们的bean工厂内所有的beanDefinition（未实例化）数据，可以随心所欲的修改属性。
 */
@Slf4j
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        int count = beanFactory.getBeanDefinitionCount();
        log.info("当前beanFactory中有{}个bean",count);
        log.info("当前beanFactory names = {}", Arrays.asList(beanFactory.getBeanDefinitionNames()));
    }

}
