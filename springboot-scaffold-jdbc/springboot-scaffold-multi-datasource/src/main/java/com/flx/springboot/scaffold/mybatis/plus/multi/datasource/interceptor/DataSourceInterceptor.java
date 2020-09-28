package com.flx.springboot.scaffold.mybatis.plus.multi.datasource.interceptor;

import com.flx.springboot.scaffold.mybatis.plus.multi.datasource.datasource.DynamicDataSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Fenglixiong
 * @Date: 2020/9/28 17:52
 * @Description:
 */
public class DataSourceInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //每次请求后清除已经使用的数据源
        DynamicDataSource.removeDataSource();
    }
}
