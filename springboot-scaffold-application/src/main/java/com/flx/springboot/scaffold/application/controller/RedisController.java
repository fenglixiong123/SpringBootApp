package com.flx.springboot.scaffold.application.controller;

import com.flx.springboot.scaffold.application.entity.User;
import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.redis.constant.RedisConstant;
import com.flx.springboot.scaffold.redis.utils.RedisUtils;
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
@Api(value = "Redis管理层")
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @GetMapping("/get")
    public ResultResponse getKey(String key){
        return ResultResponse.success(RedisUtils.get(key));
    }

    @GetMapping("/set")
    public ResultResponse setKey(String key,String value){
        return ResultResponse.success(RedisUtils.set(key,value));
    }

    @GetMapping("/setUser")
    public ResultResponse setUser(){
        User user = new User();
        user.setBirth(new Date());
        user.setId(12);
        user.setName("jack");
        user.setWork("Student");
        RedisUtils.set("jack",user);
        RedisUtils.hSet("myUser","user",user);
        Object o = RedisUtils.get("jack");
        log.info(JsonUtils.toJsonMsg(o));
        User user1 = (User)o;
        return ResultResponse.success();
    }

    @GetMapping("/push")
    public ResultResponse push(String message){
        redisTemplate.convertAndSend(RedisConstant.DEFAULT_CHANNEL,message);
        return ResultResponse.success("ok");
    }

}
