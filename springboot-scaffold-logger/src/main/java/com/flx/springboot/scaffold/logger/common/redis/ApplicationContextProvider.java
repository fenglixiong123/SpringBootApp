package com.flx.springboot.scaffold.logger.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author Fenglixiong
 * @Create 2019.01.03 15:34
 * @Description
 **/
@Slf4j
@Component
public class ApplicationContextProvider implements ApplicationContextAware, InitializingBean {

    /**
     * 上下文实例
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextProvider.applicationContext = applicationContext;
    }

    /**
     * 获取实例
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * 获取Bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static  <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("---------------ApplicationContextProvider------------");
    }
}
