package com.flx.springboot.scaffold.simple.websocket.chatroom.client;

import com.flx.springboot.scaffold.common.utils.CommonUtils;
import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.simple.websocket.chatroom.common.enums.MessageEnum;
import com.flx.springboot.scaffold.simple.websocket.chatroom.entity.ChatUser;
import com.flx.springboot.scaffold.simple.websocket.chatroom.entity.PayLoad;
import com.flx.springboot.scaffold.simple.websocket.chatroom.entity.ResultSend;
import com.flx.springboot.scaffold.simple.websocket.chatroom.utils.WebSocketUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author Fenglixiong
 * @Create 2018.11.20 23:06
 * @Description WebSocket处理类 单例 内存中只有一份
 **/
@Slf4j
@Component
public class WebSocketHandler {


    /**
     * 存储所有的客户端信息
     */
    private Set<Session> clientSet = new CopyOnWriteArraySet<>();
    /**
     * 用户Map存储了，UserID——>WebSocketSessions
     * 每个用户可能存在多个设备
     */
    private Map<Long, CopyOnWriteArraySet<Session>> userMap = new ConcurrentHashMap<>();
    /**
     * 房间Map存储了，RoomID——>UserListSession
     * 每个房间存在多个用户
     */
    private Map<Long, CopyOnWriteArraySet<Session>> roomMap = new ConcurrentHashMap<>();


    @Autowired
    private WebSocketService webSocketService;

    public WebSocketHandler() {
        log.info(">>>>>>>>【NewWebSocketHandler】<<<<<<<<");
    }


    /**
     * 添加一个用户
     * @param roomId
     * @param session
     */
    public void addUser(ChatUser chatUser,Long roomId, Session session){
        log.info("【WebSocketAdd】ready to add client userId:{},userName:{},roomId:{}",chatUser.getId(),chatUser.getUsername(),roomId);
        clientSet.add(session);
        log.info("【WebSocketAdd】add to clientSet!");
        addToUserMapHandler(chatUser,session);
        if(roomId!=null) {
            boolean add = addToRoomMapHandler(chatUser, roomId, session);
            //房间广播：有新用户加入
            if(add){
                webSocketService.sendWelcomeMessageToRoom(chatUser,roomId,"欢迎用户["+chatUser.getUsername()+"]加入房间");
                webSocketService.sendLineMessageToRoom(chatUser,roomId);
//                sendMessageToRoom(MessageEnum.WELCOME_MSG,roomId,chatUser,"欢迎用户"+chatUser.getUsername()+"加入房间");
//                sendOnlineToRoom(roomId,chatUser);
            }
        }
    }

    public void removeUser(ChatUser chatUser,Long roomId,Session oldSession){
        log.info("【WebSocketRemove】ready to remove client userId:{},userName:{},roomId:{}",chatUser.getId(),chatUser.getUsername(),roomId);
        Long userId = chatUser.getId();
        clientSet.remove(oldSession);
        log.info("【WebSocketRemove】removed from clientSet!");
        removeMapHandler(userId,oldSession,userMap,"group");
        if(roomId!=null) {
            boolean removed = removeMapHandler(roomId, oldSession, roomMap,"room");
            boolean remained = roomMap.get(roomId).size()>0;
            //房间广播：用户退出群聊
            if(removed && remained) {
                webSocketService.sendQuitMessageToRoom(chatUser,roomId,"用户[" + chatUser.getUsername() + "]退出群聊！");
                webSocketService.sendLineMessageToRoom(chatUser,roomId);
//                sendMessageToRoom(MessageEnum.QUIT_MSG, roomId, chatUser, "用户" + chatUser.getUsername() + "退出群聊！");
//                sendOnlineToRoom(roomId,chatUser);
            }
        }
    }


    private boolean addToUserMapHandler(ChatUser chatUser,Session newSession){
        boolean add = false;
        Long userId = chatUser.getId();
        //判断是否需要创建用户组
        if(userMap.containsKey(userId)){
            log.info("【WebSocketAdd】the user group exist :{}",userId);
        }else {
            userMap.put(userId,new CopyOnWriteArraySet<>());
            log.info("【WebSocketAdd】create a new user group :{}",userId);
        }
        //判断是否需要添加用户
        if(!isClientExist(newSession,userMap.get(userId))) {
            add = userMap.get(userId).add(newSession);
            log.info("【WebSocketAdd】put a new user to group :{}",userId);
        }else {
            log.info("【WebSocketAdd】user {} already in group ",userId);
        }
        return add;
    }

    private boolean addToRoomMapHandler(ChatUser chatUser,Long roomId, Session newSession){
        boolean add = false;
        //判断是否需要创建房间
        if(roomMap.containsKey(roomId)){
            log.info("【WebSocketAdd】the room exist : {}",roomId);
        }else {
            roomMap.put(roomId,new CopyOnWriteArraySet<>());
            log.info("【WebSocketAdd】create a new room : {}",roomId);
        }

        //判断是否需要添加到房间
        if(!isClientExist(newSession,roomMap.get(roomId))){
            add = roomMap.get(roomId).add(newSession);
            log.info("【WebSocketAdd】put a new user to room {}",roomId);
        }else {
            log.info("【WebSocketAdd】user {} already in room ",chatUser.getId());
        }
        return add;
    }

