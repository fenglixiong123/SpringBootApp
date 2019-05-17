package com.flx.springboot.scaffold.web.core.annotation;


import com.flx.springboot.scaffold.web.core.enums.BizTypeEnum;
import com.flx.springboot.scaffold.web.core.enums.OperateTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SysLog {

    /**
     * 业务类型
     */
    BizTypeEnum bizType();

    /**
     * 操作类型
     */
    OperateTypeEnum operateType();

    /**
     * 业务主键
     */
    String bizId() default "";

    /**
     * 主键来源
     */
    boolean isFromResp() default false;

    /**
     * 检索字段
     */
    String[] searchKey() default "";

    /**
     * 说明字段
     */
    String remark() default "";

}
