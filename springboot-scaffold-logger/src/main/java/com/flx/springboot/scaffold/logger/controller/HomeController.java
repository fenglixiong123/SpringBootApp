package com.flx.springboot.scaffold.logger.controller;

import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.logger.common.annotation.BizLog;
import com.flx.springboot.scaffold.logger.common.redis.RedisQueue;
import com.flx.springboot.scaffold.logger.dao.BizLogDao;
import com.flx.springboot.scaffold.logger.entity.BizLogger;
import com.flx.springboot.scaffold.logger.entity.Student;
import com.flx.springboot.scaffold.logger.enums.BizTypeEnum;
import com.flx.springboot.scaffold.logger.enums.OperateEnum;
import com.flx.springboot.scaffold.web.core.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Random;

/**
 * @Author Fenglixiong
 * @Create 2018.11.15 17:54
 * @Description
 **/
@Slf4j
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private BizLogDao bizLogDao;

    @BizLog(bizType = BizTypeEnum.HOUSEING,operateType = OperateEnum.ADD,
            bizId = "student.id",remark = "Hello1方法",searchKey = {"student.name","student.age","student.hobby"})
    @GetMapping(value = "/hello1")
    public ResultResponse<Student> hello1(Student student,String work,int count,Long objId){
        log.info("Hello,{},{},{},{}", JsonUtils.toJsonMsg(student),work,count,objId);
        return ResultResponse.ok(student);
    }

    @BizLog(bizType = BizTypeEnum.HOUSEING,operateType = OperateEnum.ADD,
            bizId = "code",idFromResp = true,remark = "Hello2方法",searchKey = {"student.name","student.age","student.hobby"})
    @GetMapping(value = "/hello2")
    public ResultResponse<Student> hello2(Student student,String work,int count,Long objId){
        log.info("Hello,{},{},{},{}", JsonUtils.toJsonMsg(student),work,count,objId);
        return ResultResponse.ok(student);
    }

    @BizLog(bizType = BizTypeEnum.HOUSEING,operateType = OperateEnum.ADD,
            bizId = "objId",remark = "Hello3方法",searchKey = {"work"})
    @GetMapping(value = "/hello3")
    public ResultResponse<String> hello3(String work,int count,Long objId){
        log.info("Hello,{},{},{}",work,count,objId);
        return ResultResponse.ok();
    }

    @GetMapping("/saveLog")
    public ResultResponse<String> saveLog(Integer type){
        BizLogger bizLogger = new BizLogger();
        bizLogger.setClassPath("1");
        bizLogger.setMethodName("2");
        bizLogger.setBizType(1);
        bizLogger.setOperateType(2);
        bizLogger.setParamJson("sss");
        bizLogger.setResultJson("zzz");
        bizLogger.setSearchOne("1");
        bizLogger.setSearchTwo("2");
        bizLogger.setSearchThree("3");
        bizLogger.setCreateTime(new Date());
        if(type==1) {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                bizLogDao.save(new BizLogger(""+random.nextInt(10),""+random.nextInt(100),""+random.nextInt(1000)));
                log.info("执行完save!");
            }
        }else {
            bizLogDao.save(bizLogger);
            log.info("save successful");
        }
        return ResultResponse.ok("保存成功");
    }

    @GetMapping("/sendMessage")
    public ResultResponse<String> sendMessageToRedis(){
        BizLogger bizLogger = new BizLogger();
        bizLogger.setBizId("xxx");
        bizLogger.setClassPath("1");
        bizLogger.setMethodName("2");
        bizLogger.setBizType(1);
        bizLogger.setOperateType(2);
        bizLogger.setParamJson("sss");
        bizLogger.setResultJson("zzz");
        bizLogger.setSearchOne("1");
        bizLogger.setSearchTwo("2");
        bizLogger.setSearchThree("3");
        bizLogger.setCreateTime(new Date());
        for (int i = 0; i < 10; i++) {
            stringRedisTemplate.convertAndSend("chat",JsonUtils.toJsonMsg(bizLogger));
        }
        return ResultResponse.ok("发送成功");
    }


    @GetMapping("/inQueue")
    public void testInQueue(@RequestParam String message){
        log.info("inQueue:{}",message);
        RedisQueue.inQueue("myQueue",message);
    }


    @GetMapping("/outQueue")
    public void testOutQueue(){
        log.info("outQueue");
        String message = RedisQueue.outQueue("myQueue");
        log.info("message:{}",message);
    }


}
