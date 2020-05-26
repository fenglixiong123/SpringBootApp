package com.flx.springboot.scaffold.aop.controller;

import com.flx.springboot.scaffold.aop.service.SimpleService;
import com.flx.springboot.scaffold.common.result.ResultResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/25 14:19
 * @Description:
 */
@Slf4j
@Api(value = "管理页面")
@RestController
@RequestMapping("/simple")
public class SimpleController {

    @Autowired
    private SimpleService simpleService;

    @GetMapping("/hello")
    public ResultResponse sayHello(){
        return ResultResponse.success(simpleService.sayHello());
    }

}
