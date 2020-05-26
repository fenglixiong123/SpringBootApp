package com.flx.springboot.scaffold.mybatis.controller;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.mybatis.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Fenglixiong
 * @Create 2019.01.22 17:56
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/trans")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/test")
    public ResultResponse<String> test(){
        log.info("publish>>>>>>>>>>>>");
        transactionService.publish();
        return ResultResponse.success();
    }

}
