package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.dto;

import com.flx.springboot.scaffold.mybatis.plus.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author fenglixiong
 * @date2018-12-18-16:59
 */
@Data
public class WebUserDTO extends BaseEntity {

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
