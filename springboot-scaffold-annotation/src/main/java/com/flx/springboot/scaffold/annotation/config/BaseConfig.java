package com.flx.springboot.scaffold.annotation.config;

import com.flx.springboot.scaffold.annotation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/22 18:06
 * @Description:
 * 注册bean的两种方式
 * @Configuration+@Bean
 * @Configuration+@Component
 * 需要添加添加自动扫描注解@ComponentScan默认springboot会帮我们扫描主目录下的
 */
@Slf4j
@Configuration
//@ImportResource("classpath:spring-web.xml") //导入xml配置文件
@ComponentScan(basePackages = "com.flx.springboot.scaffold.annotation.service")
public class BaseConfig {

    public BaseConfig(){
        log.info(">>>>>>>>BaseConfig init ...");
    }

    /**
     * 设置bean作用域为原型模式，默认为单例模式
     * @return
     */
    @Scope("prototype")
    @Bean(name = "userService",initMethod = "start",destroyMethod = "end")
    public UserService userServiceBean(){
        log.info("BaseConfig注入Bean UserService ！！！");
        return new UserService();
    }

}
