package com.flx.springboot.scaffold.mybatis.plus.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flx.springboot.scaffold.mybatis.plus.common.ColumnRejectUtil;
import com.flx.springboot.scaffold.mybatis.plus.common.QueryConditionBuilder;
import com.flx.springboot.scaffold.mybatis.plus.common.UpdateConditionBuilder;
import com.flx.springboot.scaffold.mybatis.plus.constants.PlusConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 公共Manager类
 *
 * @author fanzhen
 * @date 2018-08-09 19:59
 */
@Slf4j
@SuppressWarnings("all")
public abstract class BaseManager<T extends BaseDO, S extends BaseDao> {

    @Autowired
    protected S dao;

    private Class<T> modelClass;

    public BaseManager() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    protected QueryConditionBuilder<T> getConditionBuilder() {
        return new QueryConditionBuilder<T>();
    }

    protected UpdateConditionBuilder<T> getUpdateConditionBuilder() {
        return new UpdateConditionBuilder<>();
    }


    protected Long add(T model) {
        model.setCreateTime(new Date());
        if (StringUtils.isEmpty(model.getCreateUser())) {
            model.setCreateUser("admin");
        }
        if (StringUtils.isEmpty(model.getUpdateUser())) {
            model.setUpdateUser("admin");
        }
        model.setUpdateTime(new Date());
        dao.insert(model);
        return model.getId();
    }

    protected int update(T model, QueryWrapper queryWrapper) throws Exception {
        model.setUpdateTime(new Date());
        if (StringUtils.isEmpty(model.getUpdateUser())) {
            model.setUpdateUser("admin");
        }
        return dao.update(model, queryWrapper);
    }

    protected int update(T model, UpdateWrapper updateWrapper) throws Exception {
        model.setUpdateTime(new Date());
        if (StringUtils.isEmpty(model.getUpdateUser())) {
            model.setUpdateUser("admin");
        }
        return dao.update(model, updateWrapper);
    }

    protected Integer updateById(T model) throws Exception {
        model.setUpdateTime(new Date());
        if (StringUtils.isEmpty(model.getUpdateUser())) {
            model.setUpdateUser("admin");
        }
        return dao.updateById(model);
    }

    protected Integer updateNullById(T model) throws Exception {
        model.setUpdateTime(new Date());
        if (StringUtils.isEmpty(model.getUpdateUser())) {
            model.setUpdateUser("admin");
        }
        UpdateWrapper<T> updateWrapper = getUpdateConditionBuilder()
                .query("id", model.getId())
                .readObject(model).build(true);
        return dao.update(model, updateWrapper);
    }

    protected Integer updateByCode(T model, String codeName, String codeValue) throws Exception {
        model.setUpdateTime(new Date());
        if (StringUtils.isEmpty(model.getUpdateUser())) {
            model.setUpdateUser("admin");
        }
        QueryWrapper queryWrapper = getConditionBuilder().query(codeName, codeValue).build(false);
        return dao.update(model, queryWrapper);
    }

    protected Integer updateNullByCode(T model, String codeName, String codeValue) throws Exception {
        model.setUpdateTime(new Date());
        if (StringUtils.isEmpty(model.getUpdateUser())) {
            model.setUpdateUser("admin");
        }
        UpdateWrapper queryWrapper = getUpdateConditionBuilder()
                .query(codeName, codeValue)
                .readObject(model)
                .build(false);
        return dao.update(model, queryWrapper);
    }

    protected Integer addByList(List<T> modelList) throws Exception {
        int result = 1;
        for (T model : modelList) {
            
            model.setCreateTime(new Date());
            if (StringUtils.isEmpty(model.getCreateUser())) {
                model.setCreateUser("admin");
            }
            
            if (StringUtils.isEmpty(model.getUpdateUser())) {
                model.setUpdateUser("admin");
            }
            model.setUpdateTime(new Date());
            try {
                dao.insert(model);
            } catch (Exception e) {
                log.error("Add model fail,message=" + e.getMessage());
                throw e;
            }
        }
        return result;
    }

