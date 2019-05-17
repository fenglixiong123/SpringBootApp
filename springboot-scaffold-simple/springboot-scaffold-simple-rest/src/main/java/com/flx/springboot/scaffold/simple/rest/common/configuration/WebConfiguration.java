package com.flx.springboot.scaffold.simple.rest.common.configuration;

import com.flx.springboot.scaffold.simple.rest.common.interceptor.XSSInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author Fenglixiong
 * @Create 2018.11.15 15:21
 * @Description
 **/
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private XSSInterceptor xssInterceptor;


    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(xssInterceptor).addPathPatterns("/admin/**");
        super.addInterceptors(registry);
    }

    /**
     * 静态资源配置
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/resources");
        super.addResourceHandlers(registry);
    }
}
