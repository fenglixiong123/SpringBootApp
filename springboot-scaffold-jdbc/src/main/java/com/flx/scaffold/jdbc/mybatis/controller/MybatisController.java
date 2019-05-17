package com.flx.scaffold.jdbc.mybatis.controller;

import com.flx.scaffold.jdbc.common.page.PageQuery;
import com.flx.scaffold.jdbc.common.page.PageResult;
import com.flx.scaffold.jdbc.mybatis.model.Father;
import com.flx.scaffold.jdbc.mybatis.model.Teacher;
import com.flx.scaffold.jdbc.mybatis.model.Worker;
import com.flx.scaffold.jdbc.mybatis.service.FatherService;
import com.flx.scaffold.jdbc.mybatis.service.TeacherService;
import com.flx.scaffold.jdbc.mybatis.service.WorkerService;
import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.web.core.result.ResultResponse;
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
        return ResultResponse.ok(workerPage);
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
        return ResultResponse.ok(workerPage);
    }


    @PostMapping("/saveFather")
    public ResultResponse<Long> saveStudent(@RequestBody Father father){
        Long id = fatherService.save(father);
        log.info("save successful:{}",id);
        return ResultResponse.ok(id);
    }

    @PostMapping("/saveTeacher")
    public ResultResponse<Long> saveTeacher(@RequestBody Teacher teacher){
        Long id = teacherService.save(teacher);
        log.info("save successful:{}",id);
        return ResultResponse.ok(id);
    }

    @PostMapping("/saveWorker")
    public ResultResponse<Long> saveWorker(@RequestBody @Valid Worker worker){
        Long id = workerService.save(worker);
        log.info("save successful:{}",id);
        return ResultResponse.ok(id);
    }

}
