package com.flx.springboot.scaffold.flyway.config;

import com.flx.springboot.scaffold.common.servlet.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @Author: Fenglixiong
 * @Date: 2020/8/4 18:47
 * @Description:
 *
 * 其他项目使用flyway sdk
 * 1.添加注解@EnableFlyway
 * 2.配置文件添加如下配置
 * 设置flyway关闭，采用代码方式加载
 * spring.flyway.enabled=false
 * 设置flyway表名，会自动创建
 * spring.flyway.table=scaffold_flyway_history
 * 设置sql文件存放位置
 * spring.flyway.locations=classpath:db/migration
 *
 */
@Slf4j
@Configuration
public class FlywayConfiguration {

    private String locations;
    private String table;

    @Autowired
    private DataSource dataSource;

    @Transactional(rollbackFor = Exception.class)
    public void initFlyway(){
        log.info("*************************************************");
        log.info("*                                               *");
        log.info("*                  init flyway                  *");
        log.info("*                                               *");
        log.info("*************************************************");
        FluentConfiguration fluentConfiguration = Flyway.configure()
                .dataSource(dataSource)
                .baselineDescription("flyway-baseline init")
                .baselineVersion(MigrationVersion.fromVersion("1"))
                .baselineOnMigrate(true)
                .validateOnMigrate(false)
                .outOfOrder(true)
                .encoding("UTF-8")
                .table(table)
                .locations(locations)
                .cleanDisabled(true);
        Flyway flyway=fluentConfiguration.load();
        flyway.migrate();
    }

    /**
     * 启动flyway会去读取这几个配置项
     * spring.flyway.table
     * spring.flyway.locations
     */
    @PostConstruct
    public void init(){
        locations = PropertyUtils.getProperty("spring.flyway.locations");
        table = PropertyUtils.getProperty("spring.flyway.table");
        log.info("spring.flyway.locations = {} , spring.flyway.table = {}",locations,table);
        initFlyway();
    }

}
