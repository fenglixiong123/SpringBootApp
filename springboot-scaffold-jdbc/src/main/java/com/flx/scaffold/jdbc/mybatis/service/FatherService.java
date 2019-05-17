package com.flx.scaffold.jdbc.mybatis.service;

import com.flx.scaffold.jdbc.mybatis.model.Father;

public interface FatherService {

    Father select(Long id);

    Long save(Father father);

    Long update(Father father);

    int delete(Long id);

}
