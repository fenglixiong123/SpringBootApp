package com.flx.springboot.email.controller;

import com.flx.springboot.email.entity.ComplexMail;
import com.flx.springboot.email.entity.SimpleMail;
import com.flx.springboot.email.service.ComplexEmailService;
import com.flx.springboot.email.service.SimpleEmailService;
import com.flx.springboot.scaffold.common.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Api("邮件收发相关接口")
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private ComplexEmailService complexEmailService;

    @ApiOperation("发送简单的邮件")
    @GetMapping("/sendSimpleEmail")
    public ResultResponse sendSimpleEmil(
                                @RequestParam String to,
                                @RequestParam String subject,
                                @RequestParam String content){
        boolean result = simpleEmailService.sendSimpleEmail(new SimpleMail(subject,to,content));
        log.info("发送结果：{}",result);
        return ResultResponse.success(result);
    }

    @ApiOperation("发送复杂的邮件")
    @GetMapping("/sendComplexEmail")
    public ResultResponse sendComplexEmil(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String content){
        boolean result = complexEmailService.sendComplexEmail(new ComplexMail(subject,to,content));
        log.info("发送结果：{}",result);
        return ResultResponse.success(result);
    }

}
