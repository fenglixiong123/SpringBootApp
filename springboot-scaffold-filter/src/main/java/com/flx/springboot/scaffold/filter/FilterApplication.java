package com.flx.springboot.scaffold.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/20 15:07
 * @Description:
 */
@Slf4j
@ServletComponentScan
@SpringBootApplication
public class FilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilterApplication.class,args);
        log.info("FilterApplication run...");
    }

}
