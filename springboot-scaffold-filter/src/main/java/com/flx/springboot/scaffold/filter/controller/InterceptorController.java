package com.flx.springboot.scaffold.filter.controller;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/21 19:09
 * @Description:
 */
@Slf4j
@Api(value = "拦截器管理层")
@RestController
@RequestMapping("/interceptor")
public class InterceptorController {

    @GetMapping("/sayHello")
    public ResultResponse sayHello(){
        return ResultResponse.success("Hello Interceptor");
    }

}
