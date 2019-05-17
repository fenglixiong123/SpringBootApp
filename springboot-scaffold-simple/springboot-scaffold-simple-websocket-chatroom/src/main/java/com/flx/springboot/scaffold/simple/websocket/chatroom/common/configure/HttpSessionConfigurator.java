package com.flx.springboot.scaffold.simple.websocket.chatroom.common.configure;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @Author Fenglixiong
 * @Create 2018.11.20 16:27
 * @Description  从websocket中获取用户session
 * 由于HTTP协议与websocket协议的不同，导致没法直接从websocket中获取协议，
 * 下面的类中写了获取HttpSession的代码，但是如果真的放出去执行，那么会报空指值异常，
 * 因为这个HttpSession并没有设置进去。需要我们自己来来设置HttpSession。
 * 这时候我们需要写一个监听器
 **/
public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        sec.getUserProperties().put(HttpSession.class.getName(),httpSession);
    }
}
