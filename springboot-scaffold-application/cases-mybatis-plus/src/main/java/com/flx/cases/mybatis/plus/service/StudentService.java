package com.flx.cases.mybatis.plus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import com.flx.springboot.scaffold.mybatis.plus.page.QueryAndPage;
import com.flx.cases.mybatis.plus.vo.StudentVO;

import java.util.List;
import java.util.Map;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/24 16:33
 * @Description:
 */
public interface StudentService {

    int count();

    /**
     * 新增
     *
     * @param studentVO
     * @return
     * @throws Exception
     */
    Long add(StudentVO studentVO) throws Exception;

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
     * @param studentVO
     * @return
     * @throws Exception
     */
    Integer updateById(StudentVO studentVO) throws Exception;

    /**
     * 状态修改
     *
     * @param studentVOList
     * @param state
     * @return
     * @throws Exception
     */
    void stateChange(List<StudentVO> studentVOList, State state) throws Exception;

    /**
     * 查询
     * @param id
     * @return
     * @throws Exception
     */
    StudentVO getById(Long id) throws Exception;

    /**
     * 模糊分页查询
     *
     * @param queryAndPage
     * @return
     * @throws Exception
     */
    IPage<StudentVO> queryAndPage(QueryAndPage queryAndPage) throws Exception;

    /**
     * 通过Map模糊查询
     *
     * @param query
     * @return
     * @throws Exception
     */
    List<StudentVO> query(Map<String, Object> query) throws Exception;

    /**
     * 通过对象模糊查询
     *
     * @param query
     * @return
     * @throws Exception
     */
    List<StudentVO> query(Object query) throws Exception;

    /**
     * 批量新增，错误回滚
     *
     * @param studentVOList
     * @return
     * @throws Exception
     */
    int addWholeByList(List<StudentVO> studentVOList) throws Exception;

    /**
     * 批量新增，错误回滚
     *
     * @param studentVOList
     * @return
     * @throws Exception
     */
    int addByList(List<StudentVO> studentVOList) throws Exception;

}
