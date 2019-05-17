package com.flx.springboot.scaffold.simple.websocket.chatroom.client;

import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.simple.websocket.chatroom.common.configure.HttpSessionConfigurator;
import com.flx.springboot.scaffold.simple.websocket.chatroom.common.constants.WebContant;
import com.flx.springboot.scaffold.simple.websocket.chatroom.common.enums.ChatTypeEnum;
import com.flx.springboot.scaffold.simple.websocket.chatroom.common.enums.MessageEnum;
import com.flx.springboot.scaffold.simple.websocket.chatroom.entity.ChatUser;
import com.flx.springboot.scaffold.simple.websocket.chatroom.utils.WebSocketUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @Author Fenglixiong
 * @Create 2018.11.19 14:10
 * @Description 用的地方需要加入@ServerEndpoint("/websocket")
 * 其实每个CoreWebSocket就是一个客户端拥有自己的发送消息的方法,
 * 如果需要单独给某个客户端发送消息那么久需要拿到它的websocket调用自身
 * 的消息发送方法
 * 每次新用户连接都会新产生一个实例
 * 所以必须注册一个静态处理类，不然会报空指针异常
 **/
@Slf4j
@Component
@ServerEndpoint(value = "/websocket",configurator = HttpSessionConfigurator.class)
public class WebSocketClient {

    //注入一个静态的Handler
    private static WebSocketHandler webSocketHandler;

    //将HttpSession与WebSocketSession结合
    private HttpSession httpSession;

    //与某个客户端的会话，需要通过他给客户端发送数据
    @Getter
    private Session session;

    public WebSocketClient() {
        log.info(">>>>>>>>【NewWebSocketClient】<<<<<<<<");
    }

    @Autowired
    public void setWebSocketHandler(WebSocketHandler webSocketHandler){
        Assert.notNull(webSocketHandler,"WebSocketHandler Should Not Null!");
        WebSocketClient.webSocketHandler = webSocketHandler;
        log.info(">>>>>>>>Successful autowired a webSocketHandler Bean!");
    }

    /**
     * 连接建立成功调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session,EndpointConfig config){
        httpSession = (HttpSession)config.getUserProperties().get(HttpSession.class.getName());
        ChatUser chatUser = (ChatUser)httpSession.getAttribute(WebContant.WEBSOCKET_SESSION_KEY);
        log.info("【WebSocket连接】获取到用户信息为：{}",JsonUtils.toJsonMsg(chatUser));
        log.info("【WebSocket连接】当前在线用户数：" + webSocketHandler.getOnlineCount());
        if(chatUser!=null) {
            this.session = session;
        }else {
            //用户未登陆
            closeSession(session,"user no login");
        }
    }

    /**
     * 收到客户端消息时候调用的方法
     * @param session
     */
    @OnMessage
    public void onMessage(String message , Session session){
        log.info("【WebSocket消息】来自客户端的消息："+message);
        ChatUser chatUser = (ChatUser)httpSession.getAttribute(WebContant.WEBSOCKET_SESSION_KEY);
        if(chatUser==null){
            log.error("【WebSocket消息】user info is null！");
            closeSession(session,"user info is null");
            return;
        }
        if(message.startsWith(ChatTypeEnum.GROUP_CHAT.getName())){
            //群聊
            String[] msgArr = message.split("\\s+");
            if(msgArr.length == 2){
                Long roomId = WebSocketUtils.parseLong(msgArr[1]);
                if(roomId==null){
                    log.error("【WebSocket群聊连接】parse room id is null !");
                    closeSession(session,"room id is null");
                    return;
                }
                log.info("【WebSocket群聊连接】room id : {}",roomId);
                //连接成功之后将房间号存储在session中
                session.getUserProperties().put(WebContant.WEBSOCKET_ROOM_ID,roomId);
                log.info("【WebSocket群聊连接】begin to add user {} to room {}:" ,chatUser.getId(),roomId);
                webSocketHandler.addUser(chatUser, roomId, session);
                log.info("【WebSocket群聊连接】当前在线用户数："+webSocketHandler.getOnlineCount());
            }else{
                log.error("【WebSocket群聊连接】未传房间号错误" + message + "\t" + JsonUtils.toJsonMsg(chatUser));
            }
        }else if(message.startsWith(ChatTypeEnum.PRIVATE_CHAT.getName())){
            //私聊
            log.info("【WebSocket私聊连接】user id : {}",chatUser.getId());
            webSocketHandler.addUser(chatUser,null,session);
            log.info("【WebSocket私聊连接】当前在线用户数："+webSocketHandler.getOnlineCount());
        }else {
            //Test
            webSocketHandler.sendMessageToRoom(MessageEnum.CHAT_MSG,1L,chatUser,message);
        }

    }

    /**
     * 连接关闭时候调用的方法
     */
    @OnClose
    public void onClose(Session session){
        log.info("【WebSocket关闭】当前在线用户数："+webSocketHandler.getOnlineCount());
        ChatUser chatUser = (ChatUser)httpSession.getAttribute(WebContant.WEBSOCKET_SESSION_KEY);
        if(chatUser==null){
            return;
        }
        Long roomId = WebSocketUtils.parseLong(session.getUserProperties().get(WebContant.WEBSOCKET_ROOM_ID));
        log.info("【WebSocket关闭】parse room id :{}",roomId);
        webSocketHandler.removeUser(chatUser,roomId,session);
        log.info("【WebSocket关闭】当前在线用户数："+webSocketHandler.getOnlineCount());

    }

    /**
     * 发送错误时候调用
     */
    @OnError
    public void onError(Throwable e){
        log.error("【WebSocket错误】WebSocket发生错误！！！");
        e.printStackTrace();
    }

    /**
     * 关闭Session
     * @param session
     */
    private void closeSession(Session session,String reason){
        if(session!=null){
            try {
                session.close();
                log.error("【WebSocketClose】session closed !!!\t{}",reason);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("【WebSocketClose】close session happen error !!!");
            }
        }
    }

}
