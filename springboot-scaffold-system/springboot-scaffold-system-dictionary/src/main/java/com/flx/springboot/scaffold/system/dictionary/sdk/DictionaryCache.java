package com.flx.springboot.scaffold.system.dictionary.sdk;

import com.flx.springboot.scaffold.common.constants.WebConstant;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import com.flx.springboot.scaffold.system.dictionary.dto.DictionaryDTO;
import com.flx.springboot.scaffold.system.dictionary.manager.DictionaryManager;
import com.flx.springboot.scaffold.system.i18n.sdk.I18nCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.util.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Fenglixiong
 * @Date: 2020/8/6 20:18
 * @Description:
 */
@Slf4j
@Component
public class DictionaryCache {

    @Value("${dictionary.refresh.prefix:Scaffold-}")
    private String refreshPrefix;
    @Value("${dictionary.refresh.cron:#{\"0 0 0 1/1 * ?\"}}")
    private String refreshTime;

    private static Map<String, DictionaryDTO> dictionaryHashMap = new HashMap<>();

    @Autowired
    private I18nCache i18nCache;
    @Autowired
    private DictionaryManager dictionaryManager;

    public String getDictionaryMessage(String keyCode, String valueCode) {
        String language = LocaleContextHolder.getLocale().getLanguage();
        if (language == null || language.isEmpty()) {
            language = WebConstant.en_us;
        }
        return getDictionaryMessage(keyCode,valueCode,language);
    }

    public String getDictionaryMessage(String keyCode, String valueCode, String language) {
        String code = keyCode + valueCode;
        if (dictionaryHashMap.containsKey(code)) {
            DictionaryDTO dictionaryDTO = dictionaryHashMap.get(code);
            return i18nCache.getMessageOrDefault(dictionaryDTO.getI18nCode(), language, keyCode);
        } else {
            return keyCode;
        }
    }

    /**
     * 每隔10分钟更新一次
     */
    @Scheduled(cron = "${dictionary.refresh.cron:#{\"0 0/10 * * * ? \"}}")
    private void refreshDictionary() throws Exception {
        refresh();
        log.info("Update dictionary in " + new Date());
    }

    @PostConstruct
    private void init() throws Exception {
        if (StringUtils.isBlank(refreshTime)) {
            throw new Exception("Cron is blank,your cron is " + refreshTime);
        }
        if (!CronExpression.isValidExpression(refreshTime)) {
            throw new Exception("Cron is illegal,your cron is " + refreshTime);
        }
        refresh();
    }

    /**
     * 刷新dictionary文件
     * @throws Exception
     */
    private void refresh() throws Exception {
        try {
            dictionaryHashMap.clear();
            Map<String, Object> query = new HashMap<>();
            query.put("state", State.effective.name());
            List<DictionaryDTO> dictionaryDTOS = dictionaryManager.queryDictionary(query);
            dictionaryDTOS.forEach(e -> dictionaryHashMap.put(e.getKeyCode() + e.getValueCode(), e));
        } catch (Exception e) {
            log.error("DictionaryCache refresh fail!");
            throw new Exception(e.toString());
        }

    }

}
