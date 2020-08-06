package com.flx.springboot.scaffold.system.i18n.common.autoconfig;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: Fenglixiong
 * @Date: 2019/12/5 19:22
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({I18nAutoConfiguration.class})
@Documented
public @interface EnableI18nHandler {

}
