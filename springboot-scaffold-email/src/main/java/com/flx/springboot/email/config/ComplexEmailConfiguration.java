package com.flx.springboot.email.config;

import com.flx.springboot.email.service.ComplexEmailService;
import com.flx.springboot.email.service.impl.ComplexEmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/28 17:37
 * @Description:
 */
@Slf4j
@Configuration
public class ComplexEmailConfiguration {

    @Bean
    @ConditionalOnMissingBean //不然会被注入两次
    public JavaMailSender javaMailSender(){
        return new JavaMailSenderImpl();
    }

    @Bean(name = "com.flx.springboot.email.service.ComplexEmailService")
    public ComplexEmailService complexEmailService(){
        log.info("=============注册ComplexEmailService成功========");
        return new ComplexEmailServiceImpl();
    }

}
