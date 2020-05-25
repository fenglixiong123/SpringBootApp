package com.flx.springboot.scaffold.annotation.service;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/25 18:11
 * @Description:
 */
@Slf4j
public class ImportBean {

    public ImportBean() {
        log.info("ImportBean init...");
    }

    public void sayHello(){
        log.info("Hello Import...");
    }

}
