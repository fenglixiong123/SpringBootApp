package com.flx.springboot.scaffold.common.utils.network;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 可以随时随地获取request，response对象
 */
@Slf4j
public class ServletUtils {

    /**
     * 获取当前请求对象
      * @return
     */
    public static HttpServletRequest getRequest(){
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            if(servletRequestAttributes!=null) {
                return servletRequestAttributes.getRequest();
            }
        }catch (Exception e){
            log.error("获取当前请求对象Request错误",e);
        }
        return null;
    }

    /**
     * 获取当前响应对象
     * @return
     */
    public static HttpServletResponse getResponse(){
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            if(servletRequestAttributes!=null) {
                return servletRequestAttributes.getResponse();
            }
        }catch (Exception e){
            log.error("获取当前响应对象Response错误");
        }
        return null;
    }

}
