package com.flx.springboot.email.annotation;

import com.flx.springboot.email.common.enums.EmailMode;
import com.flx.springboot.email.common.selector.EmailConfigurationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/28 19:26
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({EmailConfigurationSelector.class})
@Documented
public @interface EnableEmail {

    EmailMode emailMode() default EmailMode.SIMPLE;

}
