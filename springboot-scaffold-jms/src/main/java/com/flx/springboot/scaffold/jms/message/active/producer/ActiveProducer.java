package com.flx.springboot.scaffold.jms.message.active.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.Topic;

/**
 * @Author Fenglixiong
 * @Create 2019.01.25 16:52
 * @Description
 * #=========ActiveMQ=========
 * #spring.activemq.broker-url=tcp://192.168.56.104:61616
 * #spring.activemq.in-memory=true
 * #spring.activemq.user=admin
 * #spring.activemq.password=admin123
 * #spring.activemq.pool.enabled=true
 * #spring.activemq.pool.max-connections=50
 * #添加支持发布订阅模型,单独只有订阅模式时候使用
 * #spring.jms.pub-sub-domain=true
 **/
@Slf4j
@Component
public class ActiveProducer {

    @Autowired
    private Destination queue;
    @Autowired
    private Topic topic;
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    /**
     * 点对点发送消息
     * @param message
     */
    public void sendPointMessage(String message){
        jmsTemplate.convertAndSend(queue,message);
    }


    /**
     * 订阅模式发送消息
     * @param message
     */
    public void sendTopicMessage(String message){
        jmsTemplate.convertAndSend(topic,message);
    }


}
