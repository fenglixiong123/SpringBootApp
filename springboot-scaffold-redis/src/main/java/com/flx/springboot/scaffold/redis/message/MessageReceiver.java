package com.flx.springboot.scaffold.redis.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/29 18:51
 * @Description: 消息监听器，使用者必须继承这个类，并实现方法
 */
@Slf4j
@Component
public abstract class MessageReceiver implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.info("Receive a message from {}",new String(message.getChannel()));
        this.handleMessage(message);

    }

    /**
     * 需要有实现类来调用
     * @param message
     */
    public abstract void handleMessage(Message message);

}
