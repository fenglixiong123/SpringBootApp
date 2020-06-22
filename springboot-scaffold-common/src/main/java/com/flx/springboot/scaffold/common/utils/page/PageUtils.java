package com.flx.springboot.scaffold.common.utils.page;

/**
 * @Author: Fenglixiong
 * @Date: 2020/6/22 12:37
 * @Description:
 */
public class PageUtils {

    public static int getTotalPage(int totalRecord,int pageSize){
        return (totalRecord+pageSize-1)/pageSize;
    }

    public static int getTotalPage1(int totalRecord,int pageSize){
        return totalRecord % pageSize == 0 ? totalRecord/pageSize : totalRecord/pageSize+1;
    }

    public static int getTotalPage2(int totalRecord,int pageSize){
        return (int)Math.ceil(totalRecord/pageSize);
    }

}
