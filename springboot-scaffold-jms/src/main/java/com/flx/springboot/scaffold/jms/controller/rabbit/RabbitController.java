package com.flx.springboot.scaffold.jms.controller.rabbit;

import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.jms.entity.Student;
import com.flx.springboot.scaffold.jms.message.rabbit.producer.RabbitProducer;
import com.flx.springboot.scaffold.web.core.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Fenglixiong
 * @Create 2019.01.28 19:26
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private RabbitProducer rabbitProducer;

    @GetMapping("/sendDirect")
    public ResultResponse<String> sendDirect() {
        String message = JsonUtils.toJsonMsg(new Student(1L,"jack","study"));
        rabbitProducer.sendDirect(message);
        log.info("【RabbitMQ】sendDirect发送了一条消息：{}",message);
        return ResultResponse.ok("RabbitMQ发送成功！");
    }

    @GetMapping("/sendFanout")
    public ResultResponse<String> sendFanout() {
        String message = JsonUtils.toJsonMsg(new Student(2L,"tom","foot"));
        rabbitProducer.sendFanout(message);
        log.info("【RabbitMQ】sendFanout发送了一条消息：{}",message);
        return ResultResponse.ok("RabbitMQ发送成功！");
    }

    @GetMapping("/sendTopic")
    public ResultResponse<String> sendTopic() {
        String message = JsonUtils.toJsonMsg(new Student(3L,"mary","eat"));
        rabbitProducer.sendTopic(JsonUtils.toJsonMsg(new Student(3L,"mary","eat")));
        log.info("【RabbitMQ】sendTopic发送了一条消息：{}",message);
        return ResultResponse.ok("RabbitMQ发送成功！");
    }

}
