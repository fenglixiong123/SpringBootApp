package com.flx.springboot.scaffold.annotation.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/22 18:20
 * @Description:
 */
@Slf4j
@Service
public class StudentService implements InitializingBean {

    public StudentService(){
        log.info("StudentService construct...");
    }

    public void sayHello(){
        log.info("StudentService Hello...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("StudentService afterPropertiesSet...");
    }

    @PostConstruct
    public void postMethod(){
        log.info("StudentService PostConstruct...");
    }

}
