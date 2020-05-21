package com.flx.springboot.scaffold.filter.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/21 18:55
 * @Description:
 */
@Slf4j
@WebListener
public class SessionListener implements HttpSessionListener {

    private AtomicInteger sessionCount = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("One session create...{}",sessionCount.incrementAndGet());
        se.getSession().setAttribute("count",sessionCount.get());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("One session destroy...{}",sessionCount.decrementAndGet());
        se.getSession().setAttribute("count",sessionCount.get());
    }
}
