package com.flx.springboot.scaffold.common.jdbc;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: Fenglixiong
 * @Date: 2021/3/16 12:06
 * @Description:
 */
public class SqlUtils {

    private static String driverClass;//加载驱动时的路径
    private static String url;//数据库的路径
    private static String username;//数据库的登录名
    private static String password;//数据据库的登录密码
    private static DataSource dataSource;//数据集

    static {
        InputStream is = SqlUtils.class.getClassLoader().getResourceAsStream("/druid.properties");
        Properties properties = new Properties();
        try {
            properties.load(is); //创建连接池，使用配置文件中的参数
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
