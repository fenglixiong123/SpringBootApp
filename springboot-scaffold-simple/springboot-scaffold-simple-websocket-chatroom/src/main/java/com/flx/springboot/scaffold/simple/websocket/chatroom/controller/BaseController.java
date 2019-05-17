package com.flx.springboot.scaffold.simple.websocket.chatroom.controller;

import com.flx.springboot.scaffold.simple.websocket.chatroom.common.constants.WebContant;
import com.flx.springboot.scaffold.simple.websocket.chatroom.entity.ChatUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author Fenglixiong
 * @Create 2018.11.22 15:34
 * @Description
 **/
public class BaseController {

    protected ChatUser getChatUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (ChatUser) session.getAttribute(WebContant.WEBSOCKET_SESSION_KEY);
    }

}
