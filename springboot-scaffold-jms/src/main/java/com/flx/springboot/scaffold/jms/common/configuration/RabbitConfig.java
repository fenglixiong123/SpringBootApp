package com.flx.springboot.scaffold.jms.common.configuration;

import com.flx.springboot.scaffold.jms.common.constants.RabbitConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Fenglixiong
 * @Create 2019.01.24 19:19
 * @Description
 **/
@Configuration
public class RabbitConfig {

    /**
     * direct模式
     * 消息中的路由键（routing key）如果和 Binding 中的 binding key 一致， 交换器就将消息发到对应的队列中。路由键与队列名完全匹配
     */
    @Bean
    public Queue directQueue() {
        return new Queue(RabbitConstant.DIRECT_QUEUE);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitConstant.DIRECT_EXCHANGE);
    }

    @Bean
    public Binding directBinding(DirectExchange directExchange,Queue directQueue) {
        return BindingBuilder
                .bind(directQueue)
                .to(directExchange)
                .with("direct.key");
    }

    /**
     * Fanout模式
     * Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(RabbitConstant.FANOUT_QUEUE1);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue(RabbitConstant.FANOUT_QUEUE2);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitConstant.FANOUT_EXCHANGE);
    }

    @Bean
    public Binding fanoutBinding1(FanoutExchange fanoutExchange,Queue fanoutQueue1) {
        return BindingBuilder.bind(fanoutQueue1)
                .to(fanoutExchange);
    }

    @Bean
    public Binding fanoutBinding2(FanoutExchange fanoutExchange,Queue fanoutQueue2) {
        return BindingBuilder.bind(fanoutQueue2)
                .to(fanoutExchange);
    }


    /**
     * Topic模式
     * 相当于匹配模式
     */
    @Bean
    public Queue topicQueue1() {
        return new Queue(RabbitConstant.TOPIC_QUEUE1);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(RabbitConstant.TOPIC_QUEUE2);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitConstant.TOPIC_EXCHANGE);
    }

    @Bean
    public Binding topicBinding1(TopicExchange topicExchange,Queue topicQueue1) {
        return BindingBuilder
                .bind(topicQueue1)
                .to(topicExchange)
                .with("lzc.message");
    }

    @Bean
    public Binding topicBinding2(TopicExchange topicExchange,Queue topicQueue2) {
        return BindingBuilder
                .bind(topicQueue2)
                .to(topicExchange)
                .with("lzc.#");
    }

}
