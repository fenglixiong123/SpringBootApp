package com.flx.springboot.scaffold.simple.websocket.common;

import com.flx.springboot.scaffold.websocket.client.CoreWebSocket;
import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpoint;

/**
 * @Author Fenglixiong
 * @Create 2018.11.19 16:31
 * @Description
 **/
@Component
@ServerEndpoint("/websocket")
public class SimpleWebSocket extends CoreWebSocket {

}
