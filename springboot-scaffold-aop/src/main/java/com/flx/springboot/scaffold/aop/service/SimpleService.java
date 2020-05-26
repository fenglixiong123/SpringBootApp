package com.flx.springboot.scaffold.aop.service;

import com.flx.springboot.scaffold.aop.annotation.BizLog;
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

    @BizLog(bizId = "key001")
    public String sayHello(){
        return "Hello,Jack";
    }

}
