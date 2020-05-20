package com.flx.springboot.scaffold.filter.filter.multi;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/20 18:06
 * @Description:
 */
@Slf4j
public class FilterTwo implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("FilterTwo doFilter...");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.info("FilterTwo destroy...");
    }
}
