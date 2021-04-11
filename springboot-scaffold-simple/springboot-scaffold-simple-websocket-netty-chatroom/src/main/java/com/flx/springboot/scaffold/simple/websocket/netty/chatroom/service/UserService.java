package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import com.flx.springboot.scaffold.mybatis.plus.page.QueryAndPage;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.vo.WebUserVO;

import java.util.List;
import java.util.Map;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/24 16:33
 * @Description:
 */
public interface UserService {

    /**
     * 新增
     *
     * @param WebUserVO
     * @return
     * @throws Exception
     */
    Long add(WebUserVO WebUserVO) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    Integer deleteById(Long id) throws Exception;

    /**
     * 更新
     *
     * @param WebUserVO
     * @return
     * @throws Exception
     */
    Integer updateById(WebUserVO WebUserVO) throws Exception;

    /**
     * 状态修改
     *
     * @param WebUserVOList
     * @param state
     * @return
     * @throws Exception
     */
    void stateChange(List<WebUserVO> WebUserVOList, State state) throws Exception;

    /**
     * 查询
     * @param id
     * @return
     * @throws Exception
     */
    WebUserVO getById(Long id) throws Exception;

    /**
     * 模糊分页查询
     *
     * @param queryAndPage
     * @return
     * @throws Exception
     */
    IPage<WebUserVO> queryAndPage(QueryAndPage queryAndPage) throws Exception;

    /**
     * 通过Map模糊查询
     *
     * @param query
     * @return
     * @throws Exception
     */
    List<WebUserVO> query(Map<String, Object> query) throws Exception;

    /**
     * 通过对象模糊查询
     *
     * @param query
     * @return
     * @throws Exception
     */
    List<WebUserVO> query(Object query) throws Exception;

    /**
     * 批量新增，错误回滚
     *
     * @param WebUserVOList
     * @return
     * @throws Exception
     */
    int addWholeByList(List<WebUserVO> WebUserVOList) throws Exception;

    /**
     * 批量新增，错误回滚
     *
     * @param WebUserVOList
     * @return
     * @throws Exception
     */
    int addByList(List<WebUserVO> WebUserVOList) throws Exception;

}
