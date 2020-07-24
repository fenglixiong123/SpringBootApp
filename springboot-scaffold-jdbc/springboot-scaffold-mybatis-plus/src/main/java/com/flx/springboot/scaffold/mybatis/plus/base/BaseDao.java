package com.flx.springboot.scaffold.mybatis.plus.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 公共Dao层
 *
 * @author fenglixiong
 * @date 2018-08-09 20:00
 */
public interface BaseDao<T extends BaseDO> extends BaseMapper<T> {

}
