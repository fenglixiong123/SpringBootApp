package com.flx.scaffold.jdbc.mybatis.service;

import com.flx.scaffold.jdbc.mybatis.model.Teacher;

public interface TeacherService {

    Teacher select(Long id);

    Long save(Teacher teacher);

    Long update(Teacher teacher);

    int delete(Long id);

}
