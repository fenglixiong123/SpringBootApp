package com.flx.springboot.scaffold.logger.common.redis;

import com.alibaba.fastjson.JSONObject;
import com.flx.springboot.scaffold.logger.dao.BizLogDao;
import com.flx.springboot.scaffold.logger.entity.BizLogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @Author Fenglixiong
 * @Create 2018.12.05 15:04
 * @Description
 **/
@Slf4j
@Component
public class MessageHandler implements MessageListener, InitializingBean {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    private BizLogDao bizLogDao;

    /**
     * 接收消息的方法
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        String msg = serializer.deserialize(message.getBody());
        log.info("【Redis】收到日志消息："+msg);
        processMessage(msg);
    }

    private void processMessage(String message){
        try{
            BizLogger bizLogger = JSONObject.parseObject(message, BizLogger.class);
            if(StringUtils.isNotBlank(bizLogger.getBizId())) {
               BizLogger logger = bizLogDao.save(bizLogger);
                if(logger!=null){
                    log.info("【Redis】异步存入数据库成功！");
                }else {
                    log.info("【Redis】异步存入数据库失败！");
                }
            }else {
                log.info("【Redis】主键不存在不入库！");
            }
        }catch (Exception e){
            log.error("【Redis】处理日志消息过程发生error...");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("------------MessageHandler-----------");
    }
}
