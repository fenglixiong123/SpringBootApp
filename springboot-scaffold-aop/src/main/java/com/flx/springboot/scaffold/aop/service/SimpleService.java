package com.flx.springboot.scaffold.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/25 14:20
 * @Description:
 */
@Slf4j
@Service
public class SimpleService {

    public String sayHello(){
        return "Hello,Jack";
    }

}
