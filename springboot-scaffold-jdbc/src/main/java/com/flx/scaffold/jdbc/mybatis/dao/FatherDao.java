package com.flx.scaffold.jdbc.mybatis.dao;

import com.flx.scaffold.jdbc.mybatis.model.Father;
import com.flx.scaffold.jdbc.mybatis.model.FatherExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FatherDao {
    long countByExample(FatherExample example);

    int deleteByExample(FatherExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Father record);

    int insertSelective(Father record);

    List<Father> selectByExample(FatherExample example);

    Father selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Father record, @Param("example") FatherExample example);

    int updateByExample(@Param("record") Father record, @Param("example") FatherExample example);

    int updateByPrimaryKeySelective(Father record);

    int updateByPrimaryKey(Father record);
}