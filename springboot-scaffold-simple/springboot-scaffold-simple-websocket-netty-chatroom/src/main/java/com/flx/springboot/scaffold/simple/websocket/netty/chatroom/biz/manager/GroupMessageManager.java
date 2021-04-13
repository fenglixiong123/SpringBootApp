package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseManager;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.dao.WebGroupMessageDao;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.entity.WebGroupMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/24 17:24
 * @Description: 基础增删改查功能
 */
@Service
public class GroupMessageManager extends BaseManager<WebGroupMessage, WebGroupMessageDao> {

    public WebGroupMessage get(Long id) throws Exception {
        return (WebGroupMessage)super.get(id);
    }

    public IPage<WebGroupMessage> queryPage(Integer pageNum, Integer pageSize, Map<String, Object> query) throws Exception {
        return super.queryPage(pageNum, pageSize, query);
    }


    public IPage<WebGroupMessage> queryPage(Integer pageNum, Integer pageSize, Object query) throws Exception {
        return super.queryPage(pageNum, pageSize, query);
    }

    public List<WebGroupMessage> query(Object query) throws Exception {
        return super.query(query);
    }

    public List<WebGroupMessage> query(Map<String, Object> query) throws Exception {
        return super.query(query);
    }

    public List<WebGroupMessage> querySome(Object query,String[] columns) throws Exception {
        return super.querySome(query,columns);
    }

    public List<WebGroupMessage> querySome(Map<String, Object> query,String[] columns) throws Exception {
        return super.querySome(query,columns);
    }

    public List<WebGroupMessage> queryAll(Object query) throws Exception {
        return super.queryAll(query);
    }

    public List<WebGroupMessage> queryAll(Map<String, Object> query) throws Exception {
        return super.queryAll(query);
    }

    public Long add(WebGroupMessage entity) throws Exception {
        return super.add(entity);
    }

    public Integer add(List<WebGroupMessage> entityList) throws Exception {
        return super.add(entityList);
    }

    public Integer update(WebGroupMessage entity) throws Exception {
        return super.update(entity);
    }

    public Integer updateNull(WebGroupMessage entity) throws Exception {
        return super.updateNull(entity);
    }

    public Integer update(WebGroupMessage entity,String keyCode,String keyValue) throws Exception {
        return super.update(entity,keyCode,keyValue);
    }

    public Integer updateNull(WebGroupMessage entity,String keyCode,String keyValue) throws Exception {
        return super.updateNull(entity,keyCode,keyValue);
    }

    public Integer updateState(WebGroupMessage entity) throws Exception {
        if(entity.getState().equals(State.deleted.name())){
            return super.delete(entity.getId());
        }
        return super.update(entity);
    }

    public Integer delete(Long id) throws Exception {
        return super.delete(id);
    }

    public boolean isExist(String keyCode,String keyValue) throws Exception {
        return super.isExist(keyCode,keyValue);
    }

    public boolean isExist(Long id) throws Exception {
        return super.isExist(id);
    }


}
