package com.flx.springboot.scaffold.common.jdbc.base;

import com.flx.springboot.scaffold.common.utils.code.CodeUtils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * @Author: Fenglixiong
 * @Date: 2021/3/16 12:18
 * @Description: SQL基础操作原
 */
public class SqlBaseUtils {

    public static String KEY_URL = "url";//数据库的路径
    public static String KEY_USERNAME = "username";//数据库的登录名
    public static String KEY_PASSWORD = "password";//数据据库的登录密码
    public static String KEY_DRIVER_CLASS = "driverClass";//加载驱动时的路径

    private static String JDBC_LOCATION = "application.properties";//数据库连接文件地址

    public static String DEFAULT_DRIVER_CLASS = "com.mysql.jdbc.Driver";//默认driverClass

    public static void setLocation(String location){
        JDBC_LOCATION = location;
    }

    public static String getLocation(){
        return JDBC_LOCATION;
    }

    public static Connection getConnection() throws Exception{
        return getConnection(JDBC_LOCATION);
    }

    /**
     * 获取数据库连接
     * @param location
     * @return
     * @throws Exception
     */
    public static Connection getConnection(String location) throws Exception {
        //1.加载配置文件
        Objects.requireNonNull(location);
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(location);
        Properties pr = new Properties();
        pr.load(is);
        //2.读取配置信息
        String username = pr.getProperty(KEY_USERNAME);
        String password = pr.getProperty(KEY_PASSWORD);
        String url = pr.getProperty(KEY_URL);
        String driverClass = pr.getProperty(KEY_DRIVER_CLASS);
        //3.加载驱动
        Class.forName(driverClass);
        //4.获取连接
        return DriverManager.getConnection(url,username,password);
    }

    /**
     * 通用查询接口
     */
    public static <T> T queryOne(String sql,List<Object> params,Class<T> c)throws Exception{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i+1,params.get(i));
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            int column = rsm.getColumnCount();
            if (rs.next()){
                T o = c.newInstance();
                for (int i=0;i<column;i++){
                    String name = CodeUtils.underToCamel(rsm.getColumnName(i+1));
                    Object value = rs.getObject(i+1);
                    Field field = c.getDeclaredField(name);
                    field.setAccessible(true);
                    field.set(o,value);
                }
                return o;
            }
            return null;
        }finally {
            close(con,ps,rs);
        }
    }

    /**
     * 通用查询接口
     */
    public static <T> List<T> queryList(String sql,List<Object> params,Class<T> c)throws Exception{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> resultList = new ArrayList<>();
        try{
            con = getConnection();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i+1,params.get(i));
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            int column = rsm.getColumnCount();
            while (rs.next()){
                T o = c.newInstance();
                for (int i=0;i<column;i++){
                    String name = CodeUtils.underToCamel(rsm.getColumnName(i+1));
                    Object value = rs.getObject(i+1);
                    Field field = c.getDeclaredField(name);
                    field.setAccessible(true);
                    field.set(o,value);
                    resultList.add(o);
                }
            }
            return resultList;
        }finally {
            close(con,ps,rs);
        }
    }

    /**
     * 通用更新操作
     */
    public static int update(String sql, List<Object> params)throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            return ps.executeUpdate();
        }finally {
            close(con,ps);
        }
    }

    /**
     * 1.Statement它更适合执行不同sql的批处理，它没有提供预处理功能，性能比较低。
     * 2.PreparedStatement它适合执行相同的批处理，它提供了预处理功能，属性比较高。
     * @param sqlList insert into user values ();
     * @throws Exception
     */
    public static int[] updateBatch(List<String> sqlList)throws Exception{
        Connection con = null;
        Statement st = null;
        try {
            con = getConnection();
            st = con.createStatement();
            for (String sql : sqlList){
                st.addBatch(sql);
            }
            int[] ret = st.executeBatch();
            st.clearBatch();
            return ret;
        }finally {
            close(con,st);
        }
    }

    /**
     * 批量执行新增或者更新操作，对相同数据操作
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */
    public static int[] updateBatch(String sql,List<List<Object>> params)throws Exception{
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                List<Object> rows = params.get(i);
                for (int j = 0; j < rows.size(); j++) {
                    ps.setObject(j+1,rows.get(j));
                }
                ps.addBatch();
                if(i%1000==0){
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            int[] ret = ps.executeBatch();
            ps.clearBatch();
            return ret;
        }finally {
            close(con,ps);
        }
    }

    /**
     * 通用操作
     */
    public static boolean execute(String sql, List<Object> params)throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            return ps.execute();
        }finally {
            close(con,ps);
        }
    }

    public static void close(Connection con){
        close(con,null,null,null);
    }

    public static void close(Statement st){
        close(null,null,st,null);
    }

    public static void close(PreparedStatement ps){
        close(null,ps,null,null);
    }

    public static void close(Connection con, Statement st){
        close(con,null,st,null);
    }

    public static void close(Connection con, PreparedStatement ps){
        close(con,ps,null,null);
    }

    public static void close(Connection con, PreparedStatement ps, ResultSet rs){
        close(con,ps,null,rs);
    }

    /**
     * 释放资源
     * @param con 连接源
     * @param ps 预编译
     * @Param rs 结果集
     */
    @SuppressWarnings("all")
    public static void close(Connection con, PreparedStatement ps, Statement st, ResultSet rs){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
