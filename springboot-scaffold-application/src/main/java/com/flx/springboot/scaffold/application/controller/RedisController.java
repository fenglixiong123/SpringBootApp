package com.flx.springboot.scaffold.application.controller;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/26 19:25
 * @Description:
 */
@Api(value = "Redis管理层")
@RestController
@RequestMapping("/redis")
public class RedisController {

    @GetMapping("/get")
    public ResultResponse getKey(String key){
        return ResultResponse.success(RedisUtils.get(key));
    }

    @GetMapping("/set")
    public ResultResponse setKey(String key,String value){
        return ResultResponse.success(RedisUtils.set(key,value));
    }

}
