package com.flx.springboot.scaffold.application.controller;

import com.flx.springboot.scaffold.application.service.StudentService;
import com.flx.springboot.scaffold.application.service.UserService;
import com.flx.springboot.scaffold.common.context.SpringContextUtil;
import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.web.core.controller.BaseController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/22 17:59
 * @Description:
 */
@Slf4j
@Api(value = "管理类")
@RestController
@RequestMapping(value = "/simple")
public class SimpleController extends BaseController {

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

    @GetMapping("/getRequest")
    public ResultResponse getRequest1(){
        log.info(this.getRequest().getRequestedSessionId());
        log.info(this.getRequest().getAttribute("aaa").toString());
        return ResultResponse.success();
    }

    @GetMapping("/setRequest1")
    public ResultResponse setRequest1() throws InterruptedException {
        log.info(this.getRequest().getRequestedSessionId());
        this.getRequest().setAttribute("aaa","aaa1");
        log.info(Thread.currentThread().getName());
        Thread.sleep(5000);
        return ResultResponse.success();
    }

    @GetMapping("/setRequest2")
    public ResultResponse setRequest2(){
        log.info(this.getRequest().getRequestedSessionId());
        this.getRequest().setAttribute("aaa","aaa2");
        return ResultResponse.success();
    }

}
