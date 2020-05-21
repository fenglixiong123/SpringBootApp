package com.flx.springboot.scaffold.filter.filter.multi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/20 18:13
 * @Description:
 */
@Slf4j
@Configuration
public class FilterConfig {

    @Bean
    public Filter filter1(){
        return new FilterOne();
    }

    @Bean
    public Filter filter2(){
        return new FilterTwo();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean1(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(filter1());
        filterRegistrationBean.setName("filterOne");
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/filter/multi/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean2(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(filter2());
        filterRegistrationBean.setName("filterTwo");
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/filter/multi/*");
        return filterRegistrationBean;
    }

}
