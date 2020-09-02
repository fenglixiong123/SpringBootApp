package com.flx.springboot.scaffold.redis.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Fenglixiong
 * @Date: 2020/9/2 19:58
 * @Description:
 */
public interface IRedisService {

    //基础操作

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param expire 时间
     * @return
     */
    boolean expire(String key,long expire);

    /**
     * 获取key失效时间
     * @param key
     * @return 返回0代表永久有效
     */
    long getExpire(String key);

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    boolean hasKey(String key);

    /**
     * 删除缓存
     * @param key
     */
    boolean delete(String key);

    /**
     * 删除多个缓存key
     * @param keys
     * @return
     */
    int deleteKeys(List<String> keys);

    //String操作

    /**
     * 获取值
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 设置值
     * @param key
     * @param value
     * @return
     */
    boolean set(String key,Object value);

    /**
     * 设置带失效时间的值
     * @param key
     * @param value
     * @param expire
     * @return
     */
    boolean setWithExpire(String key,Object value,long expire);

    /**
     * 递增
     * @param key
     * @param count
     * @return
     */
    long incr(String key,long count);

    /**
     * 递减
     * @param key
     * @param count
     * @return
     */
    long decr(String key,long count);
    

    //Hash操作----------------------------------------------

    /**
     * Hash Get
     * @param key 键
     * @param item 项
     * @return
     */
    Object hGet(String key,String item);

    /**
     * Hash Set 设置数据，不存在则创建
     * @param key
     * @param item
     * @return
     */
    boolean hSet(String key,String item,Object value);

    /**
     * Hash Set 设置数据，不存在则创建
     * @param key
     * @param item
     * @return
     */
    boolean hSetWithExpire(String key,String item,Object value,long expire);

    /**
     * 获取hashKey的所有键值对
     * @param key
     * @return
     */
    Map<Object,Object> hmGet(String key);

    /**
     * HashSet 设置多个键值对
     * @param key
     * @param map
     * @return
     */
    boolean hmSet(String key,Map<String,Object> map);

    /**
     * HashSet 设置多个键值对带过期时间
     * @param key
     * @param map
     * @return
     */
    boolean hmSetWithExpire(String key,Map<String,Object> map,long expire);

    /**
     * 删除Hash表中的值
     * @param key
     * @param item
     * @return
     */
    boolean hDel(String key,Object ... item);

    /**
     * 判断hash表中是否存在该项值
     * @param key
     * @param item
     * @return
     */
    boolean hHasKey(String key,String item);

    /**
     * Hash 递增，如果不存在则创建 并返回新增后的值
     * @param key
     * @param item
     * @param by
     * @return
     */
    double hIncr(String key,String item,double by);

    /**
     * 减少多少
     * @param key
     * @param item
     * @param by
     * @return
     */
    double hDecr(String key,String item,double by);

    
    //Set操作---------------------------------------------------------

    /**
     * 根据key获取set的所有值
     * @param key
     * @return
     */
    Set<Object> sGet(String key);

    /**
     * 将数据放入set缓存
     * @param key
     * @param values
     * @return
     */
    boolean sSet(String key,Object ... values);

    /**
     * 带失效时间的set设置
     * @param key
     * @param values
     * @param expire
     * @return
     */
    boolean sSetWithExpire(String key, long expire, Object ... values);

    /**
     * 是否有某个value
     * @param key
     * @param value
     * @return
     */
    boolean sHasKey(String key,Object value);

    /**
     * 获取set的长度
     * @param key
     * @return
     */
    long sSize(String key);

    /**
     * 移除value值
     * @param key
     * @param values
     * @return
     */
    boolean sDel(String key,Object ... values);


    //List操作-------------------------------------------

    /**
     * 通过下标获取list的值
     * @param key
     * @param index
     * @return
     */
    Object lGetByIndex(String key,long index);

    /**
     * 根据索引修改list中的数据
     * @param key
     * @param index
     * @param value
     * @return
     */
    boolean lSetByIndex(String key,long index,Object value);

    /**
     * 获取指定区间内的list的值 0 到 -1代表所有值
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<Object> lGet(String key, long start, long end);

    /**
     * 将list值放入缓存
     * @param key
     * @param value
     * @return
     */
    boolean lSet(String key,Object value);

    /**
     * 带失效时间的设置值
     * @param key
     * @param value
     * @param expire
     * @return
     */
    boolean lSetWithExpire(String key,Object value,long expire);

    /**
     * 设置多个值
     * @param key
     * @param values
     * @return
     */
    boolean lSetMulti(String key,List<Object> values);

    /**
     * 设置带失效时间的
     * @param key
     * @param values
     * @param expire
     * @return
     */
    boolean lSetMultiWithExpire(String key,List<Object> values,long expire);

    /**
     * list的长度
     * @param key
     * @return
     */
    long lSize(String key);

    /**
     * 删除N个值为value的项
     * @param key
     * @param count
     * @param value
     * @return
     */
    boolean lDel(String key,long count,Object value);
    
    
}
