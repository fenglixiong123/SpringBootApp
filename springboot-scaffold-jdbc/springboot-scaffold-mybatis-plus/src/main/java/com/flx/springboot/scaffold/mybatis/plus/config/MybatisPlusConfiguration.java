package com.flx.springboot.scaffold.mybatis.plus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.flx.springboot.scaffold.mybatis.plus.annotation.DaoMapper;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfiguration {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 扫描mybatis的dao层
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.flx.springboot.scaffold.mybatis.plus.*.dao");
        scannerConfigurer.setAnnotationClass(DaoMapper.class);
        return scannerConfigurer;
    }

}
