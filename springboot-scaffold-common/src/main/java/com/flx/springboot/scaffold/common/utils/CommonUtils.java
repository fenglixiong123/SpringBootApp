package com.flx.springboot.scaffold.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author Fenglixiong
 * @Create 2018.11.09 23:19
 * @Description
 **/
public class CommonUtils {

    public static boolean isBlank(String s){
        return StringUtils.isBlank(s);
    }

    public static boolean isNotBlank(String s){
        return !isBlank(s);
    }

    public static boolean isEmpty(List list){
        return (list==null||list.isEmpty());
    }

    public static boolean isNotEmpty(List list){
        return !isEmpty(list);
    }

    public static boolean isEmpty(Set set){
        return (set==null||set.isEmpty());
    }

    public static boolean isNotEmpty(Set set){
        return !isEmpty(set);
    }

    public static boolean isEmpty(Map map){
        return (map==null||map.isEmpty());
    }

    public static boolean isNotEmpty(Map map){
        return !isEmpty(map);
    }

    public static boolean isEmpty(Object[] params){
        return (params==null||params.length==0);
    }

    public static boolean isNotEmpty(Object[] params){
        return !isEmpty(params);
    }

}
