package com.flx.springboot.scaffold.common.utils.date;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Fenglixiong
 * @Create 2018.11.09 23:21
 * @Description
 **/
public class DateUtils {

    private static String DEFAULT_PATTEN = "yyyy-MM-dd HH:mm:ss";
    private static String SIMPLE_PATTEN = "yyyyMMddHHmmss";


    public static String dateToString(Date source){
        return formatWithPatten(source,DEFAULT_PATTEN);
    }

    public static String dateToSimpleString(Date source){
        return formatWithPatten(source,SIMPLE_PATTEN);
    }

    /**
     * 日期转换为字符串
     * @param source
     * @param patten
     * @return
     */
    public static String formatWithPatten(Date source, String patten){
        if(StringUtils.isBlank(patten)){
            patten = DEFAULT_PATTEN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        return sdf.format(source);
    }

}
