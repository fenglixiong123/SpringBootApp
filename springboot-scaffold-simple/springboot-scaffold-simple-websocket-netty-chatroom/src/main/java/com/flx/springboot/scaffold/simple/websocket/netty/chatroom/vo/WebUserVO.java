package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.vo;

import com.flx.springboot.scaffold.mybatis.plus.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/11 16:48
 * @Description:
 */
@Data
public class WebUserVO extends BaseEntity {

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 找回密码
     */
    private String passwordReminder;

    /**
     * 头像
     */
    private String icon;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 电话
     */
    private String phone;

    /**
     * 性别
     */
    private String sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 教育
     */
    private String education;

    /**
     * 学校
     */
    private String school;

    /**
     * 邮件
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 过期时间
     */
    private Date expireTime;

}
