package com.flx.springboot.scaffold.redis.utils;

import com.flx.springboot.scaffold.exception.element.RedisException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/27 16:45
 * @Description:
 */
@Slf4j
public class RedisBValueUtils extends RedisABaseUtils {

    //==================================string操作================================================

    /**
     * 获取值
     * @param key
     * @return
     */
    public static Object get(String key){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[get] key is null !");
        }
        try {
            return redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            throw new RedisException("[get] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 设置值
     * @param key
     * @param value
     * @return
     */
    public static boolean set(String key,Object value){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[set] key is null !");
        }
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            throw new RedisException("[set] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 设置带失效时间的值
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public static boolean setWithExpire(String key,Object value,long expire){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[setWithExpire] key is null !");
        }
        if(expire<=0){
            throw new RedisException("[setWithExpire] time is illegal !");
        }
        try{
            redisTemplate.opsForValue().set(key,value,expire, TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            throw new RedisException("[setWithExpire] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 递增
     * @param key
     * @param count
     * @return
     */
    public static long incr(String key,long count){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[incr] key is null !");
        }
        if(count<=0){
            throw new RedisException("[incr] count <= 0 !");
        }
        try{
            Long result = redisTemplate.opsForValue().increment(key,count);
            return result == null ? 0 : result;
        }catch (Exception e){
            throw new RedisException("[incr] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 递减
     * @param key
     * @param count
     * @return
     */
    public static long decr(String key,long count){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[decr] key is null !");
        }
        if(count<=0){
            throw new RedisException("[decr] count <= 0 !");
        }
        try{
            Long result = redisTemplate.opsForValue().decrement(key,count);
            return result == null ? 0 : result;
        }catch (Exception e){
            throw new RedisException("[decr] method occur error : "+e.getMessage()+" !");
        }
    }

}
