package com.flx.springboot.scaffold.mybatis.plus.multi.datasource.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fanzhen
 * @date2018-10-30-15:10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface TargetDataSource {
    String value();//此处接收的是数据源的名称
}
