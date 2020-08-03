package com.flx.springboot.scaffold.mybatis.plus.autoconfig;

import com.flx.springboot.scaffold.mybatis.plus.common.ScanFieldAlias;
import com.flx.springboot.scaffold.mybatis.plus.config.MybatisPlusConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @Author Fenglixiong
 * @Create 2020/8/4 0:55
 * @Description
 **/
@Import(value = {MybatisPlusConfiguration.class, ScanFieldAlias.class})
public class MyBatisPlusAutoConfiguration {

}
