package com.flx.springboot.scaffold.flyway.config;

import com.flx.springboot.scaffold.common.system.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Fenglixiong
 * @Date: 2020/8/4 18:47
 *
 * 其他项目使用flyway sdk
 *
 * 配置文件添加如下配置
 * 设置flyway关闭，采用代码方式加载
 * spring.flyway.enabled=false
 * 设置flyway表名，会自动创建
 * spring.flyway.table=scaffold_flyway_history
 * 设置sql文件存放位置
 * spring.flyway.locations=classpath:db/migration
 * 设置flyway连接数据库url
 * spring.flyway.url
 * 设置flyway用使用数据库户名
 * spring.flyway.user
 * 设置flyway使用数据库密码
 * spring.flyway.password
 *
 * 需要保证flyway最早执行将数据库刷新为最新状态
 * 这里采用ApplicationContextInitializer方式加载flyway进行sql迁移，是防止数据库不是最新的导致执行某些sql失败
 * classpath:/resource/META-INF/spring.factories 添加下面类，springboot启动会首先加载这个
 * org.springframework.context.ApplicationContextInitializer=com.flx.springboot.scaffold.flyway.config.FlywayConfigurationInitializer
 */
@Slf4j
public class FlywayConfigurationInitializer implements ApplicationContextInitializer {

    private String url;
    private String user;
    private String password;
    private String flywayTable;
    private String locations;

    @Transactional(rollbackFor = Exception.class)
    public void initFlyway(){
        log.info("*************************************************");
        log.info("*                                               *");
        log.info("*                 Flyway Success                *");
        log.info("*                                               *");
        log.info("*************************************************");
        FluentConfiguration fluentConfiguration = Flyway.configure()
                .dataSource(url,user,password)
                .baselineDescription("flyway-baseline init")
                .baselineVersion(MigrationVersion.fromVersion("1"))
                .baselineOnMigrate(true)
                .validateOnMigrate(false)
                .outOfOrder(true)
                .encoding("UTF-8")
                .table(flywayTable)
                .locations(locations)
                .cleanDisabled(true);
        Flyway flyway=fluentConfiguration.load();
        flyway.migrate();
    }

    /**
     * 启动flyway会去读取这几个配置项
     * spring.flyway.url
     * spring.flyway.user
     * spring.flyway.password
     * spring.flyway.table
     * spring.flyway.locations
     */
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        url = PropertyUtils.getProperty("spring.flyway.url");
        user = PropertyUtils.getProperty("spring.flyway.user");
        password = PropertyUtils.getProperty("spring.flyway.password");
        flywayTable = PropertyUtils.getProperty("spring.flyway.table");
        locations = PropertyUtils.getProperty("spring.flyway.locations");
        log.info("spring.flyway.url = {}",url);
        log.info("spring.flyway.username = {}",user);
        log.info("spring.flyway.password = {}",password);
        log.info("spring.flyway.table = {}",flywayTable);
        log.info("spring.flyway.locations = {}",locations);
        initFlyway();
    }

}
