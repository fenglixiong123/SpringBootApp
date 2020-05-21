package com.flx.springboot.scaffold.filter.config;

import com.flx.springboot.scaffold.filter.inteceptor.InterceptorOne;
import com.flx.springboot.scaffold.filter.inteceptor.InterceptorTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/20 19:51
 * @Description:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private InterceptorOne interceptorOne;
    @Autowired
    private InterceptorTwo interceptorTwo;

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorOne).addPathPatterns("/interceptor/**")
                .excludePathPatterns("/login").excludePathPatterns("/img/**","/css/**","/js/**");
        registry.addInterceptor(interceptorTwo).addPathPatterns("/interceptor/**")
                .excludePathPatterns("/login").excludePathPatterns("/img/**","/css/**","/js/**");
    }
}
