package com.flx.cases.mybatis.plus.dao;

import com.flx.springboot.scaffold.mybatis.plus.annotation.DaoMapper;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseDao;
import com.flx.cases.mybatis.plus.entity.StudentDO;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/24 17:25
 * @Description:
 */
@DaoMapper
public interface StudentDao extends BaseDao<StudentDO> {

    @Select("select count(1) from scaffold_student")
    int countStudent();

}
