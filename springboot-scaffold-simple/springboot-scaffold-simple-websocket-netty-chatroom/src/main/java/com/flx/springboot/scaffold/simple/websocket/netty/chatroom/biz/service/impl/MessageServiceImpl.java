package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.common.servlet.BeanUtils;
import com.flx.springboot.scaffold.mybatis.plus.entity.StateVO;
import com.flx.springboot.scaffold.mybatis.plus.page.PageConvert;
import com.flx.springboot.scaffold.mybatis.plus.page.QueryAndPage;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.entity.WebMessage;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.manager.MessageManager;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.service.MessageService;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.service.UserService;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.vo.WebMessageVO;
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
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageManager messageManager;

    private void convertVO(List<WebMessageVO> entityList){

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(WebMessageVO entityVO) throws Exception {
        return messageManager.add(BeanUtils.copyProperties(entityVO, WebMessage.class));
    }

    @Override
    public Integer delete(Long id) throws Exception {
        return messageManager.delete(id);
    }

    @Override
    public Integer update(WebMessageVO entityVO) throws Exception {
        return messageManager.update(BeanUtils.copyProperties(entityVO, WebMessage.class));
    }

    @Override
    public boolean updateState(StateVO stateVO) throws Exception {
        for (Long id : stateVO.getIds()){
            WebMessage entity = new WebMessage();
            entity.setId(id);
            entity.setState(stateVO.getState());
            entity.setUpdateUser(stateVO.getUpdateUser());
            messageManager.updateState(entity);
        }
        return true;
    }

    @Override
    public WebMessageVO get(Long id) throws Exception {
        WebMessage entity = messageManager.get(id);
        if(entity==null){
            return null;
        }
        return BeanUtils.copyProperties(entity, WebMessageVO.class);
    }

    @Override
    public IPage<WebMessageVO> queryPage(QueryAndPage queryAndPage) throws Exception {
        IPage<WebMessage> iPage = messageManager.queryPage(queryAndPage.getPageNum(),queryAndPage.getPageSize(),queryAndPage.getQuery());
        IPage<WebMessageVO> voPage = PageConvert.pageConvert(iPage, WebMessageVO.class);
        convertVO(voPage.getRecords());
        return voPage;
    }

    @Override
    public List<WebMessageVO> query(Map<String, Object> query) throws Exception {
        return messageManager.query(query).parallelStream().map(e -> BeanUtils.copyProperties(e, WebMessageVO.class)).collect(Collectors.toList());
    }

    @Override
    public List<WebMessageVO> queryAll(Map<String, Object> query) throws Exception {
        return messageManager.queryAll(query).parallelStream().map(e -> BeanUtils.copyProperties(e, WebMessageVO.class)).collect(Collectors.toList());
    }

    @Override
    public List<WebMessageVO> querySome(Map<String, Object> query,String[] columns) throws Exception {
        return messageManager.querySome(query,columns).parallelStream().map(e -> BeanUtils.copyProperties(e, WebMessageVO.class)).collect(Collectors.toList());
    }

    
}
