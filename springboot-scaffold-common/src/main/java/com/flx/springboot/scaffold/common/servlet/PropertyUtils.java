package com.flx.springboot.scaffold.common.servlet;

import com.flx.springboot.scaffold.common.constants.WebConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.Objects;
import java.util.Properties;

/**
 * @Author Fenglixiong
 * @Create 2020/8/4 1:30
 * @Description
 **/
@Slf4j
public class PropertyUtils {

    public static final String defaultLocation = "application.properties";

    /**
     * 获取配置项
     * @param key
     * @return
     */
    public static String getProperty(String key){
        return Objects.requireNonNull(getPropertyEntity(defaultLocation)).getProperty(key);
    }

    /**
     * 根据文件获取配置项
     * @param location
     * @param key
     * @return
     */
    public static String getProperty(String location,String key){
        return Objects.requireNonNull(getPropertyEntity(location)).getProperty(key);
    }

    public static Properties getPropertyEntity(String location){
        if(StringUtils.isBlank(location)){
            location = "application.properties";
        }
        try{
            log.info("Ready to load resource : location = {}",location);
            return PropertiesLoaderUtils.loadProperties(new EncodedResource(new ClassPathResource(location), WebConstant.UTF_8));
        }catch (Exception e){
            log.info("Load resource failed , location = {}",location);
            e.printStackTrace();
            return null;
        }
    }

}
