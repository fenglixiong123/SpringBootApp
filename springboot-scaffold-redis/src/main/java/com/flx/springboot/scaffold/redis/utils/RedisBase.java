package com.flx.springboot.scaffold.redis.utils;

import com.flx.springboot.scaffold.common.context.SpringContextUtil;
import com.flx.springboot.scaffold.exception.element.RedisException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/27 16:44
 * @Description:
 */
@Slf4j
public class RedisBase {

    protected static RedisTemplate<String, Object> redisTemplate;

    /**
     * 依赖注入后执行早于
     */
    @PostConstruct
    @SuppressWarnings("unchecked")
    public void setRedisTemplate(){
        redisTemplate = SpringContextUtil.getBean("redisTemplate", RedisTemplate.class);
        log.info("redisTemplate:{}",redisTemplate);
    }

}
