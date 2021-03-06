package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.common.servlet.BeanUtils;
import com.flx.springboot.scaffold.mybatis.plus.entity.StateVO;
import com.flx.springboot.scaffold.mybatis.plus.page.PageConvert;
import com.flx.springboot.scaffold.mybatis.plus.page.QueryAndPage;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.entity.WebGroup;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.manager.GroupManager;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.manager.UserManager;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.service.GroupService;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.service.UserService;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.vo.WebGroupVO;
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
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupManager groupManager;

    private void convertVO(List<WebGroupVO> entityList){

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(WebGroupVO entityVO) throws Exception {
        return groupManager.add(BeanUtils.copyProperties(entityVO, WebGroup.class));
    }

    @Override
    public Integer delete(Long id) throws Exception {
        return groupManager.delete(id);
    }

    @Override
    public Integer update(WebGroupVO entityVO) throws Exception {
        return groupManager.update(BeanUtils.copyProperties(entityVO, WebGroup.class));
    }

    @Override
    public boolean updateState(StateVO stateVO) throws Exception {
        for (Long id : stateVO.getIds()){
            WebGroup entity = new WebGroup();
            entity.setId(id);
            entity.setState(stateVO.getState());
            entity.setUpdateUser(stateVO.getUpdateUser());
            groupManager.updateState(entity);
        }
        return true;
    }

    @Override
    public WebGroupVO get(Long id) throws Exception {
        WebGroup entity = groupManager.get(id);
        if(entity==null){
            return null;
        }
        return BeanUtils.copyProperties(entity, WebGroupVO.class);
    }

    @Override
    public IPage<WebGroupVO> queryPage(QueryAndPage queryAndPage) throws Exception {
        IPage<WebGroup> iPage = groupManager.queryPage(queryAndPage.getPageNum(),queryAndPage.getPageSize(),queryAndPage.getQuery());
        IPage<WebGroupVO> voPage = PageConvert.pageConvert(iPage, WebGroupVO.class);
        convertVO(voPage.getRecords());
        return voPage;
    }

    @Override
    public List<WebGroupVO> query(Map<String, Object> query) throws Exception {
        return groupManager.query(query).parallelStream().map(e -> BeanUtils.copyProperties(e, WebGroupVO.class)).collect(Collectors.toList());
    }

    @Override
    public List<WebGroupVO> queryAll(Map<String, Object> query) throws Exception {
        return groupManager.queryAll(query).parallelStream().map(e -> BeanUtils.copyProperties(e, WebGroupVO.class)).collect(Collectors.toList());
    }

    @Override
    public List<WebGroupVO> querySome(Map<String, Object> query,String[] columns) throws Exception {
        return groupManager.querySome(query,columns).parallelStream().map(e -> BeanUtils.copyProperties(e, WebGroupVO.class)).collect(Collectors.toList());
    }

    
}