    /**
     * 判断某个集合是否存在某个用户
     * @param newSession
     * @return
     */
    private boolean isClientExist(Session newSession, CopyOnWriteArraySet<Session> sessions){
        if(CommonUtils.isEmpty(sessions)){
            return false;
        }
        Iterator<Session> it = sessions.iterator();
        if(it.hasNext()){
            Session session = it.next();
            if(!session.isOpen()){
                sessions.remove(session);
            }else if(session.getId().equals(newSession.getId())){
                return true;
            }
        }
        return false;
    }

    private boolean removeMapHandler(Long id,Session oldSession,Map<Long,CopyOnWriteArraySet<Session>> dataMap,String desc){
        boolean removed = false;
        if(CommonUtils.isEmpty(dataMap)){
            log.info("【WebSocketRemove】set is null,no need to remove !");
            return false;
        }
        CopyOnWriteArraySet<Session> sessions = dataMap.get(id);
        if(CommonUtils.isNotEmpty(sessions)) {
            removed = sessions.removeIf(session -> session.getId().equals(oldSession.getId()));
            log.info("【WebSocketRemove】removed from {} sessionId:{}!",desc,oldSession.getId());
            //如果用户已经没有了就删除UserGroup
            if(sessions.isEmpty()){
                userMap.remove(id);
                log.info("【WebSocketRemove】removed {} !",desc);
            }
        }
        return removed;
    }


    /**
     * 客户端自己的核心发送消息方法
     * @param payLoad
     * @return
     */
    public boolean sendMessage(Session session, PayLoad payLoad){
        try {
            session.getBasicRemote().sendText(JsonUtils.toJsonMsg(payLoad));
            return true;
        } catch (IOException e) {
            log.error("【WebSocketMessageSendError】发送消息出现错误！！！");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 给指定客户端发送消息 P2P
     * @param userId
     * @param chatUser
     * @param message
     * @return
     */
    public void sendMessageToUser(Long userId, ChatUser chatUser,String message){
        log.info("【WebSocketUserMessage】ready to send message to user : {}",userId);
        CopyOnWriteArraySet<Session> sessions = userMap.get(userId);
        ResultSend result = sendMessage(sessions, new PayLoad(MessageEnum.CHAT_MSG,chatUser,message));
        log.info(WebSocketUtils.sendMessageResult("【WebSocketUserMessage】",result));
    }

    /**
     * 向房间所有用户发送消息 p2R
     * @param roomId
     * @param payLoad
     */
    public void sendMessageToRoom(Long roomId, PayLoad payLoad){
        log.info("【WebSocketRoomMessage】start to send message to room : {}",roomId);
        CopyOnWriteArraySet<Session> sessions = roomMap.get(roomId);
        ResultSend result = sendMessage(sessions,payLoad);
        log.info(WebSocketUtils.sendMessageResult("【WebSocketRoomMessage】",result));
    }

    /**
     * 向房间所有用户发送消息 P2R
     * @param roomId
     */
    public void sendMessageToRoom(MessageEnum messageEnum, Long roomId, ChatUser chatUser,String message){
        sendMessageToRoom(roomId,new PayLoad(messageEnum,chatUser,message));
    }

    /**
     * 给全体在线人员发送消息 p2A
     * @param chatUser
     */
    public void sendMessageToAll(ChatUser chatUser,String message){
        log.info("【WebSocketALLMessage】ready to send message to all !!!");
        ResultSend result = sendMessage(clientSet,new PayLoad(MessageEnum.BROAD_MSG,chatUser,message));
        log.info(WebSocketUtils.sendMessageResult("【WebSocketALLMessage】",result));

    }

    /**
     * 发送人员刷新消息给房间 p2R
     * @param roomId
     */
    public void sendOnlineToRoom(Long roomId,ChatUser chatUser){
        log.info("【WebSocketLineMessage】ready to send line num to room : {},userId:{},userName{}",roomId,chatUser.getId(),chatUser.getUsername());
        CopyOnWriteArraySet<Session> sessions = roomMap.get(roomId);
        ResultSend result = sendMessage(sessions,new PayLoad(MessageEnum.LINE_MSG,chatUser,clientSet.size()+""));
        log.info(WebSocketUtils.sendMessageResult("【WebSocketLineMessage】",result));
    }

    private ResultSend sendMessage(Set<Session> sessions, PayLoad payLoad){
        if(CommonUtils.isEmpty(sessions)){
            return new ResultSend();
        }
        Iterator<Session> it = sessions.iterator();
        int success = 0,fail = 0;
        while (it.hasNext()) {
            Session session = it.next();
            if(session.isOpen()){
                if(sendMessage(session,payLoad)){
                    success ++ ;
                }else {
                    fail ++ ;
                }
            }
        }
        return new ResultSend(success,fail,success+fail);
    }

    public int getOnlineCount(){
        return this.clientSet.size();
    }


}
