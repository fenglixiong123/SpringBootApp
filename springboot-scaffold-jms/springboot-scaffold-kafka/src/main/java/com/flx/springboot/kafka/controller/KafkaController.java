package com.flx.springboot.kafka.controller;

import com.flx.springboot.kafka.constants.KafkaConstant;
import com.flx.springboot.kafka.entity.Student;
import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.kafka.service.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Fenglixiong
 * @Create 2019.01.11 19:28
 * @Description
 **/
@Slf4j
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/send")
    public ResultResponse<String> send(@RequestParam String message){
        kafkaProducer.sendMessage(message);
        log.info("【Kafka】send发送了一条消息:{}",message);
        return ResultResponse.success("消息发送成功！");
    }

    @GetMapping("/sendKey")
    public ResultResponse<String> sendKey(@RequestParam String key,@RequestParam String message){
        kafkaProducer.sendMessage(key,message);
        log.info("【Kafka】sendKey发送了一条消息:{}",message);
        return ResultResponse.success("消息发送成功！");
    }

    @GetMapping("/sendPartition")
    public ResultResponse<String> sendPartition(@RequestParam String message){
        kafkaProducer.sendMessage(1,"myKey",message);
        log.info("【Kafka】sendPartition发送了一条消息:{}",message);
        return ResultResponse.success("消息发送成功！");
    }

    @GetMapping("/sendTopic")
    public ResultResponse<String> sendTopic(@RequestParam String message){
        kafkaProducer.sendMessage("mytopic2",0,"myKey",message);
        log.info("【Kafka】sendTopic发送了一条消息:{}",message);
        return ResultResponse.success("消息发送成功！");
    }

    @GetMapping("/sendStudent")
    public ResultResponse<Long> sendStudent(Student student){
        String message = JsonUtils.toJsonMsg(student);
        log.info(">>>>>>>>>>>>>sendStudent入参:{}", message);
        kafkaProducer.sendMessage(KafkaConstant.KAFKA_TOPIC_STUDENT,0,null,student);
        return ResultResponse.success(99L);
    }

}
