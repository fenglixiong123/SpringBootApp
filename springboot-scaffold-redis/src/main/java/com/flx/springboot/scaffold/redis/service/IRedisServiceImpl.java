package com.flx.springboot.scaffold.redis.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Fenglixiong
 * @Date: 2020/9/2 20:00
 * @Description:
 */
public class IRedisServiceImpl implements IRedisService{

    @Autowired
    private RedisBaseService redisBaseService;
    @Autowired
    private RedisStringService redisStringService;
    @Autowired
    private RedisHashService redisHashService;
    @Autowired
    private RedisListService redisListService;
    @Autowired
    private RedisSetService redisSetService;

    @Override
    public boolean expire(String key, long expire) {
        return redisBaseService.expire(key,expire);
    }

    @Override
    public long getExpire(String key) {
        return redisBaseService.getExpire(key);
    }

    @Override
    public boolean hasKey(String key) {
        return redisBaseService.hasKey(key);
    }

    @Override
    public boolean delete(String key) {
        return redisBaseService.delete(key);
    }

    @Override
    public int deleteKeys(List<String> keys) {
        return redisBaseService.deleteKeys(keys);
    }

    @Override
    public Object get(String key) {
        return redisStringService.get(key);
    }

    @Override
    public boolean set(String key, Object value) {
        return redisStringService.set(key,value);
    }

    @Override
    public boolean setWithExpire(String key, Object value, long expire) {
        return redisStringService.setWithExpire(key,value,expire);
    }

    @Override
    public long incr(String key, long count) {
        return redisStringService.incr(key,count);
    }

    @Override
    public long decr(String key, long count) {
        return redisStringService.decr(key,count);
    }

    @Override
    public Object hGet(String key, String item) {
        return redisHashService.hGet(key,item);
    }

    @Override
    public boolean hSet(String key, String item, Object value) {
        return redisHashService.hSet(key,item,value);
    }

    @Override
    public boolean hSetWithExpire(String key, String item, Object value, long expire) {
        return redisHashService.hSetWithExpire(key,item,value,expire);
    }

    @Override
    public Map<Object, Object> hmGet(String key) {
        return redisHashService.hmGet(key);
    }

    @Override
    public boolean hmSet(String key, Map<String, Object> map) {
        return redisHashService.hmSet(key,map);
    }

    @Override
    public boolean hmSetWithExpire(String key, Map<String, Object> map, long expire) {
        return redisHashService.hmSetWithExpire(key,map,expire);
    }

    @Override
    public boolean hDel(String key, Object... item) {
        return redisHashService.hDel(key,item);
    }

    @Override
    public boolean hHasKey(String key, String item) {
        return redisHashService.hHasKey(key,item);
    }

    @Override
    public double hIncr(String key, String item, double by) {
        return redisHashService.hIncr(key,item,by);
    }

    @Override
    public double hDecr(String key, String item, double by) {
        return redisHashService.hDecr(key,item,by);
    }

    @Override
    public Set<Object> sGet(String key) {
        return redisSetService.sGet(key);
    }

    @Override
    public boolean sSet(String key, Object... values) {
        return redisSetService.sSet(key,values);
    }

    @Override
    public boolean sSetWithExpire(String key, long expire, Object... values) {
        return redisSetService.sSetWithExpire(key,expire,values);
    }

    @Override
    public boolean sHasKey(String key, Object value) {
        return redisSetService.sHasKey(key,value);
    }

    @Override
    public long sSize(String key) {
        return redisSetService.sSize(key);
    }

    @Override
    public boolean sDel(String key, Object... values) {
        return redisSetService.sDel(key,values);
    }

    @Override
    public Object lGetByIndex(String key, long index) {
        return redisListService.lGetByIndex(key,index);
    }

    @Override
    public boolean lSetByIndex(String key, long index, Object value) {
        return redisListService.lSetByIndex(key,index,value);
    }

    @Override
    public List<Object> lGet(String key, long start, long end) {
        return redisListService.lGet(key,start,end);
    }

    @Override
    public boolean lSet(String key, Object value) {
        return redisListService.lSet(key,value);
    }

    @Override
    public boolean lSetWithExpire(String key, Object value, long expire) {
        return redisListService.lSetWithExpire(key,value,expire);
    }

    @Override
    public boolean lSetMulti(String key, List<Object> values) {
        return redisListService.lSetMulti(key,values);
    }

    @Override
    public boolean lSetMultiWithExpire(String key, List<Object> values, long expire) {
        return redisListService.lSetMultiWithExpire(key,values,expire);
    }

    @Override
    public long lSize(String key) {
        return redisListService.lSize(key);
    }

    @Override
    public boolean lDel(String key, long count, Object value) {
        return redisListService.lDel(key,count,value);
    }

}
