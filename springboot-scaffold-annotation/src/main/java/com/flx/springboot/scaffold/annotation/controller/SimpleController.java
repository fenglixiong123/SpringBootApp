package com.flx.springboot.scaffold.annotation.controller;

import com.flx.springboot.scaffold.annotation.service.StudentService;
import com.flx.springboot.scaffold.annotation.service.UserService;
import com.flx.springboot.scaffold.common.context.SpringContextUtil;
import com.flx.springboot.scaffold.common.result.ResultResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/22 17:59
 * @Description:
 */
@Slf4j
@Api(value = "管理类")
@RestController
@RequestMapping(value = "/simple")
public class SimpleController {

    @GetMapping("/getUserServiceBean")
    public ResultResponse getUserServiceBean(){
        UserService userService = SpringContextUtil.getBean("userService", UserService.class);
        userService.sayHello();
        int z = 1/0;
        return ResultResponse.success();
    }

    @GetMapping("/getStudentServiceBean")
    public ResultResponse getStudentServiceBean(){
        StudentService studentService = SpringContextUtil.getBean("studentService", StudentService.class);
        studentService.sayHello();
        return ResultResponse.success();
    }

}
