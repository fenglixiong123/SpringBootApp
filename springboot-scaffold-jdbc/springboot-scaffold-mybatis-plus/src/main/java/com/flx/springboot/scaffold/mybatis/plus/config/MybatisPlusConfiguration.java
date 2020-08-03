package com.flx.springboot.scaffold.mybatis.plus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.flx.springboot.scaffold.common.servlet.PropertyUtils;
import com.flx.springboot.scaffold.mybatis.plus.annotation.DaoMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Configuration
public class MybatisPlusConfiguration implements InitializingBean{

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
        String basePackage = PropertyUtils.getProperty("spring.flx.dao.basePackage");
        log.info("MapperScannerConfigurer basePackage = {}",basePackage);
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage(Objects.requireNonNull(basePackage));
        scannerConfigurer.setAnnotationClass(DaoMapper.class);
        return scannerConfigurer;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
