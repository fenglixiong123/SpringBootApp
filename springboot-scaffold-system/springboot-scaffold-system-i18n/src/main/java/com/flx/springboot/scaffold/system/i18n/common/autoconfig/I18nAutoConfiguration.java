package com.flx.springboot.scaffold.system.i18n.common.autoconfig;

import com.flx.springboot.scaffold.system.i18n.common.config.I18nWebConfig;
import com.flx.springboot.scaffold.system.i18n.dao.I18nDao;
import com.flx.springboot.scaffold.system.i18n.manager.I18nManager;
import com.flx.springboot.scaffold.system.i18n.resource.I18nController;
import com.flx.springboot.scaffold.system.i18n.sdk.I18nCache;
import com.flx.springboot.scaffold.system.i18n.sdk.I18nResponse;
import com.flx.springboot.scaffold.system.i18n.service.I18nService;
import com.flx.springboot.scaffold.system.i18n.service.impl.I18nServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * @Author: Fenglixiong
 * @Date: 2020/8/6 20:41
 * @Description:
 */
@Slf4j
@Import(value = {I18nWebConfig.class})
public class I18nAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(I18nCache.class)
    public I18nResponse i18nResponse(){
        return new I18nResponse();
    }

    @Bean
    @ConditionalOnMissingBean(I18nCache.class)
    public I18nCache i18nCache(){
        return new I18nCache();
    }

    @Bean
    @ConditionalOnMissingBean(I18nManager.class)
    public I18nManager i18nManager(){
        return new I18nManager();
    }

    @Bean
    @ConditionalOnMissingBean(I18nService.class)
    public I18nService i18nService(){
        return new I18nServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(I18nController.class)
    public I18nController i18nController(){
        return new I18nController();
    }

    @PostConstruct
    public void init(){
        log.info("*************************************************");
        log.info("*                                               *");
        log.info("*                  I18n Success                 *");
        log.info("*                                               *");
        log.info("*************************************************");
    }

}
