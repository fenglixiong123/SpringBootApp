package com.flx.springboot.scaffold.logger.common.redis;

import com.flx.springboot.scaffold.common.context.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

/**
 * @Author Fenglixiong
 * @Create 2019.01.03 14:38
 * @Description
 **/

@Slf4j
@DependsOn({"redisConfig","messageHandler"})
@Component
public class RedisQueue implements InitializingBean {

    private static StringRedisTemplate redisTemplate = null;

    /**
     * 获取Bean
     * @return
     */
    private StringRedisTemplate getRedisTemplate(){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        if(ctx!=null) {
            return ctx.getBean(StringRedisTemplate.class);
        }
        return null;
    }

    /**
     * 入队
     * @param queue
     * @param key
     */
    public static void inQueue(String queue,String key){
        redisTemplate.opsForList().leftPush(queue,key);
    }

    /**
     * 出队
     * @param queue
     */
    public static String outQueue(String queue){
        return redisTemplate.opsForList().rightPop(queue);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("----------加载队列服务---------");
        RedisQueue.redisTemplate = SpringContextUtil.getBean(StringRedisTemplate.class);
    }

}
