package com.flx.springboot.scaffold.application.redis;

import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.redis.message.MessageReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @Author: Fenglixiong
 * @Date: 2020/6/2 15:52
 * @Description:
 */
@Slf4j
//@Component
public class MessageReceiverClient extends MessageReceiver {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void handleMessage(Message message) {
        RedisSerializer serializer = redisTemplate.getValueSerializer();
        Object msg = serializer.deserialize(message.getBody());
        log.info("MessageReceiverClient : {}", JsonUtils.toJsonMsg(msg));
    }

}
