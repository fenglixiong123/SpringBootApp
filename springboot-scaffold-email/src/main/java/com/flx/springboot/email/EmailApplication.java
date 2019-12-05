package com.flx.springboot.email;

import com.flx.springboot.scaffold.web.core.annotation.EnableExceptionHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Fenglixiong
 * @Date: 2019/12/3 18:19
 * @Description:
 */
@Slf4j
@EnableExceptionHandle
@SpringBootApplication
public class EmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class,args);
        log.info("EmailApplication start...");
    }

}
