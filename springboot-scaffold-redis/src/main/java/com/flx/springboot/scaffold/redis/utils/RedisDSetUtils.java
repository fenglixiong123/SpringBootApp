package com.flx.springboot.scaffold.redis.utils;

import com.flx.springboot.scaffold.exception.element.RedisException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/27 16:50
 * @Description:
 */
@Slf4j
public class RedisDSetUtils extends RedisCHashUtils {

    //==================================set操作================================================

    /**
     * 根据key获取set的所有值
     * @param key
     * @return
     */
    public static Set<Object> sGet(String key){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[sGet] key is null !");
        }
        try {
            return redisTemplate.opsForSet().members(key);
        }catch (Exception e){
            throw new RedisException("[sGet] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 将数据放入set缓存
     * @param key
     * @param values
     * @return
     */
    public static boolean sSet(String key,Object ... values){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[sSet] key is null !");
        }
        try {
            Long result = redisTemplate.opsForSet().add(key,values);
            return result != null;
        }catch (Exception e){
            throw new RedisException("[sSet] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 带失效时间的set设置
     * @param key
     * @param values
     * @param expire
     * @return
     */
    public static boolean sSetWithExpire(String key, long expire, Object ... values){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[sSetWithExpire] key is null !");
        }
        if(expire<=0){
            throw new RedisException("[sSetWithExpire] expire time is illegal !");
        }
        try {
            Long result = redisTemplate.opsForSet().add(key,values);
            expire(key,expire);
            return result != null;
        }catch (Exception e){
            throw new RedisException("[sSetWithExpire] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 是否有某个value
     * @param key
     * @param value
     * @return
     */
    public static boolean sHasKey(String key,Object value){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[sHasKey] key is null !");
        }
        try {
            Boolean result = redisTemplate.opsForSet().isMember(key,value);
            return result ==null ? false : result;
        }catch (Exception e){
            throw new RedisException("[sHasKey] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 获取set的长度
     * @param key
     * @return
     */
    public static long sSize(String key){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[sSize] key is null !");
        }
        try {
            Long result = redisTemplate.opsForSet().size(key);
            return result ==null ? 0 : result;
        }catch (Exception e){
            throw new RedisException("[sSize] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 移除value值
     * @param key
     * @param values
     * @return
     */
    public static boolean sDel(String key,Object ... values){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[sDel] key is null !");
        }
        try {
            Long result = redisTemplate.opsForSet().remove(key,values);
            return result != null;
        }catch (Exception e){
            throw new RedisException("[sDel] method occur error : "+e.getMessage()+" !");
        }
    }

}
