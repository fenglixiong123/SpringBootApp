package com.flx.springboot.scaffold.mybatis.plus.page;

/**
 * @author fanzhen
 * @date 2018/8/22
 */

import lombok.Data;

import java.util.Map;

/**
 * @author fanzhen
 * @date2018-08-22-10:13
 */
@Data
public class QueryAndPage extends PageRequest {

    private Map<String, Object> query;

}
