package com.flx.springboot.scaffold.web.core.util;

import com.google.gson.Gson;

/**
 * @Author Fenglixiong
 * @Create 2018.12.03 11:37
 * @Description 核心包Json
 **/
public class CoreUtils {

    private static Gson gson = new Gson();

    public static String toJsonMsg(Object o){
        if(o==null){
            return null;
        }
        return gson.toJson(o);
    }

}
