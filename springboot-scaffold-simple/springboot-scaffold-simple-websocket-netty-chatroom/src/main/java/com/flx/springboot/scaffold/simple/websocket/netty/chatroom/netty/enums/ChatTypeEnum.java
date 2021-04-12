package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.enums;

/**
 * @Author Fenglixiong
 * @Create 2021/4/9 1:44
 * @Description 发送消息类型
 **/
public enum ChatTypeEnum {

    person("私聊"),   //私聊消息
    group("群聊"),    //群聊消息
    ping("ping"),    //ping信息
    pong("pong"),    //pong信息

    system("系统"),   //系统信息
    online("上线"),   //上线消息
    offline("下线"),  //下线消息
    count("数量"),    //刷新人数消息
    list("列表");     //刷新列表消息

    ChatTypeEnum(String desc) {
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