    protected IPage<T> queryPage(Integer pageNum, Integer pageSize, Map<String, Object> query) throws Exception {
        Page<T> modelPage = new Page<>(pageNum, pageSize);
        QueryWrapper<T> queryWrapper = getConditionBuilder().query(query).build(true);
        queryWrapper.orderByAsc("id");
        return dao.selectPage(modelPage, queryWrapper);
    }

    protected IPage<T> queryPage(Integer pageNum, Integer pageSize, Object query) throws Exception {
        Page<T> modelPage = new Page<T>(pageNum, pageSize);
        QueryWrapper<T> queryWrapper = getConditionBuilder().query(query).build(false);
        queryWrapper.orderByAsc("id");
        return dao.selectPage(modelPage, queryWrapper);
    }

    protected List<T> query(Object query) throws Exception {
        Page<T> modelPage = new Page<T>(1, PlusConstant.MAX_PAGE_NUM);
        QueryWrapper<T> queryWrapper = getConditionBuilder().query(query).build(false);
        queryWrapper.orderByAsc("id");
        queryWrapper.select(ColumnRejectUtil.columnReject(modelClass));
        IPage<T> modelDOList = dao.selectPage(modelPage, queryWrapper);
        if (modelDOList == null || modelDOList.getRecords()==null) {
            return new ArrayList<>();
        }
        return modelDOList.getRecords();
    }

    protected List<T> querySomeColumns(Object query, String[] columns) throws Exception {
        Page<T> modelPage = new Page<>(1, PlusConstant.MAX_PAGE_NUM);
        QueryWrapper<T> queryWrapper = getConditionBuilder().query(query).build(false);
        queryWrapper.orderByAsc("id");
        if (columns != null && columns.length > 0) {
            queryWrapper.select(columns);
        }
        IPage<T> modelDOList = dao.selectPage(modelPage, queryWrapper);
        if (modelDOList == null || modelDOList.getRecords()==null) {
            return new ArrayList<>();
        }
        return modelDOList.getRecords();
    }

    protected List<T> query(Map<String, Object> query) throws Exception {
        Page<T> modelPage = new Page<>(1, PlusConstant.MAX_PAGE_NUM);
        QueryWrapper<T> queryWrapper = getConditionBuilder().query(query).build(false);
        queryWrapper.orderByAsc("id");
        queryWrapper.select(ColumnRejectUtil.columnReject(modelClass));
        IPage<T> modelDOList = dao.selectPage(modelPage, queryWrapper);
        if (modelDOList == null || modelDOList.getRecords()==null) {
            return new ArrayList<>();
        }
        return modelDOList.getRecords();
    }

    protected List<T> querySomeColumns(Map<String, Object> query, String[] columns) throws Exception {
        Page<T> modelPage = new Page<>(1, PlusConstant.MAX_PAGE_NUM);
        QueryWrapper<T> queryWrapper = getConditionBuilder().query(query).build(false);
        queryWrapper.orderByAsc("id");
        if (columns != null && columns.length > 0) {
            queryWrapper.select(columns);
        }
        IPage<T> modelDOList = dao.selectPage(modelPage, queryWrapper);
        if (modelDOList == null || modelDOList.getRecords()==null) {
            return new ArrayList<>();
        }
        return modelDOList.getRecords();
    }

    protected List<T> queryAllColumns(Map<String, Object> query) throws Exception {
        Page<T> modelPage = new Page<T>(1, PlusConstant.MAX_PAGE_NUM);
        QueryWrapper<T> queryWrapper = getConditionBuilder().query(query).build(false);
        queryWrapper.orderByAsc("id");
        IPage<T> modelDOList = dao.selectPage(modelPage, queryWrapper);
        if (modelDOList == null || modelDOList.getRecords()==null) {
            return new ArrayList<>();
        }
        return modelDOList.getRecords();
    }
}
