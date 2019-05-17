package com.flx.springboot.scaffold.web.core.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

/**
 * @Author Fenglixiong
 * @Create 2018.11.07 17:18
 * @Description
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@org.springframework.web.bind.annotation.RestController
public @interface RestController {
    @AliasFor(
            annotation = org.springframework.web.bind.annotation.RestController.class
    )
    String value() default "";
}
