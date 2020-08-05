package com.flx.springboot.scaffold.system.dictionary.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import com.flx.springboot.scaffold.mybatis.plus.page.QueryAndPage;
import com.flx.springboot.scaffold.system.dictionary.vo.DictionaryVO;

import java.util.List;
import java.util.Map;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/24 16:33
 * @Description:
 */
public interface DictionaryService {

    /**
     * 新增
     *
     * @param dictionaryVO
     * @return
     * @throws Exception
     */
    Long add(DictionaryVO dictionaryVO) throws Exception;

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
     * @param dictionaryVO
     * @return
     * @throws Exception
     */
    Integer updateById(DictionaryVO dictionaryVO) throws Exception;

    /**
     * 状态修改
     *
     * @param dictionaryVOList
     * @param state
     * @return
     * @throws Exception
     */
    void stateChange(List<DictionaryVO> dictionaryVOList, State state) throws Exception;

    /**
     * 查询
     * @param id
     * @return
     * @throws Exception
     */
    DictionaryVO getById(Long id) throws Exception;

    /**
     * 模糊分页查询
     *
     * @param queryAndPage
     * @return
     * @throws Exception
     */
    IPage<DictionaryVO> queryAndPage(QueryAndPage queryAndPage) throws Exception;

    /**
     * 通过Map模糊查询
     *
     * @param query
     * @return
     * @throws Exception
     */
    List<DictionaryVO> query(Map<String, Object> query) throws Exception;

    /**
     * 通过对象模糊查询
     *
     * @param query
     * @return
     * @throws Exception
     */
    List<DictionaryVO> query(Object query) throws Exception;

    /**
     * 批量新增，错误回滚
     *
     * @param dictionaryVOList
     * @return
     * @throws Exception
     */
    int addWholeByList(List<DictionaryVO> dictionaryVOList) throws Exception;

    /**
     * 批量新增，错误回滚
     *
     * @param dictionaryVOList
     * @return
     * @throws Exception
     */
    int addByList(List<DictionaryVO> dictionaryVOList) throws Exception;

}
