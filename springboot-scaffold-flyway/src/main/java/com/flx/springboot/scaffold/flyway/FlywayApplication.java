package com.flx.springboot.scaffold.flyway;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Fenglixiong
 * @Date: 2020/8/4 17:53
 * @Description:
 * 解决flyway启动顺序问题
 * 1.depenson
 *  @DependsOn(value = "flywayInitializer")
 * 2.自己写flyway配置
 * @SpringBootApplication(exclude = {FlywayAutoConfiguration.class})
 */
@Slf4j
public class FlywayApplication {
}
