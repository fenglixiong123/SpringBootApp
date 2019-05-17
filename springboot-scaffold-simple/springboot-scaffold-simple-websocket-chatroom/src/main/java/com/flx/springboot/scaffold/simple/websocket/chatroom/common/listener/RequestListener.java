package com.flx.springboot.scaffold.simple.websocket.chatroom.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author Fenglixiong
 * @Create 2018.11.20 16:10
 * @Description 请求监听器 : 将所有request请求都携带上httpSession
 **/
@Slf4j
@WebListener
public class RequestListener implements ServletRequestListener,InitializingBean {

    /**
     * 将所有request请求都携带上httpSession
     * @param event
     */
    @Override
    public void requestInitialized(ServletRequestEvent event) {
        ((HttpServletRequest)event.getServletRequest()).getSession();
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("=============注册请求监听器成功=============");
    }
}
