package com.flx.springboot.scaffold.application.service;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/22 18:22
 * @Description:
 */
@Slf4j
public class UserService {

    public UserService(){
        log.info("UserService init...");
    }

    public void sayHello(){
        log.info("UserService Hello ... ");
    }

    public void start(){
        log.info("userService 创建...");
    }

    public void end(){
        log.info("userService 销毁...");
    }

}
