package com.flx.springboot.scaffold.application.controller;

import com.flx.springboot.scaffold.application.service.StudentService;
import com.flx.springboot.scaffold.application.service.UserService;
import com.flx.springboot.scaffold.web.core.context.SpringContextUtil;
import com.flx.springboot.scaffold.web.core.result.ResultResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/22 17:59
 * @Description:
 */
@Api(value = "管理类")
@RestController
@RequestMapping(value = "/simple")
public class SimpleController {

    @GetMapping("/getUserServiceBean")
    public ResultResponse getUserServiceBean(){
        UserService userService = SpringContextUtil.getBean("userServiceBean", UserService.class);
        userService.sayHello();
        return ResultResponse.ok();
    }

    @GetMapping("/getStudentServiceBean")
    public ResultResponse getStudentServiceBean(){
        StudentService studentService = SpringContextUtil.getBean("studentService", StudentService.class);
        studentService.sayHello();
        return ResultResponse.ok();
    }

}
