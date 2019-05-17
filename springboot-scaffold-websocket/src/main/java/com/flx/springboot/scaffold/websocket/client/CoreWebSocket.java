package com.flx.springboot.scaffold.websocket.client;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author Fenglixiong
 * @Create 2018.11.19 14:10
 * @Description 用的地方需要加入@ServerEndpoint("/websocket")
 * 其实每个CoreWebSocket就是一个客户端拥有自己的发送消息的方法,
 * 如果需要单独给某个客户端发送消息那么久需要拿到它的websocket调用自身
 * 的消息发送方法
 **/
@Slf4j
public class CoreWebSocket {

    //在线客户端数量
    private static int onlineCount = 0;

    //线程安全的Set，用来存放每个客户端对应的WebSocket
    private static CopyOnWriteArraySet<CoreWebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    //与某个客户端的会话，需要通过他给客户端发送数据
    private Session session;


    /**
     * 连接建立成功调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        log.info("【WebSocket连接】有新的连接加入，当前用户数："+getOnlineCount());
        sendMessageToAll("【WebSocket连接】连接成功！");
    }

    /**
     * 收到客户端消息时候调用的方法
     * @param session
     */
    @OnMessage
    public void onMessage(String message , Session session){

        log.info("【WebSocket消息】来自客户端的消息："+message);
        sendMessageToAll(message);

    }

    /**
     * 连接关闭时候调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnlineCount();
        log.info("【WebSocket关闭】有用户退出，当前用户数："+getOnlineCount());
    }

    /**
     * 发送错误时候调用
     * @param session
     */
    @OnError
    public void onError(Session session,Throwable e){
        log.error("【WebSocket错误】WebSocket发生错误！！！");
        e.printStackTrace();
    }

    /**
     * 群发消息
     * @param message
     */
    public void sendMessageToAll(String message){
        boolean result;
        int success = 0,fail = 0;
        for (CoreWebSocket clientWebSocket:webSocketSet){
            result = clientWebSocket.sendMessage(message);
            if(result){success ++;}else {fail++;}
        }
        log.info("【WebSocket群发】总共发送给：{}个人，成功{}人，失败{}人，消息体：{}",(success+fail),success,fail,message);
    }

    /**
     * 客户端自己的发送消息方法
     * @param message
     * @return
     */
    public boolean sendMessage(String message){
        boolean result = true;
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            result = false;
            log.error("【WebSocket错误】发送消息出现错误！！！");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 给指定客户端发送消息
     * @param coreWebSocket
     * @param message
     * @return
     */
    public boolean sendMessage(CoreWebSocket coreWebSocket, String message){
        return coreWebSocket.sendMessage(message);
    }

    private static synchronized int getOnlineCount(){
        return onlineCount;
    }

    private static synchronized void addOnlineCount(){
        CoreWebSocket.onlineCount ++;
    }

    private static synchronized void subOnlineCount(){
        CoreWebSocket.onlineCount --;
    }

}
