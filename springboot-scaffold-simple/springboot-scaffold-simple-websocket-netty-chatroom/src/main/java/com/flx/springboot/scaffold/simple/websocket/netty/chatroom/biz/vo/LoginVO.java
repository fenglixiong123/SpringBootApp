package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.vo;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/13 15:42
 * @Description:
 */
@Data
public class LoginVO {

    @Size(max = 64)
    private String username;

    @Size(max = 64)
    private String password;

}
