package com.flx.springboot.scaffold.filter.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/20 19:28
 * @Description:
 *
 * ServletContextListener类：能够监听ServletContext对象的生命周期，实际上就是监听Web应用的生命周期。
 * 当Servlet 容器启动或终止Web应用时，会触发ServletContextEvent 事件，该事件由ServletContextListener类来处理。
 * 在ServletContextListener接口中定义了处理ServletContextEvent 事件的两个方法：contextInitialized和contextDestroyed
 */
@Slf4j
@WebListener
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("ServletContext上下文初始化...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("ServletContext上下文销毁...");
    }
}
