package com.flx.springboot.scaffold.system.i18n.common.config;

import com.flx.springboot.scaffold.system.i18n.common.inteceptor.I18nInterceptor;
import com.flx.springboot.scaffold.system.i18n.common.inteceptor.I18nReturnHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Fenglixiong
 * @Date: 2020/8/6 19:31
 * @Description:
 */
@Slf4j
@Configuration
public class I18nWebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
    }

    /**
     * 注入拦截器bean
     * @return
     */
    @Bean
    public I18nInterceptor i18nInterceptor(){
        return new I18nInterceptor();
    }

    /**
     * 注入返回值过滤器bean
     */
    @Bean
    public I18nReturnHandler i18nReturnHandler(){
        return new I18nReturnHandler(requestMappingHandlerAdapter.getMessageConverters());
    }

    /**
     * 拦截器
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(i18nInterceptor());
        super.addInterceptors(registry);
    }

    /**
     * 配置消息转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
    }

    /**
     * 处理静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/index.html")
                .addResourceLocations("classpath:/html/");
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    /**
     * 解决自定义ReturnHandler不生效问题
     * 原因是自己添加的位置会比较靠后，最后得不到执行就返回了
     */
    @PostConstruct
    public void init(){
        final List<HandlerMethodReturnValueHandler> originalHandlers = new ArrayList<>(Objects.requireNonNull(requestMappingHandlerAdapter.getReturnValueHandlers()));
        final int deferredPos = obtainValueHandlerPosition(originalHandlers, HttpEntityMethodProcessor.class);
        originalHandlers.add(deferredPos - 1, i18nReturnHandler());
        requestMappingHandlerAdapter.setReturnValueHandlers(originalHandlers);
    }

    /**
     * 获取自定义返回值处理器的位置
     * @param originalHandlers
     * @param handlerClass
     * @return
     */
    private int obtainValueHandlerPosition(final List<HandlerMethodReturnValueHandler> originalHandlers, Class<?> handlerClass) {
        for (int i = 0; i < originalHandlers.size(); i++) {
            final HandlerMethodReturnValueHandler handler = originalHandlers.get(i);
            if (handlerClass.isAssignableFrom(handler.getClass())) {
                return i;
            }
        }
        return -1;
    }

}
