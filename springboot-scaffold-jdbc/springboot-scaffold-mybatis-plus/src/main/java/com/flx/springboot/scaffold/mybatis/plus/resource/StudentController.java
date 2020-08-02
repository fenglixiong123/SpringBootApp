package com.flx.springboot.scaffold.mybatis.plus.resource;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.mybatis.plus.service.StudentService;
import com.flx.springboot.scaffold.mybatis.plus.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author Fenglixiong
 * @Create 2020/8/2 21:36
 * @Description
 **/
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResultResponse query(@RequestBody HashMap<String,Object> query){
        try {
            return ResultResponse.success(studentService.query(query));
        }catch (Exception e){
            return ResultResponse.error();
        }
    }

}
