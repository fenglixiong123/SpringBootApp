package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.cases.mybatis.plus.dao.StudentDao;
import com.flx.cases.mybatis.plus.dto.StudentDTO;
import com.flx.cases.mybatis.plus.entity.StudentDO;
import com.flx.springboot.scaffold.common.servlet.BeanUtils;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseManager;
import com.flx.springboot.scaffold.mybatis.plus.page.PageConvert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/24 17:24
 * @Description:
 */
@Service
public class StudentManager extends BaseManager<StudentDO, StudentDao> {

    public int count(){
        return dao.countStudent();
    }

    public Long add(StudentDO StudentDO) throws Exception {
        return super.add(StudentDO);
    }

    public IPage<StudentDTO> queryAndPage(Integer pageNum, Integer pageSize, Map<String, Object> query) throws Exception {
        IPage<StudentDO> StudentDOList = super.queryPage(pageNum, pageSize, query);
        return PageConvert.pageConvert(StudentDOList, StudentDTO.class);
    }


    public IPage<StudentDTO> queryAndPage(Integer pageNum, Integer pageSize, Object query) throws Exception {
        IPage<StudentDO> StudentDOList = super.queryPage(pageNum, pageSize, query);
        return PageConvert.pageConvert(StudentDOList, StudentDTO.class);
    }

    public List<StudentDTO> queryStudent(Map<String, Object> query) throws Exception {
        List<StudentDO> StudentDOList = super.query(query);
        return StudentDOList.parallelStream().map(e -> BeanUtils.copyProperties(e, StudentDTO.class)).collect(Collectors.toList());
    }

    public List<StudentDTO> queryStudentAllColumns(Map<String, Object> query) throws Exception {
        List<StudentDO> StudentDOList = super.queryAllColumns(query);
        return StudentDOList.parallelStream().map(e -> BeanUtils.copyProperties(e, StudentDTO.class)).collect(Collectors.toList());
    }

    public List<StudentDTO> queryStudent(Object query) throws Exception {
        List<StudentDO> StudentDOList = super.query(query);
        return StudentDOList.parallelStream().map(e -> BeanUtils.copyProperties(e, StudentDTO.class)).collect(Collectors.toList());
    }

    public Integer deleteById(Long id) throws Exception {
        return super.deleteById(id);
    }

    public Integer updateById(StudentDO StudentDO) throws Exception {
        return super.updateById(StudentDO);
    }

    public Integer updateNullById(StudentDO StudentDO) throws Exception {
        return super.updateNullById(StudentDO);
    }

    public Integer updateByCode(StudentDO StudentDO) throws Exception {
        return super.updateByCode(StudentDO, StudentDO.getStudentNo(), "StudentNo");
    }

    public Integer updateNullByCode(StudentDO StudentDO) throws Exception {
        return super.updateNullByCode(StudentDO,  StudentDO.getStudentNo(), "StudentNo");
    }

    public StudentDTO getById(Long id) throws Exception {
        Object StudentDO = super.getById(id);
        return BeanUtils.copyProperties(StudentDO, StudentDTO.class);
    }

    public boolean isExist(String studentNo) throws Exception {
        QueryWrapper<StudentDO> queryWrapper = getConditionBuilder().query("StudentNo", studentNo).build(false);
        return dao.selectCount(queryWrapper)>0;
    }

    public boolean isExist(Long id) throws Exception {
        QueryWrapper<StudentDO> queryWrapper = getConditionBuilder().query("id", id).build(false);
        return dao.selectCount(queryWrapper)>0;
    }

    public Integer addByList(List<StudentDO> StudentDOList) throws Exception {
        try {
            return super.addByList(StudentDOList);
        } catch (Exception e) {
            return 0;
        }
    }
}
