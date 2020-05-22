package com.flx.springboot.scaffold.application.config;

import com.flx.springboot.scaffold.application.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/22 18:06
 * @Description:
 */
@Slf4j
@Configuration
public class BaseConfig {

    public BaseConfig(){
        log.info("BaseConfig初始化！！！");
    }

    @Bean
    public UserService userServiceBean(){
        log.info("BaseConfig注入Bean UserService ！！！");
        return new UserService();
    }

}
