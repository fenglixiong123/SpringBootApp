package com.flx.springboot.scaffold.mybatis.controller;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.mybatis.common.PageQuery;
import com.flx.springboot.scaffold.mybatis.common.PageResult;
import com.flx.springboot.scaffold.mybatis.model.Father;
import com.flx.springboot.scaffold.mybatis.model.Teacher;
import com.flx.springboot.scaffold.mybatis.model.Worker;
import com.flx.springboot.scaffold.mybatis.service.FatherService;
import com.flx.springboot.scaffold.mybatis.service.TeacherService;
import com.flx.springboot.scaffold.mybatis.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Fenglixiong
 * @Create 2019.01.22 16:48
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/mybatis")
public class MybatisController {

    @Autowired
    private FatherService fatherService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private WorkerService workerService;

    /**
     * 分页展示实例一
     * @param pageQuery
     * @return
     */
    @GetMapping("/workList1")
    public ResultResponse<PageResult<Worker>> workList1(@RequestBody PageQuery<Worker> pageQuery){
        log.info(">>>>>>查询参数：{}", JsonUtils.toJsonMsg(pageQuery));
        PageResult<Worker> workerPage = workerService.list1(pageQuery);
        return ResultResponse.success(workerPage);
    }

    /**
     * 分页展示实例二
     * @param pageQuery
     * @return
     */
    @GetMapping("/workList2")
    public ResultResponse<PageResult<Worker>> workList2(@RequestBody PageQuery<Worker> pageQuery){
        log.info(">>>>>>查询参数：{}", JsonUtils.toJsonMsg(pageQuery));
        PageResult<Worker> workerPage = workerService.list2(pageQuery);
        return ResultResponse.success(workerPage);
    }


    @PostMapping("/saveFather")
    public ResultResponse<Long> saveStudent(@RequestBody Father father){
        Long id = fatherService.save(father);
        log.info("save successful:{}",id);
        return ResultResponse.success(id);
    }

    @PostMapping("/saveTeacher")
    public ResultResponse<Long> saveTeacher(@RequestBody Teacher teacher){
        Long id = teacherService.save(teacher);
        log.info("save successful:{}",id);
        return ResultResponse.success(id);
    }

    @PostMapping("/saveWorker")
    public ResultResponse<Long> saveWorker(@RequestBody @Valid Worker worker){
        Long id = workerService.save(worker);
        log.info("save successful:{}",id);
        return ResultResponse.success(id);
    }

}
