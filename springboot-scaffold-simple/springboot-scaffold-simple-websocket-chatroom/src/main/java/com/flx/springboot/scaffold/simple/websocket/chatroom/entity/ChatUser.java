package com.flx.springboot.scaffold.simple.websocket.chatroom.entity;

import com.flx.springboot.scaffold.simple.websocket.chatroom.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

/**
 * @Author Fenglixiong
 * @Create 2018.11.20 10:21
 * @Description
 **/
@Data
@Entity
@Table(name = "t_chat_user")
public class ChatUser extends BaseDomain {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "remark")
    private String remark;

    public ChatUser() {
    }

    public ChatUser(String username, String password, String remark) {
        this.username = username;
        this.password = password;
        this.remark = remark;
    }

    public ChatUser(Long id,String username, String password, String remark) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.remark = remark;
    }

}
