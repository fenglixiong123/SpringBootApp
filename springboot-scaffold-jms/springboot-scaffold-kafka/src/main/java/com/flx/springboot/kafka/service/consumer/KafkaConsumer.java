package com.flx.springboot.kafka.service.consumer;

import com.alibaba.fastjson.JSONObject;
import com.flx.springboot.kafka.constants.KafkaConstant;
import com.flx.springboot.kafka.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author Fenglixiong
 * @Create 2019.01.11 19:45
 * @Description
 **/
@Slf4j
@Component
public class KafkaConsumer {

    /**
     * group1的消费者
     * @param record
     */
    @KafkaListener(topics = {KafkaConstant.KAFKA_TOPIC,KafkaConstant.KAFKA_TOPIC2},groupId = "group1")
    public void receiveMessage1(ConsumerRecord<?, ?> record){
        log.info("<<<<<<<<<【group1-consumer1】-->ReceiveMessage:topic:{},value:{},key:{},offset:{},partition:{}",
                record.topic(),
                record.value(),record.key(),
                record.offset(),record.partition());
    }

    /**
     * group1的消费者
     * @param record
     */
    @KafkaListener(topics = {KafkaConstant.KAFKA_TOPIC,KafkaConstant.KAFKA_TOPIC2},groupId = "group1")
    public void receiveMessage2(ConsumerRecord<?, ?> record){
        log.info("<<<<<<<<<【group1--consumer2】-->ReceiveMessage:topic:{},value:{},key:{},offset:{},partition:{}",
                record.topic(),
                record.value(),record.key(),
                record.offset(),record.partition());
    }

    /**
     * group2的消费者
     * @param record
     */
    @KafkaListener(topics = {KafkaConstant.KAFKA_TOPIC,KafkaConstant.KAFKA_TOPIC2},groupId = "group2")
    public void receiveMessage3(ConsumerRecord<?, ?> record){
        log.info("<<<<<<<<<【group2-consumer3】-->ReceiveMessage:topic:{},value:{},key:{},offset:{},partition:{}",
                record.topic(),
                record.value(),record.key(),
                record.offset(),record.partition());
    }

    /**
     * group2的消费者
     * @param record
     */
    @KafkaListener(topics = {KafkaConstant.KAFKA_TOPIC,KafkaConstant.KAFKA_TOPIC2},groupId = "group2")
    public void receiveMessage4(ConsumerRecord<?, ?> record){
        log.info("<<<<<<<<<【group2-consumer4】-->ReceiveMessage:topic:{},value:{},key:{},offset:{},partition:{}",
                record.topic(),
                record.value(),record.key(),
                record.offset(),record.partition());
    }

    /**
     * 消费对象
     * @param record
     */
    @KafkaListener(topics = {KafkaConstant.KAFKA_TOPIC_STUDENT},groupId = "group0")
    public void receiveMessage5(ConsumerRecord<?, String> record){
        log.info("<<<<<<<<<【group0-consumer5】-->ReceiveMessage:topic:{},value:{},key:{},offset:{},partition:{}",
                record.topic(),
                record.value(),record.key(),
                record.offset(),record.partition());
        String message = record.value();
        log.info("message:{}", message);
        Student student = JSONObject.parseObject(message,Student.class);
        log.info("student:id:{},name:{},work:{}",student.getId(),student.getName(),student.getWork());
    }

}
