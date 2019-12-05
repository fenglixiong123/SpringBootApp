package com.flx.springboot.rabbitmq.service.producer;

import com.flx.springboot.rabbitmq.constants.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Fenglixiong
 * @Create 2019.01.25 17:52
 * @Description
 **/
@Slf4j
@Component
public class RabbitProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDirect(String message) {
        this.rabbitTemplate.convertAndSend(RabbitConstant.DIRECT_EXCHANGE, "direct.key", message);
    }

    public void sendFanout(String message) {
        this.rabbitTemplate.convertAndSend(RabbitConstant.FANOUT_EXCHANGE, "", message);
    }

    public void sendTopic(String message) {
        this.rabbitTemplate.convertAndSend(RabbitConstant.TOPIC_EXCHANGE,"lzc.message", message);
        this.rabbitTemplate.convertAndSend(RabbitConstant.TOPIC_EXCHANGE, "lzc.lzc", message);
    }

}
