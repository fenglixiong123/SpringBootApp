package com.flx.springboot.scaffold.filter.filter.single;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/20 15:30
 * @Description:
 * @WebFilter 也可以控制filter的执行顺序
 * 通过实践发现如果想要控制filer的执行顺序可以 通过控制filter的文件名的首字母来 来控制
 */
@Slf4j
@WebFilter(filterName = "simpleFilter",urlPatterns = {"/filter/single/*"})
public class SingleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(filterConfig.getFilterName()+"init...");
    }

    /**
     * 用户名必须是admin才能继续访问
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String host = servletRequest.getRemoteHost();
        log.info("simpleFilter doFilter...{}",host);
        String username = servletRequest.getParameter("username");
        if(!StringUtils.isBlank(username) && username.equals("admin")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            throw new ServletException("error username !!!");
        }
    }

    @Override
    public void destroy() {
        log.info("simpleFilter destroy...");
    }
}
