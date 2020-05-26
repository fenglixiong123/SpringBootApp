package com.flx.springboot.scaffold.mybatis.dao;

import com.flx.springboot.scaffold.mybatis.model.Father;
import com.flx.springboot.scaffold.mybatis.model.FatherExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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