package com.flx.springboot.scaffold.common.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.flx.springboot.scaffold.common.jdbc.base.SqlBaseUtils;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static com.flx.springboot.scaffold.common.jdbc.base.SqlBaseUtils.*;

/**
 * @Author: Fenglixiong
 * @Date: 2021/3/16 12:21
 * @Description:
 */
public class SqlDruidUtils {

    @Setter
    private static DruidDataSource dataSource;//数据源

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
        dataSource = new DruidDataSource();
        dataSource.setUrl(pr.getProperty(KEY_URL));
        dataSource.setUsername(pr.getProperty(KEY_USERNAME));
        dataSource.setPassword(pr.getProperty(KEY_PASSWORD));
        dataSource.setDriverClassName(pr.getProperty(KEY_DRIVER_CLASS));
    }

    public static void createDataSource(String url,String username,String password) throws SQLException {
        createDataSource(url,username,password,DEFAULT_DRIVER_CLASS);
    }

    public static void createDataSource(String url,String username,String password,String driverClass) throws SQLException {
        dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClass);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
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
