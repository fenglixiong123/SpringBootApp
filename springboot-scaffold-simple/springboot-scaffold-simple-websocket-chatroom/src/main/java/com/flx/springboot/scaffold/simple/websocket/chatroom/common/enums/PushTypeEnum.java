package com.flx.springboot.scaffold.simple.websocket.chatroom.common.enums;

/**
 * @Author Fenglixiong
 * @Create 2018.11.22 16:29
 * @Description
 **/
public enum PushTypeEnum {

    GROUP_PUSH(1,"群播"),
    POINT_PUSH(2,"单播"),
    BROAD_PUSH(3,"广播");

    private PushTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
