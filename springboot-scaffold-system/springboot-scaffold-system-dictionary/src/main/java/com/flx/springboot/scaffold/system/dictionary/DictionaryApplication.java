package com.flx.springboot.scaffold.system.dictionary;

import com.flx.springboot.scaffold.mybatis.plus.autoconfig.EnableMyBatisPlus;
import com.flx.springboot.scaffold.system.i18n.common.autoconfig.EnableI18nHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Fenglixiong
 * @Date: 2020/8/5 18:04
 * @Description:
 */
@Slf4j
@EnableI18nHandler
@EnableMyBatisPlus
@SpringBootApplication
public class DictionaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DictionaryApplication.class,args);
        log.info("DictionaryApplication run ...");
    }

}
