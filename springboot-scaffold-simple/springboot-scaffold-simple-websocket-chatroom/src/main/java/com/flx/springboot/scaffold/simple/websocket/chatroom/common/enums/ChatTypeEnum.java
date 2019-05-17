package com.flx.springboot.scaffold.simple.websocket.chatroom.common.enums;

/**
 * @Author Fenglixiong
 * @Create 2018.11.22 10:13
 * @Description
 **/
public enum  ChatTypeEnum {

    GROUP_CHAT(1,"GROUPCHAT","群聊"),
    PRIVATE_CHAT(2,"PRIVATECHAT","私聊"),
    PUBLIC_CHAT(3,"PUBLICCHAT","公聊");

    private ChatTypeEnum(int code, String name, String desc){
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    private int code;
    private String name;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
