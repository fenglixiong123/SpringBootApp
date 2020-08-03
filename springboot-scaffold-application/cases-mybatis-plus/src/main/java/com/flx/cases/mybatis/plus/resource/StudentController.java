package com.flx.cases.mybatis.plus.resource;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.cases.mybatis.plus.service.StudentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author Fenglixiong
 * @Create 2020/8/2 21:36
 * @Description
 **/
@Api(tags = "学生管理")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/count")
    public ResultResponse<Integer> count(){

        return ResultResponse.success(studentService.count());
    }

    @PostMapping("/query")
    public ResultResponse query(@RequestBody HashMap<String,Object> query){
        try {
            return ResultResponse.success(studentService.query(query));
        }catch (Exception e){
            return ResultResponse.error();
        }
    }

}
