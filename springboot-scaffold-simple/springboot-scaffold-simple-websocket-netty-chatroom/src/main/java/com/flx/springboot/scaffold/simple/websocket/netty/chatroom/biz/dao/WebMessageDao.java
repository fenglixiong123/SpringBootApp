package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.dao;

import com.flx.springboot.scaffold.mybatis.plus.annotation.DaoMapper;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseDao;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.entity.WebMessage;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/24 17:25
 * @Description:
 */
@DaoMapper
public interface WebMessageDao extends BaseDao<WebMessage> {

}
