package com.flx.springboot.scaffold.simple.rest.jpa.controller.student;

import com.flx.springboot.scaffold.simple.rest.jpa.domain.Student;
import com.flx.springboot.scaffold.simple.rest.jpa.service.student.StudentService;
import com.flx.springboot.scaffold.web.core.annotation.RestController;
import com.flx.springboot.scaffold.web.core.result.ResultResponse;
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
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResultResponse<Student> getStudentById(@PathVariable("id")Long id){
        return ResultResponse.ok(studentService.findStudentById(id));
    }

    @PostMapping("/add")
    public ResultResponse<Student> saveStudent(@Valid @RequestBody Student student){
        return ResultResponse.ok(studentService.saveStudent(student));
    }

    @PutMapping("/update")
    public ResultResponse<Student> updateStudent(@RequestBody Student student){
        return ResultResponse.ok(studentService.updateStudent(student));
    }

    @GetMapping("/list")
    public ResultResponse<List<Student>> studentList(){
        return ResultResponse.ok(studentService.studentList());
    }

    @PutMapping("/updateStudentHobby")
    public ResultResponse<Integer> updateStudentHobby(Long id,String hobby){
        return ResultResponse.ok(studentService.updateStudentHobbyById(hobby,id));
    }

}
