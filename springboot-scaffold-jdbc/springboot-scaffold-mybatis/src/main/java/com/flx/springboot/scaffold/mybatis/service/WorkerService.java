package com.flx.springboot.scaffold.mybatis.service;


import com.flx.springboot.scaffold.mybatis.common.PageQuery;
import com.flx.springboot.scaffold.mybatis.common.PageResult;
import com.flx.springboot.scaffold.mybatis.model.Worker;

public interface WorkerService {

    PageResult<Worker> list1(PageQuery<Worker> pageQuery);

    PageResult<Worker> list2(PageQuery<Worker> pageQuery);

    Worker select(Long id);

    Long save(Worker worker);

    Long update(Worker worker);

    int delete(Long id);

}
