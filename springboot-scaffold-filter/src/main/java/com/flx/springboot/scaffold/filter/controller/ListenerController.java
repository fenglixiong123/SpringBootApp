package com.flx.springboot.scaffold.filter.controller;

import com.flx.springboot.scaffold.web.core.result.ResultResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/21 19:09
 * @Description:
 */
@Slf4j
@Api(value = "监听器管理层")
@RestController
@RequestMapping("/listener")
public class ListenerController {

    @GetMapping("/sayHello")
    public ResultResponse sayHello(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Object count = session.getAttribute("count");
        return ResultResponse.ok("Hello Listener : "+count);
    }

}
