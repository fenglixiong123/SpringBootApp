package com.flx.springboot.email.config;

import com.flx.springboot.email.service.ComplexEmailService;
import com.flx.springboot.email.service.impl.ComplexEmailServiceImpl;
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
public class ComplexEmailConfiguration {

    @Bean(name = "com.flx.springboot.email.service.ComplexEmailService")
    public ComplexEmailService complexEmailService(){
        return new ComplexEmailServiceImpl();
    }

}
