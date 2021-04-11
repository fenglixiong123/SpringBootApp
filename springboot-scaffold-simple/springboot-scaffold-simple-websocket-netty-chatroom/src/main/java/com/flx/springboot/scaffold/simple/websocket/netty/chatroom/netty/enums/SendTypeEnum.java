package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.enums;

/**
 * @Author Fenglixiong
 * @Create 2021/4/9 1:44
 * @Description 发送消息类型
 **/
public enum SendTypeEnum {

    PRIVATE,//私聊消息
    GROUP,  //群聊消息
    PING,   //ping信息
    PONG,   //pong信息

    SYSTEM,   //系统信息
    ONLINE,   //上线消息
    OFFLINE,  //下线消息
    COUNT,    //刷新人数消息
    LIST,     //刷新列表消息

}
