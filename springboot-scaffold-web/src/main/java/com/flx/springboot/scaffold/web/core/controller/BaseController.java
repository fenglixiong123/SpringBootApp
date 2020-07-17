package com.flx.springboot.scaffold.web.core.controller;

import com.flx.springboot.scaffold.common.servlet.ServletUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Fenglixiong
 * @Create 2018.11.07 12:54
 * @Description @ModelAttribute注释在方法上每个方法执行前都会执行
 **/
@Slf4j
public abstract class BaseController {

    /**
     * 获取原始请求对象
     * @return
     */
    public HttpServletRequest getRequest(){
        return ServletUtils.getRequest();
    }

    /**
     * 获得原始响应对象
     * @return
     */
    public HttpServletResponse getResponse(){
        return ServletUtils.getResponse();
    }

}
