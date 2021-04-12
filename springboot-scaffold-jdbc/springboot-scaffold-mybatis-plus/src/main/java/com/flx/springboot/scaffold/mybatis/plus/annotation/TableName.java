package com.flx.springboot.scaffold.mybatis.plus.annotation;

import java.lang.annotation.*;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/12 15:21
 * @Description: 用来标识数据库表名
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface TableName {

    String value() default "";

}
