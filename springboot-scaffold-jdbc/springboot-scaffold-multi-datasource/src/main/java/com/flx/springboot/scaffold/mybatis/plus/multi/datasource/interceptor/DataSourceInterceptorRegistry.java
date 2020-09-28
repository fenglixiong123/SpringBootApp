package com.flx.springboot.scaffold.mybatis.plus.multi.datasource.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Fenglixiong
 * @Date: 2020/9/28 17:54
 * @Description:
 */
@Configuration
public class DataSourceInterceptorRegistry implements WebMvcConfigurer {

    /**
     * 添加多数据源拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DataSourceInterceptor());
    }
}
