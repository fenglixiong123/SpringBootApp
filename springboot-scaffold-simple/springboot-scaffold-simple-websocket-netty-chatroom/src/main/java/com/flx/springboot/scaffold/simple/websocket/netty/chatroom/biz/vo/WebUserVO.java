package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.vo;

import com.flx.springboot.scaffold.mybatis.plus.base.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(max = 64)
    @NotNull
    private String nickName;

    /**
     * 用户名
     */
    @Size(max = 64)
    @NotNull
    private String userName;

    /**
     * 密码
     */
    @Size(max = 64)
    @NotNull
    private String password;

    /**
     * 找回密码
     */
    private String passwordReminder;

    /**
     * 头像
     */
    @Size(max = 64)
    private String icon;

    /**
     * 个性签名
     */
    @Size(max = 64)
    private String signature;

    /**
     * 电话
     */
    @NotNull
    @Size(max = 64)
    private String phone;

    /**
     * 性别
     */
    @NotNull
    @Size(max = 10)
    private String sex;

    /**
     * 年龄
     */
    private int age;

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
    @NotNull
    @Size(max = 64)
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
