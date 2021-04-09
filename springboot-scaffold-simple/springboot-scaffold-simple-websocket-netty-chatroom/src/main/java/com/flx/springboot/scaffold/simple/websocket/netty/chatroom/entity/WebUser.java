package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Author Fenglixiong
 * @Create 2021/4/9 2:21
 * @Description
 **/
@Data
@TableName(value = "web_user")
public class WebUser extends BaseEntity {

    private String nickname;

    private String username;

    private String password;

    private String icon;

    private String phone;

    private String sex;

    private String education;

    private String address;

    private Date expireTime;


}
