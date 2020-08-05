package com.flx.springboot.scaffold.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @Author: Fenglixiong
 * @Date: 2020/6/10 12:06
 * @Description:
 */
public class CommonUtils {

    public static void main(String[] args) {

        String entityPackage = "com.flx.springboot.scaffold.mybatis.plus.entity";
        entityPackage = Objects.requireNonNull(entityPackage).replaceAll("\\.","/");
        System.out.println(entityPackage);
    }

    public static String defaultIfNull(String source, String defaultValue) {
        return StringUtils.isNotBlank(source) ? source : defaultValue;
    }

    public static <T> T defaultIfNull(T object, T defaultValue) {
        return object != null ? object : defaultValue;
    }

}
