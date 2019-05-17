package com.flx.springboot.rbac.controller;

import com.flx.springboot.scaffold.web.core.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Fenglixiong
 * @Create 2019.02.14 17:02
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/hello")
    public ResultResponse hello(){
        log.info("-------->hello");
        return ResultResponse.ok();
    }

}
