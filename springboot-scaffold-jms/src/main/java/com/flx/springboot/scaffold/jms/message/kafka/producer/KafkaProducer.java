package com.flx.springboot.scaffold.jms.message.kafka.producer;

import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.jms.common.constants.KafkaConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author Fenglixiong
 * @Create 2019.01.14 19:07
 * @Description
 **/
@Slf4j
@Component
public class KafkaProducer implements InitializingBean {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 轮训随机发送到一个partition中去
     * @param message
     */
    public void sendMessage(String message){
        kafkaTemplate.send(KafkaConstant.KAFKA_TOPIC, JsonUtils.toJsonMsg(message));
    }

    /**
     * 以相同的key发送会hash到同一个partition中
     * @param key
     * @param message
     */
    public void sendMessage(String key,String message){
        kafkaTemplate.send(KafkaConstant.KAFKA_TOPIC, key ,JsonUtils.toJsonMsg(message));
    }

    /**
     * 发送到指定的partition中去,key失去作用
     * @param partition
     * @param key
     * @param message
     */
    public void sendMessage(Integer partition, String key,String message){
        kafkaTemplate.send(KafkaConstant.KAFKA_TOPIC,partition, key ,JsonUtils.toJsonMsg(message));
    }

    /**
     * 发送到新的topic中去
     * @param topic
     * @param partition
     * @param key
     * @param message
     */
    public void sendMessage(String topic, Integer partition, String key,Object message){
        kafkaTemplate.send(topic,partition, key ,JsonUtils.toJsonMsg(message));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String topic = kafkaTemplate.getDefaultTopic();
        log.info("-----------------------------------------------");
        log.info("--------->Kafka default topic:{}------",topic);
        log.info("-----------------------------------------------");
    }
}
