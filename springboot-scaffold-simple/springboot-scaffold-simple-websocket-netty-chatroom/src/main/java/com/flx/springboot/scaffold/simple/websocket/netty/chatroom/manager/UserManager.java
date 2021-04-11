package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseManager;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.dao.WebUserDao;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.entity.WebUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/24 17:24
 * @Description:
 */
@Service
public class UserManager extends BaseManager<WebUser, WebUserDao> {

    public Object getById(Long id) throws Exception {
        return super.get(id);
    }

    public IPage<WebUser> queryAndPage(Integer pageNum, Integer pageSize, Map<String, Object> query) throws Exception {
        return super.queryPage(pageNum, pageSize, query);
    }


    public IPage<WebUser> queryAndPage(Integer pageNum, Integer pageSize, Object query) throws Exception {
        return super.queryPage(pageNum, pageSize, query);
    }

    public List<WebUser> queryWebUser(Map<String, Object> query) throws Exception {
        return super.query(query);
    }

    public List<WebUser> queryWebUserAllColumns(Map<String, Object> query) throws Exception {
        return super.queryAllColumns(query);
    }

    public List<WebUser> queryWebUser(Object query) throws Exception {
        return super.query(query);
    }

    public Long add(WebUser webUser) throws Exception {
        return super.add(webUser);
    }

    public Integer addByList(List<WebUser> webUserList) throws Exception {
        return super.add(webUserList);
    }

    public Integer updateById(WebUser WebUser) throws Exception {
        return super.update(WebUser);
    }

    public Integer updateNullById(WebUser WebUser) throws Exception {
        return super.update(WebUser);
    }

    public Integer updateByCode(WebUser WebUser) throws Exception {
        return super.update(WebUser, WebUser.getUserName(), "userName");
    }

    public Integer updateNullByCode(WebUser WebUser) throws Exception {
        return super.update(WebUser,  WebUser.getUserName(), "userName");
    }

    public Integer deleteById(Long id) throws Exception {
        return super.delete(id);
    }

    public boolean isExist(String userName) throws Exception {
        QueryWrapper<WebUser> queryWrapper = getConditionBuilder().query("userName", userName).build(false);
        return dao.selectCount(queryWrapper)>0;
    }

    public boolean isExist(Long id) throws Exception {
        QueryWrapper<WebUser> queryWrapper = getConditionBuilder().query("id", id).build(false);
        return dao.selectCount(queryWrapper)>0;
    }


}
