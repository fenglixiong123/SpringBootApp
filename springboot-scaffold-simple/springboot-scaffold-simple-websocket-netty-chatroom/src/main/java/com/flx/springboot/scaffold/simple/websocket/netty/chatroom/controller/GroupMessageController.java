package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.controller;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.common.utils.validate.ValidationResult;
import com.flx.springboot.scaffold.common.utils.validate.ValidationUtils;
import com.flx.springboot.scaffold.mybatis.plus.entity.StateVO;
import com.flx.springboot.scaffold.mybatis.plus.page.QueryAndPage;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.service.GroupMessageService;
import com.flx.springboot.scaffold.simple.websocket.netty.chatroom.biz.vo.WebGroupMessageVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author Fenglixiong
 * @Create 2020/8/2 21:36
 * @Description
 **/
@Api(tags = "群消息管理")
@RestController
@RequestMapping("/web/groupMessage")
public class GroupMessageController {

    @Autowired
    private GroupMessageService groupMessageService;

    @GetMapping("/get/{id}")
    public ResultResponse get(@PathVariable Long id){
        try {
            return ResultResponse.success(groupMessageService.get(id));
        }catch (Exception e){
            return ResultResponse.error(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResultResponse add(@RequestBody WebGroupMessageVO entity){
        try {
            ValidationResult validate = ValidationUtils.validate(entity);
            if(!validate.isSuccess()){
                return validate.toResponse();
            }
            return ResultResponse.success(groupMessageService.add(entity));
        }catch (Exception e){
            return ResultResponse.error(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResultResponse update(@RequestBody WebGroupMessageVO entity){
        try {
            return ResultResponse.success(groupMessageService.update(entity));
        }catch (Exception e){
            return ResultResponse.error(e.getMessage());
        }
    }

    @PutMapping("/updateState")
    public ResultResponse updateState(@RequestBody StateVO stateVO){
        try {
            return ResultResponse.success(groupMessageService.updateState(stateVO));
        }catch (Exception e){
            return ResultResponse.error(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResultResponse delete(@PathVariable Long id){
        try {
            return ResultResponse.success(groupMessageService.delete(id));
        }catch (Exception e){
            return ResultResponse.error(e.getMessage());
        }
    }

    @PostMapping("/query")
    public ResultResponse query(@RequestBody Map<String,Object> query){
        try {
            return ResultResponse.success(groupMessageService.query(query));
        }catch (Exception e){
            return ResultResponse.error(e.getMessage());
        }
    }

    @PostMapping("/queryAll")
    public ResultResponse queryAll(@RequestBody Map<String,Object> query){
        try {
            return ResultResponse.success(groupMessageService.queryAll(query));
        }catch (Exception e){
            return ResultResponse.error(e.getMessage());
        }
    }

    @PostMapping("/queryPage")
    public ResultResponse queryPage(@RequestBody QueryAndPage query){
        try {
            return ResultResponse.success(groupMessageService.queryPage(query));
        }catch (Exception e){
            return ResultResponse.error(e.getMessage());
        }
    }

}
