package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.enums;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/9 19:03
 * @Description:
 */
public enum EduEnum {

    DOCTOR("博士"),//博士
    MASTER("硕士"),//硕士
    COLLEGE("本科"),//本科
    SENIOR("高中"),//高中
    JUNIOR("初中"),//初中
    PRIMARY("小学");//小学

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
