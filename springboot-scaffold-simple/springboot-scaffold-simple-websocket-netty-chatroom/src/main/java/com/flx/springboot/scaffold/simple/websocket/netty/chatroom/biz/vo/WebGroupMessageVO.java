package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.vo;

import com.flx.springboot.scaffold.mybatis.plus.base.BaseEntity;
import lombok.Data;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/12 0:58
 * @Description:
 */
@Data
public class WebGroupMessageVO extends BaseEntity {

    /**
     * 群Id
     */
    private Long groupId;

    /**
     * 发送者Id
     */
    private Long senderId;

    /**
     * 发送者昵称
     */
    private String sendName;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息类型
     */
    private String message;

}
