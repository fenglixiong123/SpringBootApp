package com.flx.springboot.scaffold.simple.websocket.chatroom.controller;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.simple.websocket.chatroom.common.constants.WebContant;
import com.flx.springboot.scaffold.simple.websocket.chatroom.entity.ChatUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Fenglixiong
 * @Create 2018.11.21 14:58
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/")
public class LoginController {

    @PostMapping("/login")
    public ResultResponse<String> login(HttpServletRequest request,
                                        @RequestParam String username,
                                        @RequestParam String password){
        log.info("Hi,login Username:{},Password:{}",username,password);
        if((username.equals("admin")&&password.equals("admin123"))||
           (username.equals("jack")&&password.equals("jack123"))||
           (username.equals("tom")&&password.equals("tom123"))){
            request.getSession().setAttribute(WebContant.WEBSOCKET_SESSION_KEY,new ChatUser(getUserIdByName(username),username,password,username+"一级新人"));
            log.info("login successful !");
            return ResultResponse.success("登陆成功！");
        }
        log.info("login error !");
        return ResultResponse.error("登陆失败！");
    }

    private Long getUserIdByName(String username){
        if(username.equals("admin")){
            return 1L;
        }else if(username.equals("jack")){
            return 2L;
        }else if(username.equals("tom")){
            return 3L;
        }else {
            return 99L;
        }
    }

}
