package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.service;

import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.vo.LoginVO;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.vo.WebUserVO;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/13 15:45
 * @Description:
 */
public interface LoginService {

    /**
     * 登录
     * @param loginVO
     * @return
     * @throws Exception
     */
    void login(LoginVO loginVO) throws Exception;

    /**
     * 注册
     * @param webUserVO
     * @return
     * @throws Exception
     */
    Long register(WebUserVO webUserVO) throws Exception;

}
