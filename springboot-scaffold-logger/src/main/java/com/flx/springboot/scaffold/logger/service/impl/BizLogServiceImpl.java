package com.flx.springboot.scaffold.logger.service.impl;

import com.flx.springboot.scaffold.logger.dao.BizLogDao;
import com.flx.springboot.scaffold.logger.entity.BizLogger;
import com.flx.springboot.scaffold.logger.service.BizLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Fenglixiong
 * @Create 2018.11.29 18:31
 * @Description
 **/
@Slf4j
@Service
public class BizLogServiceImpl implements BizLogService {


    @Autowired
    private BizLogDao bizLogDao;

    @Override
    public BizLogger saveLog(BizLogger bizLogger) {
        return bizLogDao.save(bizLogger);
    }
}
