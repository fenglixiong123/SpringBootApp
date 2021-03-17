package com.flx.springboot.scaffold.common;

import com.flx.springboot.scaffold.common.jdbc.SqlUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2021/3/18 0:06
 * @Description
 **/
public class SqlUtilsTest {

    public static void main(String[] args) throws Exception {

        String sql = "select * from admin where id = ?";
        AdminUser adminUser = SqlUtils.queryOne(sql, Arrays.asList(1), AdminUser.class);
        System.out.println(adminUser);
        sql = "select * from admin";
        List<AdminUser> adminUsers = SqlUtils.queryList(sql, null, AdminUser.class);
        System.out.println(adminUsers);
        sql = "update admin set password = ? where 1=1";
        SqlUtils.update(sql, Collections.singletonList("abcdefg"));
        sql = "update admin set nickname='www' where id = 1";
        SqlUtils.execute(sql,null);
        SqlUtils.executeBatch(Arrays.asList(
                "update admin set nickname='mmm' where id = 1",
                "update admin set nickname='ccc' where id = 3"));
        sql = "insert into admin (username,password) values (?,?)";
        SqlUtils.executeBatch(sql,Arrays.asList(
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
