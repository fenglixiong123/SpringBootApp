package com.flx.springboot.scaffold.aop.config;

import com.alibaba.fastjson.JSONObject;
import com.flx.springboot.scaffold.aop.annotation.BizLog;
import com.flx.springboot.scaffold.common.utils.CommonUtils;
import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/26 14:22
 * @Description:
 */
@Slf4j
@Aspect
@Component
public class BizAspect {

    /**
     * 注解式切点
     * @param bizLog
     */
    @Pointcut(value = "@annotation(bizLog)")
    public void bizPoint(BizLog bizLog){

    }

    @AfterReturning(pointcut = "bizPoint(bizLog)",returning = "result", argNames = "joinPoint,bizLog,result")
    public void afterReturning(JoinPoint joinPoint,BizLog bizLog,Object result){
        log.info("============>afterReturning,bizId:{}",bizLog.bizId());
        String classPath = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        Object[] paramValues = joinPoint.getArgs();
        String paramJson = processParamJson(paramNames,paramValues);
        String resultJson = JsonUtils.toJsonMsg(result);
        log.info("classpath:{}",classPath);
        log.info("methodName:{}",methodName);
        log.info("bizLog:{}", JsonUtils.toJsonMsg(bizLog));
        log.info("paramKeyJson:{}", JsonUtils.toJsonMsg(paramNames));
        log.info("paramValueJson:{}", JsonUtils.toJsonMsg(paramValues));
        log.info("resultJson:{}", resultJson);
        log.info("paramJson:{}",paramJson);
    }

    @AfterThrowing(pointcut = "bizPoint(bizLog)", argNames = "bizLog")
    public void afterThrowing(BizLog bizLog){
        log.info("============>afterThrowing<========");
    }

    /**
     * 处理请求JSON
     * @param paramNames
     * @param paramValues
     * @return
     */
    private String processParamJson(String[] paramNames, Object[] paramValues) {
        if(CommonUtils.isEmpty(paramNames)||CommonUtils.isEmpty(paramValues)){
            return null;
        }
        JSONObject jsonObject = new JSONObject();
        for(int i=0;i<paramNames.length;i++){
            jsonObject.put(paramNames[i],paramValues[i]);
        }
        return jsonObject.toJSONString();
    }

}
