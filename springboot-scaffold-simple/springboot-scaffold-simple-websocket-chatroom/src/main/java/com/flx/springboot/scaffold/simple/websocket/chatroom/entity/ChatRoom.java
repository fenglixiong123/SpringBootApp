package com.flx.springboot.scaffold.simple.websocket.chatroom.entity;

import com.flx.springboot.scaffold.simple.websocket.chatroom.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Fenglixiong
 * @Create 2018.11.20 10:15
 * @Description 聊天室实体类
 **/
@Data
@Entity
@Table(name = "t_chat_room")
public class ChatRoom extends BaseDomain {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "last_msg")
    private String lastMsg;

    @Column(name = "last_msg_time")
    private Date lastMsgTime;

}
