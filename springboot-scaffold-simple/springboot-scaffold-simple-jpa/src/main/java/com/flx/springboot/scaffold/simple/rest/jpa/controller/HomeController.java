package com.flx.springboot.scaffold.simple.rest.jpa.controller;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.exception.element.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Fenglixiong
 * @Create 2018.11.15 17:54
 * @Description
 **/
@Slf4j
@RequestMapping(value = "/home")
public class HomeController {

    @GetMapping(value = "/hello")
    public ResultResponse hello(@RequestParam("someone")String someone)throws Exception{
        log.info("hello,{}",someone);
        throw new BizException("hello error");
    }

    @GetMapping(value = "/greet")
    public ResultResponse<String> greet(@RequestParam("someone")String someone){
        log.info("greet,{}",someone);
        int s = 111/0;
        return ResultResponse.success("Greet "+someone);
    }

}
