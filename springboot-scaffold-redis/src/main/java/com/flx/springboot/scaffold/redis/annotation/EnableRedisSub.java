package com.flx.springboot.scaffold.redis.annotation;

import com.flx.springboot.scaffold.redis.config.RedisPubsubConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: Fenglixiong
 * @Date: 2019/12/5 19:22
 * @Description:
 * 提供redis消息发布订阅功能
 * 需要继承MessageReceiver类并实现handleMessage方法处理消息
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({RedisPubsubConfiguration.class})
@Documented
public @interface EnableRedisSub {

}
