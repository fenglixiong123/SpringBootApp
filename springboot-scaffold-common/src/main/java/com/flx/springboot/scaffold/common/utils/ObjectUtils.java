package com.flx.springboot.scaffold.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/8 18:37
 * @Description:
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

    /**
     * @param cascadeFieldName 带路径的属性名或简单属性名
     * @param entity                对象
     * @return 属性值
     * @MethodName : getFieldValueByCascadeName
     * @Description :
     * 根据带路径或不带路径的属性名获取属性值
     * 即接受简单属性名，如userName等，又接受带路径的属性名，如student.department.name等
     */
    public static Object getFieldValueByCascadeName(String cascadeFieldName, Object entity) throws Exception {

        String[] attributes = cascadeFieldName.split("\\.");
        if (attributes.length == 1) {
            return getFieldValueByName(cascadeFieldName, entity);
        } else {
            Object fieldObj = getFieldValueByName(attributes[0], entity);
            String subFieldNameSequence = cascadeFieldName.substring(cascadeFieldName.indexOf(".") + 1);
            return getFieldValueByCascadeName(subFieldNameSequence, fieldObj);
        }

    }

    /**
     * @param fieldName 字段名
     * @param o         对象
     * @return 字段值
     * @MethodName : getFieldValueByName
     * @Description : 根据字段名获取字段值
     */
    public static Object getFieldValueByName(String fieldName, Object o) throws Exception {

        Field field = getFieldByName(fieldName, o.getClass());

        if (field != null) {
            field.setAccessible(true);
            return field.get(o);
        } else {
            throw new Exception("The class "+ o.getClass().getSimpleName() +" does not exist field name "+ fieldName +"!");
        }

    }

    /**
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @param entity          对象
     * @MethodName : setFieldValueByName
     * @Description : 根据字段名给对象的字段赋值
     */
    public static void setFieldValueByName(String fieldName, Object fieldValue, Object entity) throws Exception {

        Field field = getFieldByName(fieldName, entity.getClass());
        if (field != null) {
            field.setAccessible(true);
            //获取字段类型
            Class<?> fieldType = field.getType();

            //根据字段类型给字段赋值
            String value = fieldValue + "";
            value = value.trim();
            if (String.class == fieldType) {
                field.set(entity, value);
            } else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
                if (StringUtils.isEmpty(value)) {
                    field.set(entity, null);
                } else {
                    field.set(entity, Integer.parseInt(value));
                }
            } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
                if (StringUtils.isEmpty(value)) {
                    field.set(entity, null);
                } else {
                    field.set(entity, Long.valueOf(value));
                }
            } else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
                if (StringUtils.isEmpty(value)) {
                    field.set(entity, null);
                } else {
                    field.set(entity, Float.valueOf(value));
                }
            } else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
                if (StringUtils.isEmpty(value)) {
                    field.set(entity, null);
                } else {
                    field.set(entity, Short.valueOf(value));
                }
            } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
                if (StringUtils.isEmpty(value)) {
                    field.set(entity, null);
                } else {
                    field.set(entity, Double.valueOf(value));
                }
            } else if (Character.TYPE == fieldType) {
                if ((fieldValue != null) && (fieldValue.toString().length() > 0)) {
                    field.set(entity, fieldValue.toString().charAt(0));
                }
            } else if (Date.class == fieldType) {
                if ((fieldValue != null) && (fieldValue.toString().length() > 0)) {
                    field.set(entity, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fieldValue.toString()));
                }
            } else if (fieldType.isEnum()) {
                if ((fieldValue != null) && (fieldValue.toString().length() > 0)) {
                    Method m = fieldType.getMethod("valueOf", String.class);
                    Object writeValue = m.invoke(entity, value);
                    field.set(entity, writeValue);
                }
            } else {
                field.set(entity, fieldValue);
            }
        } else {
            throw new Exception("The class " + entity.getClass().getSimpleName() +" does not exist field name "+fieldName+"!");
        }
    }

    /**
     * @param fieldName 字段名
     * @param clazz     包含该字段的类
     * @return 字段
     * @MethodName : getFieldByName
     * @Description : 根据字段名获取字段
     */
    private static Field getFieldByName(String fieldName, Class<?> clazz) {

        Field[] selfFields = clazz.getDeclaredFields();
        for (Field field : selfFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null && superClazz != Object.class) {
            return getFieldByName(fieldName, superClazz);
        }
        return null;
    }

}
