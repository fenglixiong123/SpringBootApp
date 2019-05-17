package com.flx.springboot.scaffold.simple.rest.jpa.service.student.impl;

import com.flx.springboot.scaffold.simple.rest.jpa.dao.StudentDao;
import com.flx.springboot.scaffold.simple.rest.jpa.domain.Student;
import com.flx.springboot.scaffold.simple.rest.jpa.service.student.StudentService;
import com.flx.springboot.scaffold.web.core.exception.element.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author Fenglixiong
 * @Create 2018.11.15 18:41
 * @Description
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student findStudentById(Long id) {
        Optional<Student> studentOp = studentDao.findById(id);
        return studentOp.orElse(null);
    }

    @Override
    public Student saveStudent(Student student) {
        if(student.getId()!=null){
            throw new ParamException("新增不能传ID！");
        }
        return studentDao.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        if(student.getId()==null){
            throw new ParamException("修改必须传ID！");
        }
        return studentDao.save(student);
    }

    @Override
    public List<Student> studentList() {
        return studentDao.findAll();
    }

    @Override
    public Integer updateStudentHobbyById(String hobby, Long id) {
        return studentDao.updateStudentHobbyById(hobby,id);
    }
}
