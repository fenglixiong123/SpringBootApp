package com.flx.springboot.scaffold.simple.websocket.controller;

import com.flx.springboot.scaffold.simple.websocket.common.SimpleWebSocket;
import com.flx.springboot.scaffold.web.core.result.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Fenglixiong
 * @Create 2018.11.19 16:20
 * @Description 消息推送的类
 **/
@RestController
@RequestMapping("/admin")
public class PushController {

    @Autowired
    private SimpleWebSocket simpleWebSocket;

    @GetMapping("/push")
    public ResultResponse<String> pushMessage(String message){
        simpleWebSocket.sendMessageToAll(message);
        return ResultResponse.ok("推送消息成功！");
    }

}
