package com.flx.springboot.scaffold.simple.rest.controller;

import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.simple.rest.entity.User;
import com.flx.springboot.scaffold.web.core.annotation.RequestLimit;
import com.flx.springboot.scaffold.web.core.annotation.RestController;
import com.flx.springboot.scaffold.web.core.annotation.SysLog;
import com.flx.springboot.scaffold.web.core.enums.BizTypeEnum;
import com.flx.springboot.scaffold.web.core.enums.OperateTypeEnum;
import com.flx.springboot.scaffold.web.core.exception.element.BizException;
import com.flx.springboot.scaffold.web.core.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2018.11.07 16:32
 * @Description
 **/
@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class SimpleController {

    @RequestLimit(count = 100)
    @GetMapping(value = "/hello")
    @SysLog(bizType = BizTypeEnum.OTHER,operateType = OperateTypeEnum.OTHER,bizId = "id",remark = "问候方法")
    public ResultResponse hello(@RequestParam String id,
                                @RequestParam("someone")String someone)throws Exception{
        log.info("hello,{}",someone);
        if(id.equals("111")){
            throw new BizException("测试错误");
        }
        return ResultResponse.ok("Hello,"+someone);
    }

    @GetMapping(value = "/greet")
    public ResultResponse<String> greet(@RequestParam("someone")String someone){
        log.info("greet,{}",someone);
        return ResultResponse.ok("Greet "+someone);
    }

    @GetMapping(value = "/user/getUser")
    public ResultResponse getUser(){
        return ResultResponse.ok(getUserList());
    }

    @PostMapping(value = "/user/postUser")
    public ResultResponse postUser(@RequestBody User user){
        log.info("user:{}", JsonUtils.toJsonMsg(user));
        return ResultResponse.ok(getUserList());
    }

    private List<User> getUserList() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L,"jack",22,"footBall"));
        users.add(new User(2L,"marry",24,"bastBall"));
        return users;
    }

}
