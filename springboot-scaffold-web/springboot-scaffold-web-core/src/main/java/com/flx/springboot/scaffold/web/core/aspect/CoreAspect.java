package com.flx.springboot.scaffold.web.core.aspect;



import com.flx.springboot.scaffold.web.core.annotation.RequestLimit;
import com.flx.springboot.scaffold.web.core.annotation.SysLog;
import com.flx.springboot.scaffold.web.core.util.CoreUtils;
import com.flx.springboot.scaffold.web.core.util.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author Fenglixiong
 * @Create 2018.11.29 14:54
 * @Description
 **/
@Slf4j
@Aspect
@Component
public class CoreAspect implements InitializingBean {


    /**
     * 最大搜索字段长度
     */
    private static final int MAX_SEARCH_LENGTH = 3;

    /**
     * execution()语法:execution (* com.xx.xx.impl..*.*(..))
     * 第一个*表示方法返回值类型[*表示所有类型]
     * com.xx.xx.impl表示包路径[*表示所有包]
     * .[.表示当前包下所有类的方法,..表示当前包下及此包下所有子包中的所有类的方法]
     * 第二个*表示类名[*表示所有类,可以匹配以X开头或结尾如X*、*X、X*X的类名]
     * 第三个*表示方法名[*表示所有方法,可以匹配以X开头或结尾的如X*、*X、X*X的方法名]
     * (..)表示方法参数[..表示任何参数]
     */
    @Pointcut(value = "execution(* com.flx.springboot.scaffold..*.*(..))")
    private void serviceCommon(){

    }

    /**
     * 定义请求日志切入点
     * @param sysLog
     */
    @Pointcut(value = "@annotation(sysLog)")
    public void serviceSysLog(SysLog sysLog){

    }

    /**
     * 定义请求次数限制
     * @param requestLimit
     */
    @Pointcut(value = "@annotation(requestLimit)")
    public void serviceRequestLimit(RequestLimit requestLimit){

    }

    /**
     * 前置通知
     * @param
     * @param requestLimit
     */
    @Before(value = "serviceRequestLimit(requestLimit)")
    public void doBefore(JoinPoint joinPoint,RequestLimit requestLimit){
        log.info("before...");
        log.info("RequestLimit:count->{},time->{}",requestLimit.count(),requestLimit.time());

    }

    /**
     * 后置通知
     * @param joinPoint
     * @param sysLog
     */
    @AfterReturning(pointcut = "serviceSysLog(sysLog)",returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result , SysLog sysLog){
        log.info("afterReturning...");
        //获取切点参数
        processReturnJoinPoint(joinPoint,result);
    }


    /**
     * 处理返回通知切点
     * @param joinPoint
     * @param result
     */
    private void processReturnJoinPoint(JoinPoint joinPoint,Object result) {

        String classPath = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] paramArgs = joinPoint.getArgs();
        log.info("classpath:{}",classPath);
        log.info("methodName:{}",methodName);
        log.info("paramJson:{}", CoreUtils.toJsonMsg(paramArgs));
        log.info("resultJson:{}", CoreUtils.toJsonMsg(result));
        HttpServletRequest request = getRequest();
        String ipAddress = IPUtils.getSimpleIp(request);
        String requestUrl = IPUtils.getRequestUrl(request);
        log.info("IPAddress:{}",ipAddress);
        log.info("requestUrl:{}",requestUrl);

    }


    /**
     * 获取Http请求对象
     * @return
     */
    private HttpServletRequest getRequest(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes!=null) {
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("----------->CoreAspect init<-------------");
    }
}
