package com.flx.springboot.scaffold.jpa.service;




import com.flx.springboot.scaffold.jpa.domain.Student;

import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2018.11.15 18:40
 * @Description
 **/
public interface StudentService {

    Student findStudentById(Long id);

    Student saveStudent(Student student);

    Student updateStudent(Student student);

    List<Student> studentList();

    Integer updateStudentHobbyById(String hobby, Long id);

}
