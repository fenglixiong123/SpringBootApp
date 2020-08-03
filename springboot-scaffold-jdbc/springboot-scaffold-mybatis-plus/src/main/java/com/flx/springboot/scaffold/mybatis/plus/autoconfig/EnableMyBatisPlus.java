package com.flx.springboot.scaffold.mybatis.plus.autoconfig;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: Fenglixiong
 * @Date: 2019/12/5 19:22
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({MyBatisPlusAutoConfiguration.class})
@Documented
public @interface EnableMyBatisPlus {

}
