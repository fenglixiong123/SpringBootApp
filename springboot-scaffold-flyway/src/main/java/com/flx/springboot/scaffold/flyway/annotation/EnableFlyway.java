package com.flx.springboot.scaffold.flyway.annotation;

import com.flx.springboot.scaffold.flyway.config.FlywayConfigurationInitializer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: Fenglixiong
 * @Date: 2019/12/5 19:22
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({FlywayConfigurationInitializer.class})
@Documented
public @interface EnableFlyway {

}
