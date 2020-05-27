package com.flx.springboot.scaffold.redis.utils;

import com.flx.springboot.scaffold.common.context.SpringContextUtil;
import com.flx.springboot.scaffold.exception.element.RedisException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
public class RedisUtils {

    public RedisUtils(){
       log.info("RedisUtils init...");
    }

    private static RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void setRedisTemplate(){
        redisTemplate = SpringContextUtil
                .getBean("redisTemplate", RedisTemplate.class);
        log.info("redisTemplate:{}",redisTemplate);
    }

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param expire 时间
     * @return
     */
    public static boolean expire(String key,long expire){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[expire] key is null !");
        }
        if(expire<=0){
            throw new RedisException("[expire] expire time is illegal !");
        }
        try {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            throw new RedisException("[expire] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 获取key失效时间
     * @param key
     * @return 返回0代表永久有效
     */
    public static long getExpire(String key){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[getExpire] key is null !");
        }
        try {
            Long time = redisTemplate.getExpire(key,TimeUnit.SECONDS);
            return time == null ? -1 : time;
        }catch (Exception e){
            throw new RedisException("[getExpire] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public static boolean hasKey(String key){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[hasKey] key is null !");
        }
        try {
            return redisTemplate.hasKey(key) != null;
        }catch (Exception e){
            throw new RedisException("[hasKey] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 删除缓存
     * @param key
     */
    public static boolean delete(String key){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[delete] key is null !");
        }
        try {
            return redisTemplate.delete(key)!=null;
        }catch (Exception e){
            throw new RedisException("[delete] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 删除多个缓存key
     * @param keys
     * @return
     */
    public static int deleteKeys(List<String> keys){
        if(CollectionUtils.isEmpty(keys)){
            throw new RedisException("[deleteKeys] list is null !");
        }
        try {
            Long result = redisTemplate.delete(keys);
            return result == null ? 0 : result.intValue();
        }catch (Exception e){
            throw new RedisException("[deleteKeys] method occur error : "+e.getMessage()+" !");
        }
    }

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
            redisTemplate.opsForValue().set(key,value,expire,TimeUnit.SECONDS);
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

    //==================================hash操作================================================

    /**
     * Hash Get
     * @param key 键
     * @param item 项
     * @return
     */
    public static Object hGet(String key,String item){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[hGet] key is null !");
        }
        if(StringUtils.isBlank(item)){
            throw new RedisException("[hGet] item is null !");
        }
        try {
            return redisTemplate.opsForHash().get(key,item);
        }catch (Exception e){
            throw new RedisException("[hGet] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * Hash Set 设置数据，不存在则创建
     * @param key
     * @param item
     * @return
     */
    public static boolean hSet(String key,String item,Object value){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[hSet] key is null !");
        }
        if(StringUtils.isBlank(key)){
            throw new RedisException("[hSet] item is null !");
        }
        try {
            redisTemplate.opsForHash().put(key,item,value);
            return true;
        }catch (Exception e){
            throw new RedisException("[hSet] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * Hash Set 设置数据，不存在则创建
     * @param key
     * @param item
     * @return
     */
    public static boolean hSetWithExpire(String key,String item,Object value,long expire){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[hSetWithExpire] key is null !");
        }
        if(StringUtils.isBlank(item)){
            throw new RedisException("[hSetWithExpire] item is null !");
        }
        if(expire<=0){
            throw new RedisException("[hSetWithExpire] expire time is illegal !");
        }
        try {
            redisTemplate.opsForHash().put(key,item,value);
            expire(key,expire);
            return true;
        }catch (Exception e){
            throw new RedisException("[hSetWithExpire] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 获取hashKey的所有键值对
     * @param key
     * @return
     */
    public static Map<Object,Object> hmGet(String key){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[hmGet] key is null !");
        }
        try {
            return redisTemplate.opsForHash().entries(key);
        }catch (Exception e){
            throw new RedisException("[hmGet] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * HashSet 设置多个键值对
     * @param key
     * @param map
     * @return
     */
    public static boolean hmSet(String key,Map<String,Object> map){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[hmSet] key is null !");
        }
        try {
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        }catch (Exception e){
            throw new RedisException("[hmSet] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * HashSet 设置多个键值对带过期时间
     * @param key
     * @param map
     * @return
     */
    public static boolean hmSetWithExpire(String key,Map<String,Object> map,long expire){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[hmSetWithExpire] key is null !");
        }
        if(expire<=0){
            throw new RedisException("[hmSetWithExpire] expire time is illegal !");
        }
        try {
            redisTemplate.opsForHash().putAll(key,map);
            expire(key,expire);
            return true;
        }catch (Exception e){
            throw new RedisException("[hmSetWithExpire] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 删除Hash表中的值
     * @param key
     * @param item
     * @return
     */
    public static boolean hDel(String key,Object ... item){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[hDel] key is null !");
        }
        try {
            Long result = redisTemplate.opsForHash().delete(key,item);
            return result != null ;
        }catch (Exception e){
            throw new RedisException("[hDel] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 判断hash表中是否存在该项值
     * @param key
     * @param item
     * @return
     */
    public static boolean hHasKey(String key,String item){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[hHasKey] key is null !");
        }
        if(StringUtils.isBlank(item)){
            throw new RedisException("[hHasKey] item is null !");
        }
        try {
            return redisTemplate.opsForHash().hasKey(key,item);
        }catch (Exception e){
            throw new RedisException("[hHasKey] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * Hash 递增，如果不存在则创建 并返回新增后的值
     * @param key
     * @param item
     * @param by
     * @return
     */
    public static double hIncr(String key,String item,double by){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[hIncr] key is null !");
        }
        if(StringUtils.isBlank(item)){
            throw new RedisException("[hIncr] item is null !");
        }
        if(by<=0){
            throw new RedisException("[hIncr] by <= 0 !");
        }
        try {
            return redisTemplate.opsForHash().increment(key,item,by);
        }catch (Exception e){
            throw new RedisException("[hIncr] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 减少多少
     * @param key
     * @param item
     * @param by
     * @return
     */
    public static double hDecr(String key,String item,double by){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[hDecr] key is null !");
        }
        if(StringUtils.isBlank(item)){
            throw new RedisException("[hDecr] item is null !");
        }
        if(by<=0){
            throw new RedisException("[hDecr] by < 0 !");
        }
        try {
            return redisTemplate.opsForHash().increment(key,item,-by);
        }catch (Exception e){
            throw new RedisException("[hDecr] method occur error : "+e.getMessage()+" !");
        }
    }

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

    //==================================list操作================================================

    /**
     * 通过下标获取list的值
     * @param key
     * @param index
     * @return
     */
    public static Object lGetByIndex(String key,long index){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[lGetByIndex] key is null !");
        }
        if(index<0){
            throw new RedisException("[lGetByIndex] index < 0 !");
        }
        try {
            return redisTemplate.opsForList().index(key,index);
        }catch (Exception e){
            throw new RedisException("[lGetByIndex] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 根据索引修改list中的数据
     * @param key
     * @param index
     * @param value
     * @return
     */
    public static boolean lSetByIndex(String key,long index,Object value){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[lSetByIndex] key is null !");
        }
        if(index<0){
            throw new RedisException("[lSetByIndex] index < 0 !");
        }
        try {
            redisTemplate.opsForList().set(key,index,value);
            return true;
        }catch (Exception e){
            throw new RedisException("[lSetByIndex] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 获取指定区间内的list的值 0 到 -1代表所有值
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static List<Object> lGet(String key,long start,long end){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[lGet] key is null !");
        }
        try {
            return redisTemplate.opsForList().range(key,start,end);
        }catch (Exception e){
            throw new RedisException("[sDel] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 将list值放入缓存
     * @param key
     * @param value
     * @return
     */
    public static boolean lSet(String key,Object value){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[lSet] key is null !");
        }
        try {
            Long result = redisTemplate.opsForList().rightPush(key,value);
            return result!=null;
        }catch (Exception e){
            throw new RedisException("[lSet] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 带失效时间的设置值
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public static boolean lSetWithExpire(String key,Object value,long expire){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[lSetWithExpire] key is null !");
        }
        if(expire<=0){
            throw new RedisException("[lSetWithExpire] expire time is illegal !");
        }
        try {
            redisTemplate.opsForList().rightPush(key,value);
            expire(key,expire);
            return true;
        }catch (Exception e){
            throw new RedisException("[lSetWithExpire] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 设置多个值
     * @param key
     * @param values
     * @return
     */
    public static boolean lSetMulti(String key,List<Object> values){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[lSetMulti] key is null !");
        }
        try {
            redisTemplate.opsForList().rightPushAll(key,values);
            return true;
        }catch (Exception e){
            throw new RedisException("[lSetMulti] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 设置带失效时间的
     * @param key
     * @param values
     * @param expire
     * @return
     */
    public static boolean lSetMultiWithExpire(String key,List<Object> values,long expire){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[lSetMultiWithExpire] key is null !");
        }
        if(expire<=0){
            throw new RedisException("[lSetMultiWithExpire] expire time is illegal !");
        }
        try {
            redisTemplate.opsForList().rightPushAll(key,values);
            expire(key,expire);
            return true;
        }catch (Exception e){
            throw new RedisException("[lSetMultiWithExpire] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * list的长度
     * @param key
     * @return
     */
    public static long lSize(String key){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[lSize] key is null !");
        }
        try {
            Long result = redisTemplate.opsForList().size(key);
            return result ==null ? 0 : result;
        }catch (Exception e){
            throw new RedisException("[lSize] method occur error : "+e.getMessage()+" !");
        }
    }

    /**
     * 删除N个值为value的项
     * @param key
     * @param count
     * @param value
     * @return
     */
    public static boolean lDel(String key,long count,Object value){
        if(StringUtils.isBlank(key)){
            throw new RedisException("[lDel] key is null !");
        }
        try {
            Long result = redisTemplate.opsForList().remove(key,count,value);
            return result !=null;
        }catch (Exception e){
            throw new RedisException("[lDel] method occur error : "+e.getMessage()+" !");
        }
    }

}
