package com.flx.springboot.scaffold.web.core.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 请求次数限制
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {

    /**
     * 允许访问次数
     * @return
     */
    int count() default Integer.MAX_VALUE;

    /**
     * 单位毫秒，默认一分钟
     * @return
     */
    long time() default 60000;

}
