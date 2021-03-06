package com.flx.springboot.scaffold.application.controller;

import com.flx.springboot.scaffold.application.entity.User;
import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.redis.annotation.EnableRedis;
import com.flx.springboot.scaffold.redis.constant.RedisConstant;
import com.flx.springboot.scaffold.redis.service.IRedisService;
import com.flx.springboot.scaffold.redis.service.RedisBaseService;
import com.flx.springboot.scaffold.redis.service.RedisStringService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/26 19:25
 * @Description:
 */
@Slf4j
//@EnableRedis
@Api(value = "Redis管理层")
@RestController
@RequestMapping("/redis")
public class RedisController {
/*
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private IRedisService redisService;

    @GetMapping("/get")
    public ResultResponse getKey(String key){
        return ResultResponse.success(redisService.get(key));
    }

    @GetMapping("/set")
    public ResultResponse setKey(String key,String value){
        return ResultResponse.success(redisService.set(key,value));
    }

    @GetMapping("/setUser")
    public ResultResponse setUser(){
        User user = new User();
        user.setBirth(new Date());
        user.setId(12);
        user.setName("jack");
        user.setWork("Student");
        redisService.set("jack",user);
        redisService.hSet("myUser","user",user);
        Object o = redisService.get("jack");
        log.info(JsonUtils.toJsonMsg(o));
        User user1 = (User)o;
        return ResultResponse.success();
    }

    @GetMapping("/push")
    public ResultResponse push(String message){
        redisTemplate.convertAndSend(RedisConstant.DEFAULT_CHANNEL,message);
        return ResultResponse.success("ok");
    }
*/
}
