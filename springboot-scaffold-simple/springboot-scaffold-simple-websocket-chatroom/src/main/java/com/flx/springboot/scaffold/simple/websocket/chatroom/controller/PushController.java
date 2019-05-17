package com.flx.springboot.scaffold.simple.websocket.chatroom.controller;

import com.flx.springboot.scaffold.simple.websocket.chatroom.client.WebSocketService;
import com.flx.springboot.scaffold.web.core.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Fenglixiong
 * @Create 2018.11.22 16:51
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/push")
public class PushController extends BaseController{

    @Autowired
    private WebSocketService webSocketService;

    /**
     * 发送私聊消息
     * @param request
     * @param userId
     * @param content
     * @return
     */
    @GetMapping("/sendP2pMsg")
    public ResultResponse<String> sendP2pMsg(HttpServletRequest request, Long userId, String content){
        webSocketService.sendChatMessageToUser(getChatUser(request),userId,content);
        return ResultResponse.ok("消息发送成功！");
    }

    /**
     * 发送群聊消息
     * @param request
     * @param roomId
     * @param content
     * @return
     */
    @GetMapping("/sendGroupMsg")
    public ResultResponse<String> sendGroupMsg(HttpServletRequest request,Long roomId,String content){
        webSocketService.sendChatMessageToRoom(getChatUser(request),roomId,content);
        return ResultResponse.ok("消息发送成功！");
    }

    /**
     * 发送系统消息
     * @param request
     * @param roomId
     * @param content
     * @return
     */
    @GetMapping("/sendSystemMsg")
    public ResultResponse<String> sendSystemMsg(HttpServletRequest request,Long roomId,String content){
        webSocketService.sendSystemMessageToRoom(getChatUser(request),roomId,content);
        return ResultResponse.ok("消息发送成功！");
    }

    /**
     * 发送人数消息
     * @param request
     * @param roomId
     * @return
     */
    @GetMapping("/sendOnlineMsg")
    public ResultResponse<String> sendOnlineMsg(HttpServletRequest request,Long roomId){
        webSocketService.sendLineMessageToRoom(getChatUser(request),roomId);
        return ResultResponse.ok("消息发送成功！");
    }

    /**
     * 发送广播消息
     * @param request
     * @param content
     * @return
     */
    @GetMapping("/sendBroadMsg")
    public ResultResponse<String> sendBroadMsg(HttpServletRequest request,String content){
        webSocketService.sendChatMessageToBroad(getChatUser(request),content);
        return ResultResponse.ok("消息发送成功！");
    }

}
