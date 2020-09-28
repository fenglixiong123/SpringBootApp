package com.flx.springboot.scaffold.mybatis.plus.multi.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Fenglixiong
 * @Date: 2020/9/28 16:39
 * @Description:
 * 切换数据源使用方法
 * DynamicDataSource.putDataSource('db1');
 */
@Slf4j
@SpringBootApplication
public class MultiDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDatasourceApplication.class,args);
        log.info("MultiDatasourceApplication run ...");
    }

}
