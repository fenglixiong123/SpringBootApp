package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.common.servlet.BeanUtils;
import com.flx.springboot.scaffold.mybatis.plus.entity.StateVO;
import com.flx.springboot.scaffold.mybatis.plus.page.PageConvert;
import com.flx.springboot.scaffold.mybatis.plus.page.QueryAndPage;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.entity.WebLabel;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.manager.LabelManager;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.service.LabelService;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.service.UserService;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.vo.WebLabelVO;
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
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelManager labelManager;

    private void convertVO(List<WebLabelVO> entityList){

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(WebLabelVO entityVO) throws Exception {
        return labelManager.add(BeanUtils.copyProperties(entityVO, WebLabel.class));
    }

    @Override
    public Integer delete(Long id) throws Exception {
        return labelManager.delete(id);
    }

    @Override
    public Integer update(WebLabelVO entityVO) throws Exception {
        return labelManager.update(BeanUtils.copyProperties(entityVO, WebLabel.class));
    }

    @Override
    public boolean updateState(StateVO stateVO) throws Exception {
        for (Long id : stateVO.getIds()){
            WebLabel entity = new WebLabel();
            entity.setId(id);
            entity.setState(stateVO.getState());
            entity.setUpdateUser(stateVO.getUpdateUser());
            labelManager.updateState(entity);
        }
        return true;
    }

    @Override
    public WebLabelVO get(Long id) throws Exception {
        WebLabel entity = labelManager.get(id);
        if(entity==null){
            return null;
        }
        return BeanUtils.copyProperties(entity, WebLabelVO.class);
    }

    @Override
    public IPage<WebLabelVO> queryPage(QueryAndPage queryAndPage) throws Exception {
        IPage<WebLabel> iPage = labelManager.queryPage(queryAndPage.getPageNum(),queryAndPage.getPageSize(),queryAndPage.getQuery());
        IPage<WebLabelVO> voPage = PageConvert.pageConvert(iPage, WebLabelVO.class);
        convertVO(voPage.getRecords());
        return voPage;
    }

    @Override
    public List<WebLabelVO> query(Map<String, Object> query) throws Exception {
        return labelManager.query(query).parallelStream().map(e -> BeanUtils.copyProperties(e, WebLabelVO.class)).collect(Collectors.toList());
    }

    @Override
    public List<WebLabelVO> queryAll(Map<String, Object> query) throws Exception {
        return labelManager.queryAll(query).parallelStream().map(e -> BeanUtils.copyProperties(e, WebLabelVO.class)).collect(Collectors.toList());
    }

    @Override
    public List<WebLabelVO> querySome(Map<String, Object> query,String[] columns) throws Exception {
        return labelManager.querySome(query,columns).parallelStream().map(e -> BeanUtils.copyProperties(e, WebLabelVO.class)).collect(Collectors.toList());
    }

    
}
