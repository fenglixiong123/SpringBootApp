package com.flx.springboot.scaffold.jms.message.rabbit.consumer;

import com.flx.springboot.scaffold.jms.common.constants.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author Fenglixiong
 * @Create 2019.01.25 17:52
 * @Description
 **/
@Slf4j
@Component
public class RabbitConsumer {

    /**
     * Direct方式监听器
     */
    @RabbitListener(queues = RabbitConstant.DIRECT_QUEUE)
    public void receiveDirect1(String message) {
        log.info(">>>>>>【receiveDirect1监听到消息】:{}" , message);
    }

    /**
     * Direct方式监听器
     */
    @RabbitListener(queues = RabbitConstant.DIRECT_QUEUE)
    public void receiveDirect2(String message) {
        log.info(">>>>>>【receiveDirect2监听到消息】:{}" , message);
    }



    /**
     * Fanout方式监听器
     */
    @RabbitListener(queues = RabbitConstant.FANOUT_QUEUE1)
    public void receiveFanout1(String message) {
        log.info(">>>>>>【receiveFanout1监听到消息】:{}" , message);
    }

    /**
     * Fanout方式监听器
     */
    @RabbitListener(queues = RabbitConstant.FANOUT_QUEUE2)
    public void receiveFanout2(String message) {
        log.info(">>>>>>【receiveFanout2监听到消息】:{}" , message);
    }



    /**
     * Topic方式监听器
     */
    @RabbitListener(queues = RabbitConstant.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        log.info(">>>>>>【receiveTopic1监听到消息】:{}" , message);
    }

    /**
     * Topic方式监听器
     */
    @RabbitListener(queues = RabbitConstant.TOPIC_QUEUE2)
    public void receiveTopic2(String message) {
        log.info(">>>>>>【receiveTopic2监听到消息】:{}" , message);
    }


}
