package com.flx.springboot.scaffold.mybatis.plus.page;

import lombok.Data;

import java.util.Map;

/**
 * @author fenglixiong
 * @date2018-08-22-10:13
 */
@Data
public class QueryAndPage extends PageRequest {

    private Map<String, Object> query;

}
