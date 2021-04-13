package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.vo;

import com.flx.springboot.scaffold.mybatis.plus.base.BaseEntity;
import lombok.Data;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/12 0:44
 * @Description: 群用户
 */
@Data
public class WebGroupUserVO extends BaseEntity {

    /**
     * 群Id
     */
    private Long groupId;

    /**
     * 群成员
     */
    private Long userId;

    /**
     * 群昵称
     */
    private String remarkName;

}
