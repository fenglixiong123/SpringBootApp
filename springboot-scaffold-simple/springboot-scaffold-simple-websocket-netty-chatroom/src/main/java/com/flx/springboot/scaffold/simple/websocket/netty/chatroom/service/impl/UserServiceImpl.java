package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.common.servlet.BeanUtils;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import com.flx.springboot.scaffold.mybatis.plus.page.PageConvert;
import com.flx.springboot.scaffold.mybatis.plus.page.QueryAndPage;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.entity.WebUser;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.manager.UserManager;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.service.UserService;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.vo.WebUserVO;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserManager userManager;

    private void convertVO(List<WebUserVO> webUserVOList){

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(WebUserVO webUserVO) throws Exception {
        return userManager.add(BeanUtils.copyProperties(webUserVO, WebUser.class));
    }

    @Override
    public Integer deleteById(Long id) throws Exception {
        return userManager.deleteById(id);
    }

    @Override
    public Integer updateById(WebUserVO webUserVO) throws Exception {
        return userManager.updateById(BeanUtils.copyProperties(webUserVO, WebUser.class));
    }

    @Override
    public void stateChange(List<WebUserVO> webUserVOList, State state) throws Exception {

    }

    @Override
    public WebUserVO getById(Long id) throws Exception {
        return BeanUtils.copyProperties(userManager.getById(id), WebUserVO.class);
    }

    @Override
    public IPage<WebUserVO> queryAndPage(QueryAndPage queryAndPage) throws Exception {
        IPage<WebUser> iPage = userManager.queryAndPage(queryAndPage.getPageNum(),queryAndPage.getPageSize(),queryAndPage.getQuery());
        IPage<WebUserVO> voiPage = PageConvert.pageConvert(iPage, WebUserVO.class);
        convertVO(voiPage.getRecords());
        return voiPage;
    }

    @Override
    public List<WebUserVO> query(Map<String, Object> query) throws Exception {
        return userManager.queryWebUser(query).parallelStream().map(e -> BeanUtils.copyProperties(e, WebUserVO.class)).collect(Collectors.toList());
    }

    @Override
    public List<WebUserVO> query(Object query) throws Exception {
        return userManager.queryWebUser(query).parallelStream().map(e -> BeanUtils.copyProperties(e, WebUserVO.class)).collect(Collectors.toList());
    }

    @Override
    public int addWholeByList(List<WebUserVO> webUserVOList) throws Exception {
        for (WebUserVO webUserVO:webUserVOList){
            try {
                add(webUserVO);
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
        return 1;
    }

    @Override
    public int addByList(List<WebUserVO> webUserVOList) throws Exception {
        return userManager.addByList(webUserVOList.parallelStream().map(e -> BeanUtils.copyProperties(e, WebUser.class)).collect(Collectors.toList()));
    }
}
