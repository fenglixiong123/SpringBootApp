package com.flx.springboot.scaffold.jms.controller;

import com.flx.springboot.scaffold.web.core.annotation.RestController;
import com.flx.springboot.scaffold.web.core.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Fenglixiong
 * @Create 2019.01.11 18:23
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/hello")
    public ResultResponse<String> hello(){
        log.info(">>>>>>>>Hello");
        return ResultResponse.ok("hello");
    }

}
