package com.flx.springboot.scaffold.logger.common.aspect;


import com.alibaba.fastjson.JSONObject;
import com.flx.springboot.scaffold.common.utils.CommonUtils;
import com.flx.springboot.scaffold.common.utils.base.ParamUtils;
import com.flx.springboot.scaffold.common.utils.json.JsonParser;
import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.logger.common.annotation.BizLog;
import com.flx.springboot.scaffold.logger.entity.BizLogger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Author Fenglixiong
 * @Create 2018.11.29 14:54
 * @Description
 **/
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Value("${common.biz.log.enable:true}")
    private boolean enable;

    /**
     * 最大搜索字段长度
     */
    private static final int MAX_SEARCH_LENGTH = 3;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 定义请求日志切入点
     * @param bizLog
     */
    @Pointcut(value = "@annotation(bizLog)")
    public void serviceCut(BizLog bizLog){

    }

    /**
     * 返回通知
     * @param joinPoint
     * @param bizLog
     */
    @AfterReturning(pointcut = "serviceCut(bizLog)",returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, BizLog bizLog, Object result){
        log.info("============>afterReturning,globalEnable:{},bizEnable:{}",enable,bizLog.enable());
        try {
            if(enable && bizLog.enable()) {
                BizLogger bizLogger = new BizLogger();
                processJoinPoint(joinPoint, result, bizLog, bizLogger);
                processSaveLog(bizLogger);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 处理切点
     * @param joinPoint
     * @param result
     * @param bizLog
     * @param bizLogger
     */
    private void processJoinPoint(JoinPoint joinPoint, Object result,BizLog bizLog ,BizLogger bizLogger) {
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
        bizLogger.setClassPath(classPath);
        bizLogger.setMethodName(methodName);
        bizLogger.setParamJson(paramJson);
        bizLogger.setResultJson(resultJson);
        bizLogger.setRemark(bizLog.remark());
        bizLogger.setBizType(bizLog.bizType().getType());
        bizLogger.setOperateType(bizLog.operateType().getType());
        bizLogger.setUserId("1");
        bizLogger.setUserName("管理员");
        bizLogger.setCreateTime(new Date());
        //从参数或者返回值中获取主键
        processWantColumns(paramJson,resultJson,result,bizLog,bizLogger);

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

    /**
     * 处理需要保存的字段
     * @param paramJson
     * @param resultJson
     * @param bizLog
     */
    private void processWantColumns(String paramJson, String resultJson,Object result, BizLog bizLog,BizLogger bizLogger) {
        String id;
        List<String> searchValues = new ArrayList<>(MAX_SEARCH_LENGTH);
        if(bizLog.idFromResp()){
            //如果是基础类型的直接存储主键
            if(ParamUtils.isBaseType(result)){
                id = result.toString();
            }else {
                id = JsonParser.readVal(resultJson, bizLog.bizId());
            }
        }else {
            id = JsonParser.readVal(paramJson,bizLog.bizId());
        }
        log.info("记录主键字段:{}",id);
        //获取参数中的检索字段
        String[] keys = bizLog.searchKey();
        if(CommonUtils.isNotEmpty(keys)){
            int length = keys.length>MAX_SEARCH_LENGTH?MAX_SEARCH_LENGTH:keys.length;
            for (int i = 0; i < length; i++) {
                searchValues.add(JsonParser.readVal(paramJson,keys[i]));
            }
        }
        log.info("记录检索字段：{}",searchValues);
        bizLogger.setBizId(id);
        try {
            bizLogger.setSearchOne(searchValues.get(0));
            bizLogger.setSearchTwo(searchValues.get(1));
            bizLogger.setSearchThree(searchValues.get(2));
        }catch (IndexOutOfBoundsException e){
            log.error("IndexOutOfBoundsException...");
        }
    }

    /**
     * 处理日志的存储
     */
    private void processSaveLog(BizLogger bizLogger) {
        log.info("【Redis】发送日志消息：{}",JsonUtils.toJsonMsg(bizLogger));
        stringRedisTemplate.convertAndSend("chat",JsonUtils.toJsonMsg(bizLogger));

    }

}
