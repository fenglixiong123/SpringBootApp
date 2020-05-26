package com.flx.springboot.scaffold.mybatis.service;


import com.flx.springboot.scaffold.mybatis.model.Teacher;

public interface TeacherService {

    Teacher select(Long id);

    Long save(Teacher teacher);

    Long update(Teacher teacher);

    int delete(Long id);

}
