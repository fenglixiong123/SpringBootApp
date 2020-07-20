package com.flx.springboot.scaffold.common.utils.base;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: fenglixiong
 * @Date: 2019/12/2 11:39
 * @Version 2.0.0
 */
public class ReflectUtils {

    /**
     * 根据旧对象生成新对象
     * @param dest
     * @param addProperties
     * @return
     * @throws Exception
     */
    public static  Object getTarget(Object dest, Map<String, Object> addProperties) throws Exception {
        if(addProperties==null){
            addProperties=new HashMap<>();
        }
        //获取field列表
        PropertyUtilsBean propertyUtilsBean =new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(dest);
        Map<String, Class> propertyMap = new HashMap<>();
        for(PropertyDescriptor d : descriptors) {
            if(!"class".equalsIgnoreCase(d.getName())) {
                propertyMap.put(d.getName(), d.getPropertyType());
            }
        }
        //增加额外属性
        addProperties.forEach((k, v) -> propertyMap.put(k, v.getClass()));
        //生成一个DynamicBean
        DynamicBean dynamicBean =new DynamicBean(dest.getClass(), propertyMap);
        //已有字段赋值
        for (String k:propertyMap.keySet()){
            if(!addProperties.containsKey(k)) {
                dynamicBean.setValue(k, propertyUtilsBean.getNestedProperty(dest, k));
            }
        }
        //新增字段赋值
        for (String k:addProperties.keySet()){
            dynamicBean.setValue(k, addProperties.get(k));
        }
        //获取赋值后新生成的对象
        return dynamicBean.getTarget();
    }

    /**
     * 根据类赋值生成实体
     * @param dest
     * @param addProperties
     * @param <T>
     * @return
     * @throws Exception
     */
    static <T> Object getTargetByClass(Class<T> dest, Map<String, Object> addProperties) throws Exception {
        if(addProperties==null){
            addProperties=new HashMap<>();
        }

        //获取fieldMap
        Map<String,Class> fieldMap=new HashMap<>();
        Field[] fieldArr=dest.getDeclaredFields();
        for (Field f:fieldArr){
            fieldMap.put(f.getName(), f.getClass());
        }

        //生成一个DynamicBean
        DynamicBean dynamicBean =new DynamicBean(dest, fieldMap);
        for (String k:addProperties.keySet()){
            if(fieldMap.containsKey(k)){
                dynamicBean.setValue(k, addProperties.get(k));
            }
        }


        return dynamicBean.getTarget();
    }

    public static class DynamicBean {
        /**
         * 目标对象
         */
        private Object target;

        /**
         * 属性集合
         */
        private BeanMap beanMap;

        DynamicBean(Class superclass, Map<String, Class> propertyMap) {
            this.target = generateBean(superclass, propertyMap);
            this.beanMap = BeanMap.create(this.target);
        }


        /**
         * bean 添加属性和值
         *
         * @param property
         * @param value
         */
        void setValue(String property, Object value) {
            beanMap.put(property, value);
        }

        /**
         * 获取属性值
         *
         * @param property
         * @return
         */
        public Object getValue(String property) {
            return beanMap.get(property);
        }

        /**
         * 获取对象
         *
         * @return
         */
        Object getTarget() {
            return this.target;
        }


        /**
         * 根据属性生成对象
         *
         * @param superclass
         * @param propertyMap
         * @return
         */
        private Object generateBean(Class superclass, Map<String, Class> propertyMap) {
            if(propertyMap==null){
                propertyMap=new HashMap<>();
            }
            BeanGenerator generator =new BeanGenerator();
            if(null != superclass) {
                generator.setSuperclass(superclass);
            }
            BeanGenerator.addProperties(generator, propertyMap);
            return generator.create();
        }
    }
}
