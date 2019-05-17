package com.flx.springboot.scaffold.simple.websocket.chatroom.client;

import com.flx.springboot.scaffold.simple.websocket.chatroom.common.enums.MessageEnum;
import com.flx.springboot.scaffold.simple.websocket.chatroom.common.enums.PushTypeEnum;
import com.flx.springboot.scaffold.simple.websocket.chatroom.entity.ChatUser;
import com.flx.springboot.scaffold.simple.websocket.chatroom.service.ChatMessageService;
import com.flx.springboot.scaffold.simple.websocket.chatroom.utils.WebSocketUtils;
import com.flx.springboot.scaffold.web.core.exception.element.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Author Fenglixiong
 * @Create 2018.11.20 23:39
 * @Description
 **/
@Slf4j
@Component
public class WebSocketService {

    @Autowired
    private ChatMessageService messageService;

    /**
     * 主要操作核心Session进行消息互相推送
     */
    @Autowired
    private WebSocketHandler webSocketHandler;

    /**
     * 群聊：发送欢迎消息到房间
     * @param roomId
     * @param chatUser
     * @param message
     */
    public void sendWelcomeMessageToRoom(ChatUser chatUser,Long roomId, String message){
        send(chatUser,MessageEnum.WELCOME_MSG,roomId,message,null,PushTypeEnum.GROUP_PUSH);
    }

    /**
     * 群聊：发送退出消息到房间
     * @param roomId
     * @param chatUser
     * @param message
     */
    public void sendQuitMessageToRoom(ChatUser chatUser,Long roomId, String message){
        send(chatUser,MessageEnum.QUIT_MSG,roomId,message,null,PushTypeEnum.GROUP_PUSH);
    }

    /**
     * 群聊：发送系统消息到房间
     * @param roomId
     * @param chatUser
     * @param message
     */
    public void sendSystemMessageToRoom(ChatUser chatUser,Long roomId, String message){
        send(chatUser,MessageEnum.SYSTEM_MSG,roomId,message,null,PushTypeEnum.GROUP_PUSH);
    }

    /**
     * 群聊：发送聊天消息到房间
     * @param roomId
     * @param chatUser
     * @param message
     */
    public void sendChatMessageToRoom(ChatUser chatUser,Long roomId, String message){
        send(chatUser,MessageEnum.CHAT_MSG,roomId,message,null,PushTypeEnum.GROUP_PUSH);
    }

    /**
     * 群聊：发送在线消息到房间
     * @param roomId
     * @param chatUser
     */
    public void sendLineMessageToRoom(ChatUser chatUser,Long roomId){
        send(chatUser,MessageEnum.LINE_MSG,roomId,"online",null,PushTypeEnum.GROUP_PUSH);
    }


    /**
     * 私聊：发送在线消息给对方
     * @param chatUser
     * @param userId
     * @param message
     */
    public void sendChatMessageToUser(ChatUser chatUser,Long userId,String message){
        send(chatUser,null,null,message,userId,PushTypeEnum.POINT_PUSH);
    }

    /**
     * 广播：发送在线消息给所有人
     * @param chatUser
     * @param message
     */
    public void sendChatMessageToBroad(ChatUser chatUser,String message){
        send(chatUser,null,null,message,null, PushTypeEnum.BROAD_PUSH);
    }


    /**
     * 核心发送并保存消息方法
     * @param chatUser
     * @param messageEnum
     * @param roomId
     * @param message
     * @param receiveId
     * @param pushTypeEnum
     */
    private void send(ChatUser chatUser,MessageEnum messageEnum,Long roomId,String message,Long receiveId,PushTypeEnum pushTypeEnum){

        int pushType = pushTypeEnum.getCode();
        //验证参数合法性
        checkValid(chatUser,roomId,message,pushType,receiveId);
        //去敏感词汇
        checkSensitive(message);
        //发送文本消息
        switch (pushType){
            case 1:
                if (messageEnum.equals(MessageEnum.LINE_MSG)) {
                    log.info("【WebSocketSend】用户{}向房间{}发送了一条人员消息：{}",chatUser.getUsername(),roomId);
                    webSocketHandler.sendOnlineToRoom(roomId, chatUser);
                } else {
                    log.info("【WebSocketSend】用户{}向房间{}发送了一条聊天消息：{}",chatUser.getUsername(),roomId,message);
                    webSocketHandler.sendMessageToRoom(messageEnum, roomId, chatUser, message);
                }
                break;
            case 2:
                log.info("【WebSocketSend】用户{}向用户{}发送了一条聊天消息：{}",chatUser.getUsername(),receiveId,message);
                webSocketHandler.sendMessageToUser(receiveId,chatUser,message);
                break;
            case 3:
                log.info("【WebSocketSend】用户{}向所有人发送了一条系统消息：{}",chatUser.getUsername(),message);
                webSocketHandler.sendMessageToAll(chatUser,message);
                break;
        }
        //保存文本消息
    }

    /**
     * 去敏感词汇
     */
    private void checkSensitive(String message) {
        if(StringUtils.isNotBlank(message)) {
            for (String sensitive : WebSocketUtils.sensitiveList) {
                if (message.contains(sensitive)) {
                    message = message.replace(sensitive, "***");
                }
            }
        }
    }

    /**
     * 检测参数合法性
     * @param chatUser
     * @param roomId
     * @param message
     * @param pushType
     * @param receiveId
     */
    private void checkValid(ChatUser chatUser, Long roomId, String message, int pushType, Long receiveId) {
        WebSocketUtils.checkChatUserValid(chatUser);
        switch (pushType){
            case 1:
                if(roomId==null){
                    throw new BizException("聊天室ID不能为空！");
                }
                break;
            case 2:
                if(receiveId==null){
                    throw new BizException("私聊接收方不能为空！");
                }
                break;
            case 3:
                if(StringUtils.isBlank(message)){
                    throw new BizException("广播不能发送空消息！");
                }
        }
    }

}
