package com.flx.springboot.scaffold.logger.service;

import com.flx.springboot.scaffold.logger.entity.BizLogger;

/**
 * @Author Fenglixiong
 * @Create 2018.11.29 18:31
 * @Description
 **/
public interface BizLogService {

    BizLogger saveLog(BizLogger bizLogger);

}
