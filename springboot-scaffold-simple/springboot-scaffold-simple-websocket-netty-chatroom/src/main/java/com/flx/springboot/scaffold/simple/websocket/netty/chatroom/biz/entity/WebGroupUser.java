package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.entity;

import com.flx.springboot.scaffold.mybatis.plus.annotation.TableName;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseDO;
import lombok.Data;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/12 0:44
 * @Description: 群用户
 */
@Data
@TableName(value = "web_group_user")
public class WebGroupUser extends BaseDO {

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
