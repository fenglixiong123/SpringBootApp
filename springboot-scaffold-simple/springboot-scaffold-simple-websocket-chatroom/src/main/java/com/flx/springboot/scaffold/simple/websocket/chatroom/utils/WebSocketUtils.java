package com.flx.springboot.scaffold.simple.websocket.chatroom.utils;

import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.exception.element.BizException;
import com.flx.springboot.scaffold.simple.websocket.chatroom.entity.ChatUser;
import com.flx.springboot.scaffold.simple.websocket.chatroom.entity.ResultSend;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2018.11.21 1:12
 * @Description
 **/
@Slf4j
public class WebSocketUtils {

    public static Long parseLong(Object o){
        if(o==null){
            return null;
        }
        try {
            return Long.parseLong(o.toString());
        }catch (Exception e){
            log.error("解析Long出错！");
        }
        return null;
    }

    public static String sendMessageResult(String prefix, ResultSend result){
        if(result==null){
            return prefix+"send message done,no result !";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(prefix).append("send message result :").append(JsonUtils.toJsonMsg(result));
        return sb.toString();
    }

    public static void checkChatUserValid(ChatUser chatUser){
        if(chatUser==null){
            throw new BizException("用户为NULL");
        }else if(chatUser.getId()==null){
            throw new BizException("用户ID为NULL");
        }else if(chatUser.getUsername()==null){
            throw new BizException("用户名为NULL");
        }
    }


    public static List<String> sensitiveList = new ArrayList<>();

    static {
        sensitiveList.add("习近平");
        sensitiveList.add("毛泽东");
        sensitiveList.add("胡锦涛");
        sensitiveList.add("李克强");
        sensitiveList.add("共产党");
        sensitiveList.add("傻逼");
    }


}
