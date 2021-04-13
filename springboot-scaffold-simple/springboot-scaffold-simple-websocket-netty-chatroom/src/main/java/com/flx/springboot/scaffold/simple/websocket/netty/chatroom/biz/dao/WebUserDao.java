package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.dao;

import com.flx.springboot.scaffold.mybatis.plus.annotation.DaoMapper;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseDao;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.entity.WebUser;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/24 17:25
 * @Description:
 */
@DaoMapper
public interface WebUserDao extends BaseDao<WebUser> {

    @Select("select count(1) from web_user")
    int countWebUser();

}
