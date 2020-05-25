package com.flx.springboot.scaffold.logger.common.annotation;

import com.flx.springboot.scaffold.common.enums.OperateEnum;
import com.flx.springboot.scaffold.logger.enums.BizTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BizLog {

    /**
     * 业务类型
     * @return
     */
    BizTypeEnum bizType();

    /**
     * 操作类型
     * @return
     */
    OperateEnum operateType();

    /**
     * 业务主键
     * @return
     */
    String bizId();

    /**
     * 主键来源(是否从返回值中取得,默认不从返回值中取，如果是添加需要设置为true)
     * @return
     */
    boolean idFromResp() default false;

    /**
     * 检索字段
     * @return
     */
    String[] searchKey() default {};

    /**
     * 说明字段
     * @return
     */
    String remark() default "";

    /**
     * 是否启用
     * @return
     */
    boolean enable() default true;

}
