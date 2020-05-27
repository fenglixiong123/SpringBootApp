package com.flx.springboot.scaffold.redis.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/26 18:18
 * @Description:
 *
 * string操作
 * hash操作
 * set操作
 * list操作
 */
@Slf4j
@Component
@DependsOn(value = "com.flx.springboot.scaffold.common.context.SpringContextUtil")
public class RedisUtils extends RedisEListUtils {

    public RedisUtils(){
       log.info("RedisUtils init...");
    }



}
