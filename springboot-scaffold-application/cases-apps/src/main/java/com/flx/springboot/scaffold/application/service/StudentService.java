package com.flx.springboot.scaffold.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/22 18:20
 * @Description:
 */
@Slf4j
@Service
public class StudentService {

    public StudentService(){
        log.info("StudentService init...");
    }

    public void sayHello(){
        log.info("StudentService Hello...");
    }

}
