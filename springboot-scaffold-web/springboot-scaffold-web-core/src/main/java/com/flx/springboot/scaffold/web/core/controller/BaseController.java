package com.flx.springboot.scaffold.web.core.controller;

import com.flx.springboot.scaffold.web.core.util.ServletUtil;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author Fenglixiong
 * @Create 2018.11.07 12:54
 * @Description @ModelAttribute注释在方法上每个方法执行前都会执行
 **/
public abstract class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    protected void setReqAndRes(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.session = request.getSession();
        init();
    }

    protected void init(){

    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public HttpSession getSession() {
        return session;
    }

    /**
     * 获取原始请求对象
     * @return
     */
    public HttpServletRequest getOriginalRequest(){
        return ServletUtil.getRequest();
    }

    /**
     * 获得原始响应对象
     * @return
     */
    public HttpServletResponse getOriginalResponse(){
        return ServletUtil.getResponse();
    }

}
