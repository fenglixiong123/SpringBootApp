package com.flx.springboot.scaffold.common.system;

/**
 * @Author: Fenglixiong
 * @Date: 2021/4/7 0:05
 * @Description:
 */
public class SystemUtils {

    /**
     * 获取系统cpu个数
     * @return
     */
    public static int getCpuNumber(){
        return Runtime.getRuntime().availableProcessors();
    }

}
