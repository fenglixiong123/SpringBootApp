package com.flx.springboot.scaffold.filter.controller;

import com.flx.springboot.scaffold.web.core.result.ResultResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/20 18:21
 * @Description:
 */
@Slf4j
@Api(value = "过滤器管理层")
@RestController
@RequestMapping("/")
public class SimpleController {

    @GetMapping("/single/hello")
    public ResultResponse single(){
        return ResultResponse.ok("Single Hello");
    }

    @GetMapping("/multi/hello")
    public ResultResponse multi(){
        return ResultResponse.ok("Multi Hello");
    }
}
