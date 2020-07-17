package com.flx.springboot.scaffold.common.servlet;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/17 17:42
 * @Description:
 */
public class BeanUtils {

    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        try {
            T entity = targetClass.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, entity);
            return entity;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T copyProperties(Object source, Class<T> targetClass, String... ignoreProperties) {
        try {
            T entity = targetClass.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, entity, ignoreProperties);
            return entity;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
