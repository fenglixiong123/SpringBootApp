package com.flx.springboot.scaffold.jms.controller.active;

import com.flx.springboot.scaffold.jms.message.active.producer.ActiveProducer;
import com.flx.springboot.scaffold.web.core.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Fenglixiong
 * @Create 2019.01.24 18:13
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/active")
public class ActiveMqController {

    @Autowired
    private ActiveProducer activeProducer;

    /**
     * 点对点发送消息
     * @param message
     * @return
     */
    @GetMapping("/sendPoint")
    public ResultResponse<String> sendPoint(@RequestParam String message){
        log.info("【ActiveMQ】sendPoint发送了一条消息：{}",message);
        activeProducer.sendPointMessage(message);
        return ResultResponse.ok("sendPoint successful");
    }

    /**
     * 发布订阅发送消息
     * @param message
     * @return
     */
    @GetMapping("/sendTopic")
    public ResultResponse<String> sendTopic(@RequestParam String message){
        log.info("【ActiveMQ】sendTopic发送了一条消息：{}",message);
        activeProducer.sendTopicMessage(message);
        return ResultResponse.ok("sendTopic successful");
    }

}
