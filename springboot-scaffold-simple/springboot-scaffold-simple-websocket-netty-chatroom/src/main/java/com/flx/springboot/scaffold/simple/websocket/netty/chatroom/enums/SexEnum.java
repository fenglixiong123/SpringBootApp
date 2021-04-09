package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.enums;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/9 19:02
 * @Description:
 */
public enum SexEnum {

    MALE("男"),//男
    FEMALE("女");//女

    SexEnum(String desc) {
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
