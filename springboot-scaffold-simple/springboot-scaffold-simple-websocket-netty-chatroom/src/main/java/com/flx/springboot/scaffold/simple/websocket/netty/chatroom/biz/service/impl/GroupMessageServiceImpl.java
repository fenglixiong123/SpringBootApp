package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.common.servlet.BeanUtils;
import com.flx.springboot.scaffold.mybatis.plus.entity.StateVO;
import com.flx.springboot.scaffold.mybatis.plus.page.PageConvert;
import com.flx.springboot.scaffold.mybatis.plus.page.QueryAndPage;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.entity.WebGroupMessage;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.manager.GroupMessageManager;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.manager.UserManager;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.service.GroupMessageService;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.service.UserService;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.vo.WebGroupMessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/24 16:34
 * @Description:
 */
@Slf4j
@Service
public class GroupMessageServiceImpl implements GroupMessageService {

    @Autowired
    private GroupMessageManager groupMessageManager;

    private void convertVO(List<WebGroupMessageVO> entityList){

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(WebGroupMessageVO entityVO) throws Exception {
        return groupMessageManager.add(BeanUtils.copyProperties(entityVO, WebGroupMessage.class));
    }

    @Override
    public Integer delete(Long id) throws Exception {
        return groupMessageManager.delete(id);
    }

    @Override
    public Integer update(WebGroupMessageVO entityVO) throws Exception {
        return groupMessageManager.update(BeanUtils.copyProperties(entityVO, WebGroupMessage.class));
    }

    @Override
    public boolean updateState(StateVO stateVO) throws Exception {
        for (Long id : stateVO.getIds()){
            WebGroupMessage entity = new WebGroupMessage();
            entity.setId(id);
            entity.setState(stateVO.getState());
            entity.setUpdateUser(stateVO.getUpdateUser());
            groupMessageManager.updateState(entity);
        }
        return true;
    }

    @Override
    public WebGroupMessageVO get(Long id) throws Exception {
        WebGroupMessage entity = groupMessageManager.get(id);
        if(entity==null){
            return null;
        }
        return BeanUtils.copyProperties(entity, WebGroupMessageVO.class);
    }

    @Override
    public IPage<WebGroupMessageVO> queryPage(QueryAndPage queryAndPage) throws Exception {
        IPage<WebGroupMessage> iPage = groupMessageManager.queryPage(queryAndPage.getPageNum(),queryAndPage.getPageSize(),queryAndPage.getQuery());
        IPage<WebGroupMessageVO> voPage = PageConvert.pageConvert(iPage, WebGroupMessageVO.class);
        convertVO(voPage.getRecords());
        return voPage;
    }

    @Override
    public List<WebGroupMessageVO> query(Map<String, Object> query) throws Exception {
        return groupMessageManager.query(query).parallelStream().map(e -> BeanUtils.copyProperties(e, WebGroupMessageVO.class)).collect(Collectors.toList());
    }

    @Override
    public List<WebGroupMessageVO> queryAll(Map<String, Object> query) throws Exception {
        return groupMessageManager.queryAll(query).parallelStream().map(e -> BeanUtils.copyProperties(e, WebGroupMessageVO.class)).collect(Collectors.toList());
    }

    @Override
    public List<WebGroupMessageVO> querySome(Map<String, Object> query,String[] columns) throws Exception {
        return groupMessageManager.querySome(query,columns).parallelStream().map(e -> BeanUtils.copyProperties(e, WebGroupMessageVO.class)).collect(Collectors.toList());
    }

    
}
