package com.flx.springboot.scaffold.application.test;

import com.flx.springboot.scaffold.common.http.SimpleHttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/15 12:03
 * @Description:
 */
public class TestPost {

    public static void main(String[] args) throws Exception {
        String url1 = "http://127.0.0.1:8023/api/basic/tenant/getCurrentTenantCode";
        String url2 = "http://127.0.0.1:8023//api/config/group/RCS/getConfigByKey";
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("warehouseId",1);
        paramMap.put("groupInstanceId","000");
        paramMap.put("key","RCS_MAX_COUNT");
        System.out.println("--------post1");
        System.out.println(SimpleHttpUtils.doGet(url1,null));
        System.out.println("--------post2");
        System.out.println(SimpleHttpUtils.doGet(url2,paramMap));
    }

}
