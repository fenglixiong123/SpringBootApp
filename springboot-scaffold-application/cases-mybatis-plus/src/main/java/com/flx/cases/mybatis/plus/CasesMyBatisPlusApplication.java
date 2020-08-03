package com.flx.cases.mybatis.plus;

import com.flx.springboot.scaffold.mybatis.plus.autoconfig.EnableMyBatisPlus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/26 20:07
 * @Description:
 */
@Slf4j
@EnableMyBatisPlus
@SpringBootApplication
public class CasesMyBatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasesMyBatisPlusApplication.class,args);
        log.info("CasesMyBatisPlusApplication run ...");
    }

}
