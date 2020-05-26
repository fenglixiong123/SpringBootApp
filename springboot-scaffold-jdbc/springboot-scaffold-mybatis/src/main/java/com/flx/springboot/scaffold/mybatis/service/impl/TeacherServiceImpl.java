package com.flx.springboot.scaffold.mybatis.service.impl;

import com.flx.springboot.scaffold.mybatis.dao.TeacherDao;
import com.flx.springboot.scaffold.mybatis.model.Teacher;
import com.flx.springboot.scaffold.mybatis.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Fenglixiong
 * @Create 2019.01.22 17:26
 * @Description
 **/
@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher select(Long id) {
        return teacherDao.selectByPrimaryKey(id);
    }

    @Override
    public Long save(Teacher teacher) {
        teacherDao.insert(teacher);
        return teacher.getId();
    }

    @Override
    public Long update(Teacher teacher) {
        teacherDao.updateByPrimaryKeySelective(teacher);
        return teacher.getId();
    }

    @Override
    public int delete(Long id) {
        return teacherDao.deleteByPrimaryKey(id);
    }
}
