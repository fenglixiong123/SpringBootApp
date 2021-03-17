package com.flx.springboot.scaffold.common;

import com.flx.springboot.scaffold.common.jdbc.SqlDruidUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2021/3/18 1:30
 * @Description
 **/
public class SqlDruidUtilsTest {

    public static void main(String[] args) throws Exception {

        String sql = "select * from admin where id = ?";
        AdminUser adminUser = SqlDruidUtils.queryOne(sql, Arrays.asList(1), AdminUser.class);
        System.out.println(adminUser);
        sql = "select * from admin";
        List<AdminUser> adminUsers = SqlDruidUtils.queryList(sql, null, AdminUser.class);
        System.out.println(adminUsers);
        sql = "update admin set password = ? where 1=1";
        SqlDruidUtils.update(sql, Collections.singletonList("abcdefg"));
        sql = "update admin set nickname='www' where id = 1";
        SqlDruidUtils.execute(sql,null);
        SqlDruidUtils.executeBatch(Arrays.asList(
                "update admin set nickname='mmm' where id = 1",
                "update admin set nickname='ccc' where id = 3"));
        sql = "insert into admin (username,password) values (?,?)";
        SqlDruidUtils.executeBatch(sql,Arrays.asList(
                Arrays.asList("tom1","123"),
                Arrays.asList("tom2","123"),
                Arrays.asList("tom3","123"),
                Arrays.asList("tom4","123")));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdminUser{
        private String username;
        private String password;
        private String nickname;
    }
    
}
