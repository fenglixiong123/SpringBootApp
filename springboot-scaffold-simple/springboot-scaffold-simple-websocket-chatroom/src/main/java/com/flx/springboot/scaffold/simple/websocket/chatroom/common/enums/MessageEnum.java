package com.flx.springboot.scaffold.simple.websocket.chatroom.common.enums;

public enum MessageEnum {

    BROAD_MSG(0,"广播消息"),
    SYSTEM_MSG(1,"系统消息"),
    WELCOME_MSG(2,"欢迎消息"),
    QUIT_MSG(3,"退出消息"),
    CHAT_MSG(4,"聊天消息"),
    LINE_MSG(5,"人数消息");

    private MessageEnum(int code,String desc){
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
