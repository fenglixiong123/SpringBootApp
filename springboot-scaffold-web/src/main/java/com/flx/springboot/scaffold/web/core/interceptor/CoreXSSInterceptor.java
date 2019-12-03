package com.flx.springboot.scaffold.web.core.interceptor;

import com.flx.springboot.scaffold.web.core.exception.element.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @Author Fenglixiong
 * @Create 2018.11.15 11:13
 * @Description 非法请求参数拦截XSS
 **/

@Component
public class CoreXSSInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Enumeration<String> parameterNames = request.getParameterNames();
        String paramK;
        while (parameterNames.hasMoreElements()){
            paramK = parameterNames.nextElement();
            String paramV = request.getParameter(paramK);
            if(checkSQLInject(paramV)){
                throw new BizException("request params has specialCharacters !");
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 检查是否存在非法字符
     * 1.防止js脚本注入
     * 2.防止sql注入
     * @param key
     * @return
     */
    private boolean checkSQLInject(String key){
        if(StringUtils.isEmpty(key)){
            return false;
        }
        String[] illegals = {"script","src","mid","master","truncate","insert","delete","update","select","declare",
        "iframe","onreadystatechange","alert","atestu","xss",";","\"","<",">","+","\\","svg",
        "confirm","prompt","onload","onmouseover","onfocus","onerror"};
        key = key.toLowerCase();
        for (int i = 0; i < illegals.length; i++) {
            if(key.contains(illegals[i])){
                return true;
            }
        }
        return false;
    }

}
