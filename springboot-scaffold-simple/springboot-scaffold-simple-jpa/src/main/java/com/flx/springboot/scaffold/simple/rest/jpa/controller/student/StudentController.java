package com.flx.springboot.scaffold.simple.rest.jpa.controller.student;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.simple.rest.jpa.domain.Student;
import com.flx.springboot.scaffold.simple.rest.jpa.service.student.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2018.11.15 18:39
 * @Description
 **/
@Slf4j
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResultResponse<Student> getStudentById(@PathVariable("id")Long id){
        return ResultResponse.success(studentService.findStudentById(id));
    }

    @PostMapping("/add")
    public ResultResponse<Student> saveStudent(@Valid @RequestBody Student student){
        return ResultResponse.success(studentService.saveStudent(student));
    }

    @PutMapping("/update")
    public ResultResponse<Student> updateStudent(@RequestBody Student student){
        return ResultResponse.success(studentService.updateStudent(student));
    }

    @GetMapping("/list")
    public ResultResponse<List<Student>> studentList(){
        return ResultResponse.success(studentService.studentList());
    }

    @PutMapping("/updateStudentHobby")
    public ResultResponse<Integer> updateStudentHobby(Long id,String hobby){
        return ResultResponse.success(studentService.updateStudentHobbyById(hobby,id));
    }

}
