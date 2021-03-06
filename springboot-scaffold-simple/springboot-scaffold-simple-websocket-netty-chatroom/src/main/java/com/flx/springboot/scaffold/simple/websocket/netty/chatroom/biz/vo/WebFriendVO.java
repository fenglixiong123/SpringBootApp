package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.vo;

import com.flx.springboot.scaffold.mybatis.plus.base.BaseEntity;
import lombok.Data;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/11 20:19
 * @Description: 好友
 */
@Data
public class WebFriendVO extends BaseEntity {

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 好友Id
     */
    private Long friendId;

    /**
     * 备注昵称
     */
    private String remarkName;

    /**
     * 标签1#2#3
     */
    private String labels;

}
