package com.flx.springboot.scaffold.redis.controller;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.exception.element.RedisException;
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
    public ResultResponse getKey(){
//        throw new RedisException("redis出错拉");
        return ResultResponse.success("Hello");
    }

}
