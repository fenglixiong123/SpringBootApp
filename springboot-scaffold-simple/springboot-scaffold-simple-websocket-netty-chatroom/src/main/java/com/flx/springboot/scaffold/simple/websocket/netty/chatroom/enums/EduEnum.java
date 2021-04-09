package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.enums;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/9 19:03
 * @Description:
 */
public enum EduEnum {

    DOCTOR("a"),//硕士
    MASTER("a"),//硕士
    COLLEGE("a"),//大学
    SENIOR("a"),//高中
    JUNIOR("a"),//初中
    PRIMARY("a");//小学

    EduEnum(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
