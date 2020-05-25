package com.flx.springboot.scaffold.annotation.config;

import com.flx.springboot.scaffold.annotation.service.ImportBean;
import com.flx.springboot.scaffold.annotation.service.ImportSelectBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/25 18:21
 * @Description:
 */
@Slf4j
@Service
@Import({ImportBean.class, ImportSelectBean.class})
public class ImportConfig {

    public ImportConfig() {
        log.info(">>>>>>>>ImportConfig init ...");
    }
}
