package com.flx.springboot.scaffold.simple.websocket.chatroom.entity;

import com.flx.springboot.scaffold.simple.websocket.chatroom.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

/**
 * @Author Fenglixiong
 * @Create 2018.11.20 10:18
 * @Description
 **/
@Data
@Entity
@Table(name = "t_chat_message")
public class ChatMessage extends BaseDomain {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "content")
    private String content;
    //房间id
    @Column(name = "room_id")
    private Long roomId;
    //文字//图片//语音//系统消息
    @Column(name = "content_type")
    private Integer contentType;

    @Column(name = "content_size")
    private int contentSize;

}
