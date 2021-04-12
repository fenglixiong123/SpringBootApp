package com.flx.springboot.scaffold.mybatis.plus.dao;

import com.flx.springboot.scaffold.mybatis.plus.annotation.DaoMapper;
import com.flx.springboot.scaffold.mybatis.plus.entity.CommonVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/12 20:50
 * @Description:
 */
@DaoMapper
public interface CommonDao {

    /**
     * 根据Id获取模糊实体
     *
     * @param id
     * @param code
     * @param name
     * @param tableName
     * @return
     * @throws Exception
     */
    @MapKey("id")
    CommonVO getCommonVO(@Param("id") Long id, @Param("code") String code, @Param("name") String name, @Param("tableName") String tableName) throws Exception;


    /**
     * 根据sql删除
     *
     * @param sql
     * @param tableName
     * @return
     * @throws Exception
     */
    Integer deleteByMap(@Param("sql") String sql, @Param("tableName") String tableName) throws Exception;

    /**
     * 根据sql更新
     *
     * @param sql
     * @param tableName
     * @return
     * @throws Exception
     */
    Integer invalidByMap(@Param("sql") String sql, @Param("lastUpdatedUser") String lastUpdatedUser, @Param("tableName") String tableName) throws Exception;

    /**
     * 根据sql更新
     *
     * @param sql
     * @param tableName
     * @return
     * @throws Exception
     */
    Integer effectiveByMap(@Param("sql") String sql, @Param("lastUpdatedUser") String lastUpdatedUser, @Param("tableName") String tableName) throws Exception;


    /**
     * 根据SQL查询语句查询Id列表
     *
     * @param sqlStr
     * @param tableName
     * @return
     * @throws Exception
     */
    Set<Long> getIdListBySql(@Param("sqlStr") String sqlStr, @Param("tableName") String tableName) throws Exception;


    /**
     * 获取数量
     *
     * @param sqlStr
     * @param tableName
     * @return
     * @throws Exception
     */
    int getDimCountBySql(@Param("sqlStr") String sqlStr, @Param("tableName") String tableName) throws Exception;


    /**
     * 根据SQL查询语句查询Id列表
     *
     * @param sqlStr
     * @param tableName
     * @return
     * @throws Exception
     */
    List<CommonVO> getDimListBySql(@Param("code") String code, @Param("sqlStr") String sqlStr, @Param("tableName") String tableName) throws Exception;


    /**
     * 根据SQL语句查询模糊实体，结果映射为Map，key为Id
     *
     * @param code
     * @param name
     * @param sqlStr
     * @param tableName
     * @return
     * @throws Exception
     */
    @MapKey("id")
    Map<Long, CommonVO> getDimObjectMap(@Param("code") String code, @Param("name") String name, @Param("sqlStr") String sqlStr, @Param("tableName") String tableName) throws Exception;

    /**
     * 根据SQL语句查询模糊实体
     *
     * @param code
     * @param name
     * @param sqlStr
     * @param tableName
     * @return
     * @throws Exception
     */
    @MapKey("id")
    Set<CommonVO> getDimObjectList(@Param("code") String code, @Param("name") String name, @Param("sqlStr") String sqlStr, @Param("tableName") String tableName) throws Exception;


    /**
     * 根据SQL语句查询模糊实体，key为Id，没有名字
     *
     * @param code
     * @param sqlStr
     * @param tableName
     * @return
     * @throws Exception
     */
    @MapKey("id")
    Map<Long, CommonVO> getDimObjectListNoName(@Param("code") String code, @Param("sqlStr") String sqlStr, @Param("tableName") String tableName) throws Exception;


    /**
     * 查询Id是否存在
     *
     * @param id
     * @param tableName
     * @return
     * @throws Exception
     */
    @MapKey("id")
    Integer isExist(@Param("id") Long id, @Param("tableName") String tableName) throws Exception;

    /**
     * 获取数据状态
     *
     * @param id
     * @param tableName
     * @return
     * @throws Exception
     */
    String getState(@Param("id") Long id, @Param("tableName") String tableName) throws Exception;

}
