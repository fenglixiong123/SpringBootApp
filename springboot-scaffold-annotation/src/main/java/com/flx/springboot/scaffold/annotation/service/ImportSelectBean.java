package com.flx.springboot.scaffold.annotation.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/25 18:16
 * @Description:
 */
@Slf4j
public class ImportSelectBean implements ImportSelector {

    public ImportSelectBean() {
        log.info("ImportSelectBean init ...");
    }

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.flx.springboot.scaffold.annotation.service.ImportSelectEntity"};
    }

}
