package com.flx.scaffold.jdbc.mybatis.service.impl;

import com.flx.scaffold.jdbc.common.page.PageQuery;
import com.flx.scaffold.jdbc.common.page.PageResult;
import com.flx.scaffold.jdbc.mybatis.dao.WorkerDao;
import com.flx.scaffold.jdbc.mybatis.model.Worker;
import com.flx.scaffold.jdbc.mybatis.model.WorkerExample;
import com.flx.scaffold.jdbc.mybatis.service.WorkerService;
import com.flx.springboot.scaffold.common.utils.code.ColumnUtils;
import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019.01.22 17:25
 * @Description
 **/
@Slf4j
@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerDao workerDao;

    @Override
    public PageResult<Worker> list1(PageQuery<Worker> pageQuery) {
        WorkerExample example = new WorkerExample();
        Worker worker = pageQuery.getEntity();
        example.createCriteria().andNameLike("%"+worker.getName()+"%");
        long count = workerDao.countByExample(example);
        List<Worker> workers = new ArrayList<>();
        if(count>0){
            Integer offset = (pageQuery.getPage()-1)*pageQuery.getPageSize();
            example.setOffset(offset.longValue());
            example.setLimit(pageQuery.getPageSize());
            workers = workerDao.selectByExample(example);
        }
        return new PageResult<>(pageQuery.getPage(),pageQuery.getPageSize(),count,workers);
    }

    @Override
    public PageResult<Worker> list2(PageQuery<Worker> pageQuery) {
        String orderBy = ColumnUtils.camelToUnderline(pageQuery.getOrder())+" "+pageQuery.getOrderDesc();
        log.info("orderBy:{}",orderBy);
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getPageSize());
        WorkerExample example = new WorkerExample();
        List<Worker> workers = workerDao.selectByExample(example);
        log.info("works:{}",JsonUtils.toJsonMsg(workers));
        PageInfo<Worker> pageInfo = new PageInfo<>(workers);
        log.info(JsonUtils.toJsonMsg(pageInfo));
        return new PageResult<>(pageInfo);
    }

    @Override
    public Worker select(Long id) {
        return workerDao.selectByPrimaryKey(id);
    }

    @Override
    public Long save(Worker worker) {
        workerDao.insert(worker);
        return worker.getId();
    }

    @Override
    public Long update(Worker worker) {
        workerDao.updateByPrimaryKeySelective(worker);
        return worker.getId();
    }

    @Override
    public int delete(Long id) {
        return workerDao.deleteByPrimaryKey(id);
    }
}
