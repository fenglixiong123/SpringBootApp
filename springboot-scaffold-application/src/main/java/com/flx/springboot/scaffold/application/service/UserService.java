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
        log.info("StudentService init...");
    }

    public void sayHello(){
        log.info("UserService Hello ... ");
    }

}
