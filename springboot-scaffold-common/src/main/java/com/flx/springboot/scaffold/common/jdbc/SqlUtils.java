package com.flx.springboot.scaffold.common.jdbc;

import com.flx.springboot.scaffold.common.jdbc.base.SqlBaseUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static com.flx.springboot.scaffold.common.jdbc.base.SqlBaseUtils.*;

/**
 * @Author: Fenglixiong
 * @Date: 2021/3/16 12:06
 * @Description:
 */
public class SqlUtils {

    private static String url;//数据库的路径
    private static String username;//数据库的登录名
    private static String password;//数据据库的登录密码
    private static String driverClass;//加载驱动时的路径

    static {
        //1.加载配置文件
        String location = getLocation();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(location);
        Properties pr = new Properties();
        try {
            pr.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.读取配置信息
        username = pr.getProperty(KEY_USERNAME);
        password = pr.getProperty(KEY_PASSWORD);
        url = pr.getProperty(KEY_URL);
        driverClass = pr.getProperty(KEY_DRIVER_CLASS);
        //3.加载驱动
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createDataSource(String url,String username,String password) throws SQLException {
        createDataSource(url,username,password,DEFAULT_DRIVER_CLASS);
    }

    public static void createDataSource(String url,String username,String password,String driverClass) throws SQLException {
        SqlUtils.url = url;
        SqlUtils.username = username;
        SqlUtils.password = password;
        SqlUtils.driverClass = driverClass;
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    public static <T> T queryOne(String sql, List<Object> params, Class<T> c)throws Exception{
        return SqlBaseUtils.queryOne(sql,params,c);
    }

    public static <T> List<T> queryList(String sql,List<Object> params,Class<T> c)throws Exception{
        return SqlBaseUtils.queryList(sql,params,c);
    }

    public static int update(String sql, List<Object> params)throws Exception {
        return SqlBaseUtils.update(sql,params);
    }

    public static int[] updateBatch(List<String> sqlList)throws Exception{
        return SqlBaseUtils.updateBatch(sqlList);
    }

    public static int[] updateBatch(String sql,List<List<Object>> params)throws Exception{
        return SqlBaseUtils.updateBatch(sql,params);
    }

    public static boolean execute(String sql, List<Object> params)throws Exception {
        return SqlBaseUtils.execute(sql,params);
    }

}
