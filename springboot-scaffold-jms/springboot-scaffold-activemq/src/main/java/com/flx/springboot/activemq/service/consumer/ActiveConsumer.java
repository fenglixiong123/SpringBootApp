package com.flx.springboot.activemq.service.consumer;

import com.flx.springboot.activemq.constants.ActiveConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Author Fenglixiong
 * @Create 2019.01.25 16:52
 * @Description
 * 1.containerFactory在配置文件中配置
 * 2.containerFactory在消费者listener中配置
 * 3.spring.jms.pub-sub-domain=true配置文件中注释掉
 **/
@Slf4j
@Component
public class ActiveConsumer {

    @JmsListener(destination = ActiveConstant.ActiveQueuePoint,containerFactory = "jmsListenerContainerFactoryPoint")
    public void receiveMessage(String message){
        log.info(">>>>>>【ActiveMq】【Point】receive message:{}",message);
    }

    @JmsListener(destination= ActiveConstant.ActiveQueueTopic,containerFactory = "jmsListenerContainerFactoryTopic")
    public void receiveMessage1(String message){
        log.info(">>>>>>【ActiveMq】【topic】消费者1:{}",message);
    }

    @JmsListener(destination=ActiveConstant.ActiveQueueTopic,containerFactory = "jmsListenerContainerFactoryTopic")
    public void receiveMessage2(String message){
        log.info(">>>>>>【ActiveMq】【topic】消费者2:{}",message);
    }

    @JmsListener(destination=ActiveConstant.ActiveQueueTopic,containerFactory = "jmsListenerContainerFactoryTopic")
    public void receiveMessage3(String message){
        log.info(">>>>>>【ActiveMq】【topic】消费者3:{}",message);
    }


}
