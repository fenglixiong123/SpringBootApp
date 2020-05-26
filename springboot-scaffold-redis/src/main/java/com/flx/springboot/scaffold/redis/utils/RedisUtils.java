package com.flx.springboot.scaffold.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/26 18:18
 * @Description:
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间
     * @return
     */
    public boolean expire(String key,long time)throws Exception{
        if(time>0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        }else {
            throw new Exception();
        }
    }

    /**
     * 获取key失效时间
     * @param key
     * @return 返回0代表永久有效
     */
    public long getExpire(String key){
        Long time = redisTemplate.getExpire(key,TimeUnit.SECONDS);
        return time == null ? -1 : time;
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key) != null;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key
     */
    public boolean delete(String key){
        return redisTemplate.delete(key)!=null;
    }

    /**
     * 删除多个缓存key
     * @param key
     * @return
     */
    public int delete(List<String> key){
        Long result = redisTemplate.delete(key);
        return result == null ? 0 : result.intValue();
    }



}
