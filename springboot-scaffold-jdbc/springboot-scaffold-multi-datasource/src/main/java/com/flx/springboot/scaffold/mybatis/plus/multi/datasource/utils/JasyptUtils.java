package com.flx.springboot.scaffold.mybatis.plus.multi.datasource.utils;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @Author: Fenglixiong
 * @Date: 2020/9/28 17:30
 * @Description:
 */
public class JasyptUtils {

    /**
     * Jasypt生成加密结果
     *
     * @param salt     配置文件中设定的加密密码 jasypt.encryptor.password
     * @param password 待加密值
     * @return
     */
    public static String encryptPwd(String salt, String password) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(salt);
        return textEncryptor.encrypt(password);
    }

    /**
     * 解密
     *
     * @param salt     配置文件中设定的加密密码 jasypt.encryptor.password
     * @param password 待解密密文
     * @return
     */
    public static String decyptPwd(String salt, String password) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(salt);
        return textEncryptor.decrypt(password);
    }

}
