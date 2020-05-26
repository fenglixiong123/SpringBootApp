package com.flx.springboot.scaffold.mybatis.service;


import com.flx.springboot.scaffold.mybatis.model.Father;

public interface FatherService {

    Father select(Long id);

    Long save(Father father);

    Long update(Father father);

    int delete(Long id);

}
