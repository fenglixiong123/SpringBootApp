package com.flx.springboot.scaffold.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.common.servlet.BeanUtils;
import com.flx.springboot.scaffold.mybatis.plus.dto.StudentDTO;
import com.flx.springboot.scaffold.mybatis.plus.entity.StudentDO;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import com.flx.springboot.scaffold.mybatis.plus.manager.StudentManager;
import com.flx.springboot.scaffold.mybatis.plus.page.PageConvert;
import com.flx.springboot.scaffold.mybatis.plus.page.QueryAndPage;
import com.flx.springboot.scaffold.mybatis.plus.service.StudentService;
import com.flx.springboot.scaffold.mybatis.plus.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/24 16:34
 * @Description:
 */
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentManager studentManager;

    private void convertVO(List<StudentVO> studentVOS){

    }

    @Override
    public int count() {
        return studentManager.count();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(StudentVO studentVO) throws Exception {
        StudentDO studentDO = BeanUtils.copyProperties(studentVO, StudentDO.class);
        return studentManager.add(studentDO);
    }

    @Override
    public Integer deleteById(Long id) throws Exception {
        return studentManager.deleteById(id);
    }

    @Override
    public Integer updateById(StudentVO studentVO) throws Exception {
        StudentDO studentDO = BeanUtils.copyProperties(studentVO, StudentDO.class);
        return studentManager.updateById(studentDO);
    }

    @Override
    public void stateChange(List<StudentVO> StudentVOList, State state) throws Exception {

    }

    @Override
    public StudentVO getById(Long id) throws Exception {
        StudentDTO studentDTO = studentManager.getById(id);
        return BeanUtils.copyProperties(studentDTO,StudentVO.class);
    }

    @Override
    public IPage<StudentVO> queryAndPage(QueryAndPage queryAndPage) throws Exception {
        IPage<StudentDTO> studentDTOS = studentManager.queryAndPage(queryAndPage.getPageNum(),queryAndPage.getPageSize(),queryAndPage.getQuery());
        IPage<StudentVO> studentVOIPage = PageConvert.pageConvert(studentDTOS,StudentVO.class);
        convertVO(studentVOIPage.getRecords());
        return studentVOIPage;
    }

    @Override
    public List<StudentVO> query(Map<String, Object> query) throws Exception {
        List<StudentDTO> studentDTOS = studentManager.queryStudent(query);
        return BeanUtils.convertList(studentDTOS,StudentVO.class);
    }

    @Override
    public List<StudentVO> query(Object query) throws Exception {
        List<StudentDTO> studentDTOS = studentManager.queryStudent(query);
        return BeanUtils.convertList(studentDTOS,StudentVO.class);
    }

    @Override
    public int addWholeByList(List<StudentVO> StudentVOList) throws Exception {
        return 0;
    }

    @Override
    public int addByList(List<StudentVO> StudentVOList) throws Exception {
        return 0;
    }
}
