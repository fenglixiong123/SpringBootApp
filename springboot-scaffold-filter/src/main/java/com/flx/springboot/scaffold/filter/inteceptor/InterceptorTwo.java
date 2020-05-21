package com.flx.springboot.scaffold.filter.inteceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/20 19:46
 * @Description:
 */
@Slf4j
@Component
public class InterceptorTwo implements HandlerInterceptor {

    //进入controller层之前拦截请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Interceptor Two preHandle ...");
        return true;
    }

    //处理请求完成后视图渲染之前的处理操作
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Interceptor Two postHandle ...");
    }

    //视图渲染之后的操作
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        log.info("InterceptorTwo afterCompletion ...");
    }
}
