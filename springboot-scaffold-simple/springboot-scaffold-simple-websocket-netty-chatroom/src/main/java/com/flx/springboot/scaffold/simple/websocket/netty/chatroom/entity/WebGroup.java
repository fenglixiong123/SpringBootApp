package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/9 18:15
 * @Description: 用户群
 */
@Data
@TableName(value = "web_group")
public class WebGroup extends BaseEntity {

    /**
     * 群主
     */
    private Long ownerId;

    /**
     * 群名称
     */
    private String groupName;

    /**
     * 群头像
     */
    private String groupIcon;

    /**
     * 群公告
     */
    private String groupNotice;

    /**
     * 群介绍
     */
    private String groupIntro;

}
