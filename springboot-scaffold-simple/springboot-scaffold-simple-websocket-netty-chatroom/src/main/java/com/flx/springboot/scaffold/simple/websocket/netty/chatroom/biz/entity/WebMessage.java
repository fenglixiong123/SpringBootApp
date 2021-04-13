package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.entity;

import com.flx.springboot.scaffold.mybatis.plus.annotation.TableName;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseDO;
import lombok.Data;

import java.util.Date;

/**
 * @Author Fenglixiong
 * @Create 2021/4/9 2:13
 * @Description 消息实体
 **/
@Data
@TableName(value = "web_message")
public class WebMessage extends BaseDO {

    /**
     * 发送者id
     */
    private Long senderId;

    /**
     * 接收者id
     */
    private Long receiverId;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 消息业务类型
     */
    private String chatType;

    /**
     * 发送时间
     */
    private Date sendTime;

}
