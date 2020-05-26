package com.flx.springboot.scaffold.mybatis.service.impl;

import com.flx.springboot.scaffold.mybatis.dao.FatherDao;
import com.flx.springboot.scaffold.mybatis.model.Father;
import com.flx.springboot.scaffold.mybatis.service.FatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Fenglixiong
 * @Create 2019.01.22 19:33
 * @Description
 **/
@Slf4j
@Service
public class FatherServiceImpl implements FatherService {

    @Autowired
    private FatherDao fatherDao;

    @Override
    public Father select(Long id) {
        return fatherDao.selectByPrimaryKey(id);
    }

    @Override
    public Long save(Father father) {
        fatherDao.insert(father);
        return father.getId();
    }

    @Override
    public Long update(Father father) {
        fatherDao.updateByPrimaryKeySelective(father);
        return father.getId();
    }

    @Override
    public int delete(Long id) {
        return fatherDao.deleteByPrimaryKey(id);
    }
}
