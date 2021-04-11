package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseEntity;
import lombok.Data;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/12 0:44
 * @Description: 群用户
 */
@Data
@TableName(value = "web_group_user")
public class WebGroupUser extends BaseEntity {

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
