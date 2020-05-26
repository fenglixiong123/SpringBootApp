package com.flx.springboot.scaffold.filter.controller;

import com.flx.springboot.scaffold.common.result.ResultResponse;
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
@RequestMapping("/filter")
public class FilterController {

    @GetMapping("/single/hello")
    public ResultResponse singleFilter(){
        return ResultResponse.success("Single Hello");
    }

    @GetMapping("/multi/hello")
    public ResultResponse multiFilter(){
        return ResultResponse.success("Multi Hello");
    }
}
