package com.flx.springboot.scaffold.system.i18n.sdk;

import com.flx.springboot.scaffold.common.constants.WebConstant;
import com.flx.springboot.scaffold.common.utils.CommonUtils;
import com.flx.springboot.scaffold.mybatis.plus.constants.PlusConstant;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import com.flx.springboot.scaffold.system.i18n.dto.I18nDTO;
import com.flx.springboot.scaffold.system.i18n.entity.I18nDO;
import com.flx.springboot.scaffold.system.i18n.manager.I18nManager;
import com.flx.springboot.scaffold.system.i18n.vo.I18nVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.util.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @Author: fenglixiong
 * @Date: 2019/5/6 16:43
 * @Version 2.0.0
 */
@Slf4j
@Component
public class I18nCache {

    @Value("${i18n.refresh.prefix:Scaffold-}")
    private String refreshPrefix;
    @Value("${i18n.refresh.cron:#{\"0 0 0 1/1 * ?\"}}")
    private String refreshTime;

    private static Map<String, String> i18nBOHashMap = new HashMap<>();

    @Autowired
    private I18nManager i18nManager;

    public String getMessage(String i18NCode) {
        String language = LocaleContextHolder.getLocale().getLanguage();
        return getMessage(i18NCode,language,null,null);
    }

    public String getMessageOrDefault(String i18NCode,String language,String defaultValue) {
        return getMessage(i18NCode,language,null,defaultValue);
    }

    public String getMessage(String i18NCode, String language) {
        return getMessage(i18NCode,language,null,null);
    }

    public String getMessage(String i18NCode, Object[] args) {
        String language = LocaleContextHolder.getLocale().getLanguage();
        return getMessage(i18NCode,language,args,null);
    }

    public String getMessage(String i18NCode, String language, Object[] args) {
        return getMessage(i18NCode,language,args,null);
    }

    private String getMessage(String i18NCode, String language, Object[] args,String defaultValue) {
        language = CommonUtils.defaultIfNull(language,WebConstant.en_us);
        String i18nMessage = i18nBOHashMap.get(i18NCode + "|" + language);
        if (StringUtils.isBlank(i18nMessage)) {
            return defaultValue == null ? i18NCode : defaultValue;
        }
        if(args!=null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                i18nMessage = i18nMessage.replace("{" + i + "}", args[i].toString());
            }
        }
        return i18nMessage;
    }

    public boolean messageExist(String i18NCode, String language) {
        return i18nBOHashMap.containsKey(i18NCode + "|" + language);
    }

    /**
     * 每隔10分钟更新一次
     */
    @Scheduled(initialDelay = 5000,cron = "${i18n.refresh.cron:#{\"0 0/10 * * * ? \"}}")
    private void refreshI18n() throws Exception {
        refresh();
        log.info("Update i18n and dictionary in " + new Date());
    }

    @PostConstruct
    private void init() throws Exception {
        if (StringUtils.isBlank(refreshTime)) {
            throw new Exception("Cron is blank,your cron is " + refreshTime);
        }
        if (!CronExpression.isValidExpression(refreshTime)) {
            throw new Exception("Cron is illegal,your cron is " + refreshTime);
        }
    }

    /**
     * 刷新i18n文件
     * @throws Exception
     */
    private void refresh() throws Exception {
        try {
            i18nBOHashMap.clear();
            List<I18nDTO> i18nBOList = i18nManager.queryByPrefix(refreshPrefix, State.effective,1, PlusConstant.I18N_MAX_PAGE_NUM).getRecords();
            i18nBOList.forEach(e -> i18nBOHashMap.put(e.getI18nCode() + "|" + e.getLanguage(), e.getValue()));
        } catch (Exception e) {
            log.error("I18nCache refresh fail!");
            throw new Exception(e.toString());
        }

    }

}
