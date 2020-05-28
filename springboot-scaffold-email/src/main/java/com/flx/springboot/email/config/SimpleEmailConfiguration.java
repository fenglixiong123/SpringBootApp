package com.flx.springboot.email.config;

import com.flx.springboot.email.service.SimpleEmailService;
import com.flx.springboot.email.service.impl.SimpleEmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/28 17:37
 * @Description:
 */
@Slf4j
@Configuration
public class SimpleEmailConfiguration {

    @Bean(name = "com.flx.springboot.email.service.SimpleEmailService")
    public SimpleEmailService simpleEmailService(){
        return new SimpleEmailServiceImpl();
    }

}
