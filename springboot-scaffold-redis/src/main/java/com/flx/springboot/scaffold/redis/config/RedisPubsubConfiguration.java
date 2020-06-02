package com.flx.springboot.scaffold.redis.config;

import com.flx.springboot.scaffold.redis.constant.RedisConstant;
import com.flx.springboot.scaffold.redis.message.MessageReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/29 18:40
 * @Description:
 * 消息发布订阅配置类
 * 其中消息监听器可以是多个
 *
 */
@Slf4j
@Configuration
public class RedisPubsubConfiguration implements InitializingBean {

    /**
     * Redis消息监听器容器
     * 可以添加监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定
     * 该消息监听器会通过反射技术调用消息订阅处理器的相关方法进行一些业务的处理
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //可以添加多个Listener
        container.addMessageListener(listenerAdapter,new PatternTopic(RedisConstant.DEFAULT_CHANNEL));
        return container;
    }

    /**
     * 消息监听器适配器，绑定消息处理器，使用反射技术调用业务方法
     * @param messageReceiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(MessageReceiver messageReceiver){
        return new MessageListenerAdapter(messageReceiver,"onMessage");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("=============注册Redis pubsub服务成功===========");
    }
}
