package com.flx.springboot.scaffold.filter.filter.single;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/20 15:30
 * @Description:
 */
@Slf4j
@WebFilter(filterName = "simpleFilter",urlPatterns = {"/single/*"})
public class SingleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(filterConfig.getFilterName()+"init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("simpleFilter doFilter...");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.info("simpleFilter destroy...");
    }
}
