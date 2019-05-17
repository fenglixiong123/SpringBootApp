package com.flx.springboot.scaffold.logger.dao;

import com.flx.springboot.scaffold.logger.entity.BizLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author Fenglixiong
 * @Create 2018.11.29 18:29
 * @Description
 **/
@Repository
public interface BizLogDao extends JpaRepository<BizLogger,Long>, JpaSpecificationExecutor<BizLogger> {
}
