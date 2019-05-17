package com.flx.springboot.scaffold.simple.websocket.chatroom.entity;

import com.flx.springboot.scaffold.simple.websocket.chatroom.common.enums.MessageEnum;
import lombok.Data;

/**
 * @Author Fenglixiong
 * @Create 2018.11.20 17:13
 * @Description
 **/
@Data
public class PayLoad {

    //用户ID
    private Long sendId;

    //用户名
    private String sendName;

    //消息类型
    private int type;

    //消息体
    private String content;

    public  PayLoad() {
    }

    public PayLoad(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public PayLoad(MessageEnum messageEnum, String content) {
        this.type = messageEnum.getCode();
        this.content = content;
    }

    public PayLoad(Long sendId, String sendName, int type, String content) {
        this.sendId = sendId;
        this.sendName = sendName;
        this.type = type;
        this.content = content;
    }

    public PayLoad(MessageEnum messageEnum, ChatUser chatUser, String content) {
        this.sendId = chatUser.getId();
        this.sendName = chatUser.getUsername();
        this.type = messageEnum.getCode();
        this.content = content;
    }

    public PayLoad(MessageEnum messageEnum, Long sendId, String sendName, String content) {
        this.sendId = sendId;
        this.sendName = sendName;
        this.type = messageEnum.getCode();
        this.content = content;
    }


}
