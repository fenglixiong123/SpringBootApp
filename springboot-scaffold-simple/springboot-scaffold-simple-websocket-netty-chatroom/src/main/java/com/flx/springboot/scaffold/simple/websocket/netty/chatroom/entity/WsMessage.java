package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.entity;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @Author Fenglixiong
 * @Create 2021/4/9 2:13
 * @Description 消息实体
 **/
@Data
public class WsMessage {

    /**
     * 消息id
     */
    private String id;
    /**
     * 消息发送类型
     */
    private String bizType;
    /**
     * 消息类型
     */
    private String msgType;
    /**
     * 发送人用户id
     */
    private String sendUserId;
    /**
     * 接收人用户id，多个逗号分隔
     */
    private String receiveUserId;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 消息扩展内容
     */
    private Map<String, Object> ext;

}
