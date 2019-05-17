package com.flx.scaffold.jdbc.mybatis.service;

import com.flx.scaffold.jdbc.common.page.PageQuery;
import com.flx.scaffold.jdbc.common.page.PageResult;
import com.flx.scaffold.jdbc.mybatis.model.Worker;

public interface WorkerService {

    PageResult<Worker> list1(PageQuery<Worker> pageQuery);

    PageResult<Worker> list2(PageQuery<Worker> pageQuery);

    Worker select(Long id);

    Long save(Worker worker);

    Long update(Worker worker);

    int delete(Long id);

}
