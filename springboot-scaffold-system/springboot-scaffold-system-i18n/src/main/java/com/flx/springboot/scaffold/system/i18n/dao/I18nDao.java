package com.flx.springboot.scaffold.system.i18n.dao;

import com.flx.springboot.scaffold.mybatis.plus.annotation.DaoMapper;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseDao;
import com.flx.springboot.scaffold.system.i18n.entity.I18nDO;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/24 17:25
 * @Description:
 */
@DaoMapper
public interface I18nDao extends BaseDao<I18nDO> {

}
