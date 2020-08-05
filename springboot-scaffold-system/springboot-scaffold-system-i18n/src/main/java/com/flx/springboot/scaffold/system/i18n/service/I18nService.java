package com.flx.springboot.scaffold.system.i18n.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import com.flx.springboot.scaffold.mybatis.plus.page.QueryAndPage;
import com.flx.springboot.scaffold.system.i18n.vo.I18nVO;

import java.util.List;
import java.util.Map;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/24 16:33
 * @Description:
 */
public interface I18nService {

    /**
     * 新增
     *
     * @param i18nVO
     * @return
     * @throws Exception
     */
    Long add(I18nVO i18nVO) throws Exception;

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
     * @param i18nVO
     * @return
     * @throws Exception
     */
    Integer updateById(I18nVO i18nVO) throws Exception;

    /**
     * 状态修改
     *
     * @param i18nVOList
     * @param state
     * @return
     * @throws Exception
     */
    void stateChange(List<I18nVO> i18nVOList, State state) throws Exception;

    /**
     * 查询
     * @param id
     * @return
     * @throws Exception
     */
    I18nVO getById(Long id) throws Exception;

    /**
     * 模糊分页查询
     *
     * @param queryAndPage
     * @return
     * @throws Exception
     */
    IPage<I18nVO> queryAndPage(QueryAndPage queryAndPage) throws Exception;

    /**
     * 通过Map模糊查询
     *
     * @param query
     * @return
     * @throws Exception
     */
    List<I18nVO> query(Map<String, Object> query) throws Exception;

    /**
     * 通过对象模糊查询
     *
     * @param query
     * @return
     * @throws Exception
     */
    List<I18nVO> query(Object query) throws Exception;

    /**
     * 批量新增，错误回滚
     *
     * @param i18nVOList
     * @return
     * @throws Exception
     */
    int addWholeByList(List<I18nVO> i18nVOList) throws Exception;

    /**
     * 批量新增，错误回滚
     *
     * @param i18nVOList
     * @return
     * @throws Exception
     */
    int addByList(List<I18nVO> i18nVOList) throws Exception;

}
