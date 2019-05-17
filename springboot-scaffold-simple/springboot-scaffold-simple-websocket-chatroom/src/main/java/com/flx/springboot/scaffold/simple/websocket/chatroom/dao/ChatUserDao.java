package com.flx.springboot.scaffold.simple.websocket.chatroom.dao;

import com.flx.springboot.scaffold.simple.websocket.chatroom.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author Fenglixiong
 * @Create 2018.11.20 10:35
 * @Description
 **/
@Repository
public interface ChatUserDao extends JpaRepository<ChatUser,Long>, JpaSpecificationExecutor<ChatUser> {
}
