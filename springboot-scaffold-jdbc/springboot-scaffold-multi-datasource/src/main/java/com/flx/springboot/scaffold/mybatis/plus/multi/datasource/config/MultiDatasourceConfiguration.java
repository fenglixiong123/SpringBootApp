package com.flx.springboot.scaffold.mybatis.plus.multi.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.flx.springboot.scaffold.mybatis.plus.multi.datasource.datasource.DynamicDataSource;
import com.flx.springboot.scaffold.mybatis.plus.multi.datasource.utils.JasyptUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: Fenglixiong
 * @Date: 2020/9/28 17:23
 * @Description:
 */
@Configuration
@ConfigurationProperties(prefix = "spring")
public class MultiDatasourceConfiguration {

    @Getter
    @Setter
    private LinkedHashMap<String,String> datasource;

    /**
     * 数据源切换，增加默认数据源
     *
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DruidConfigNode druidNode = new DruidConfigNode();
        DbConfigNode dbConfigNode = new DbConfigNode();
        for (Map.Entry<String, String> entry : datasource.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.startsWith(Constant.DRUID)) {
                druidNode.put(key.split("\\.")[1], value);
            } else if (key.startsWith(Constant.DB)) {
                dbConfigNode.put(key, value);
            }
        }

        dbConfigNode.init();
        Map<Object, Object> targetDataSources = new HashMap<>();
        for (String entry:dbConfigNode.builderMap.keySet()) {
            targetDataSources.put(entry,druidNode.createDruidDataSource(dbConfigNode.builderMap.get(entry).build()));
        }
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        //设置默认的数据源，当拿不到数据源时，使用此配置
        dataSource.setDefaultTargetDataSource(druidNode.createDruidDataSource(dbConfigNode.getDefaultDb()));
        return dataSource;

    }

    @Data
    public static class DbConfigNode {

        private static final String DEFAULT_DB_NAME = "db0";

        private final Map<String, String> properties = new LinkedHashMap<>();

        /**
         * key:dbName, value:builder
         */
        private Map<String, DbConfigBuilder> builderMap;

        public void put(String key, String value) {
            properties.put(key, value);
        }

        public void init() {
            Map<String, DbConfigBuilder> builderMap = new HashMap<>();
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                String key = entry.getKey();
                String[] segments = key.split("\\.");
                String dbName = segments[0];
                DbConfigBuilder builder = builderMap.get(dbName);
                if (builder == null) {
                    builder = new DbConfigBuilder(dbName);
                    builderMap.put(dbName, builder);
                }
                builder.put(segments[1], entry.getValue());
            }

            this.builderMap = builderMap;
        }

        private DbConfig getDefaultDb() {
            return builderMap.get(DEFAULT_DB_NAME).build();
        }

        public static class DbConfigBuilder {
            private final Map<String, String> properties = new LinkedHashMap<>();
            private final String name;

            private DbConfigBuilder(String name) {
                this.name = name;
            }

            public void put(String key, String value) {
                properties.put(key, value);
            }

            public DbConfig build() {
                DbConfig dbConfig = new DbConfig();
                dbConfig.url = properties.get("url");
                dbConfig.username = properties.get("username");
                dbConfig.password = properties.get("password");
                dbConfig.dbName = name;
                dbConfig.driverClassName = properties.get("driver-class-name");
                if (properties.containsKey("salt-flag")) {
                    dbConfig.saltFlag = Boolean.valueOf(properties.get("salt-flag"));
                    dbConfig.saltPassword = properties.get("salt-password");
                }
                return dbConfig;
            }
        }
    }

    @Data
    private static class DbConfig {
        private String dbName;
        private String url;
        private String username;
        private String password;
        private String driverClassName;
        private boolean saltFlag = false;
        private String saltPassword = "fenglixiong";
    }

    @Data
    private static class DruidConfigNode {

        private final Map<String, String> properties = new LinkedHashMap<>();

        private void put(String key, String value) {
            properties.put(key, value);
        }

        private String get(String key) {
            return properties.get(key);
        }

        private Integer getInt(String key) {
            return Integer.valueOf(properties.get(key));
        }

        private Long getLong(String key) {
            return Long.valueOf(properties.get(key));
        }

        private Boolean getBoolean(String key) {
            return Boolean.valueOf(properties.get(key));
        }

        private DruidDataSource createDruidDataSource(DbConfig dbConfig) {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl(dbConfig.url);
            dataSource.setUsername(dbConfig.username);
            if(dbConfig.saltFlag){
                dataSource.setPassword(JasyptUtils.decyptPwd(dbConfig.saltPassword, dbConfig.password));
            } else {
                dataSource.setPassword(dbConfig.password);
            }
            dataSource.setDriverClassName(dbConfig.driverClassName);
            dataSource.setInitialSize(this.getInt("initialSize"));
            dataSource.setMinIdle(this.getInt("minIdle"));
            dataSource.setMaxActive(this.getInt("maxActive"));
            dataSource.setMaxWait(this.getLong("maxWait"));
            dataSource.setTimeBetweenEvictionRunsMillis(this.getLong("timeBetweenEvictionRunsMillis"));
            dataSource.setMinEvictableIdleTimeMillis(this.getLong("minEvictableIdleTimeMillis"));
            dataSource.setValidationQuery(this.get("validationQuery"));
            dataSource.setTestWhileIdle(this.getBoolean("testWhileIdle"));
            dataSource.setTestOnBorrow(this.getBoolean("testOnBorrow"));
            dataSource.setTestOnReturn(this.getBoolean("testOnReturn"));
            dataSource.setMaxOpenPreparedStatements(this.getInt("maxOpenPreparedStatements"));
            dataSource.setRemoveAbandoned(this.getBoolean("removeAbandoned"));
            dataSource.setRemoveAbandonedTimeout(this.getInt("removeAbandonedTimeout"));
            dataSource.setLogAbandoned(this.getBoolean("logAbandoned"));
            dataSource.setPoolPreparedStatements(this.getBoolean("poolPreparedStatements"));
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(this.getInt("maxPoolPreparedStatementPerConnectionSize"));
            dataSource.setDbType(this.get("type"));
            try {
                dataSource.setFilters(this.get("filters"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dataSource.setConnectionProperties(this.get("connectionProperties"));
            return dataSource;
        }
    }

}
