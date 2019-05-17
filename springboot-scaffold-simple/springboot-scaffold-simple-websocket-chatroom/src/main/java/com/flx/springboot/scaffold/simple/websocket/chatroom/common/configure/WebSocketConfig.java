package com.flx.springboot.scaffold.simple.websocket.chatroom.common.configure;

import com.flx.springboot.scaffold.simple.websocket.chatroom.common.listener.RequestListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @Author Fenglixiong
 * @Create 2018.11.19 12:40
 * @Description
 **/
@Slf4j
@Configuration
public class WebSocketConfig implements InitializingBean {

    /**
     * 注册WebSocket配置
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("=========WebSocket启动了=========");
    }
}
