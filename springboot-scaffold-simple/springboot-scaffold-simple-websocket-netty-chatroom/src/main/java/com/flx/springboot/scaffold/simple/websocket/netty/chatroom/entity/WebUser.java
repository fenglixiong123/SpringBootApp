package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.entity;

import lombok.Data;

/**
 * @Author Fenglixiong
 * @Create 2021/4/9 2:21
 * @Description
 **/
@Data
public class WebUser {

    private Long id;

    private String nickname;

    private String username;

    private String password;

}
