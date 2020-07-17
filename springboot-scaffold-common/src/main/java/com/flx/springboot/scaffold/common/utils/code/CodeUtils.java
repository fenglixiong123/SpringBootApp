package com.flx.springboot.scaffold.common.utils.code;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/25 16:32
 * @Description:
 */
public class CodeUtils {

    /**
     * userId-->user_id
     * @param source
     * @return
     */
    public static String toLowerCase(String source) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isBlank(source)) {
            return "";
        }
        char[] charList = source.toCharArray();
        sb.append(Character.toLowerCase(charList[0]));
        for (int i = 1; i < charList.length; i++) {
            if (Character.isUpperCase(charList[i])) {
                sb.append("_").append(Character.toLowerCase(charList[i]));
            } else {
                sb.append(charList[i]);
            }
        }
        return sb.toString();
    }

    public static String toUpperCase(String str) {
        str = toLowerCase(str);
        StringBuilder tableName = new StringBuilder();
        if (StringUtils.isBlank(str)) {
            return "";
        }
        char[] charList = str.toCharArray();
        for (int i = 0; i < charList.length; i++) {
            tableName.append(Character.toUpperCase(charList[i]));
        }
        return tableName.toString();
    }

}
