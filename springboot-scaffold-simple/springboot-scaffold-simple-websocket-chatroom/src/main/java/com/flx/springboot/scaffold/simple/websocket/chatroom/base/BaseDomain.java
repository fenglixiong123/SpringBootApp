package com.flx.springboot.scaffold.simple.websocket.chatroom.base;

import javax.persistence.Column;
import java.util.Date;

/**
 * @Author Fenglixiong
 * @Create 2018.11.15 18:27
 * @Description
 **/
public class BaseDomain {

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;


}
