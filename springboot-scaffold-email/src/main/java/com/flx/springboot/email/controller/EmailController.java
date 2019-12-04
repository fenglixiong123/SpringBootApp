package com.flx.springboot.email.controller;

import com.flx.springboot.email.service.EmailService;
import com.flx.springboot.scaffold.web.core.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Fenglixiong
 * @Date: 2019/12/4 11:13
 * @Description:
 */
@Api("邮件收发相关接口")
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @ApiOperation("测试信息")
    @GetMapping("/hello")
    public ResultResponse hello(@RequestParam String name){

        return ResultResponse.ok("成功");
    }

}
