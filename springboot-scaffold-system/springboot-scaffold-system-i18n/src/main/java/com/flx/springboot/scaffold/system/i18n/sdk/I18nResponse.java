package com.flx.springboot.scaffold.system.i18n.sdk;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Fenglixiong
 * @Date: 2020/8/6 17:16
 * @Description:
 */
@Slf4j
@Component
public class I18nResponse {
    
    @Autowired
    private I18nCache i18nCache;

    /**
     * 自动将response错误结果进行国际化转换
     * code     :   Basic.Zone.Bucket.ReduplicateCode
     * message  :   Delete bucket {0} by Id {1} fail! | bucket-001 | 99
     * @param resultResponse
     * @param language
     * @return
     */
    public ResultResponse i18nTran(ResultResponse resultResponse, String language) {

        long startTime = System.currentTimeMillis();
        if (resultResponse.isSuccess()) {
            return resultResponse;
        } else {

            String code = resultResponse.getCode().trim();
            String[] codes = code.split("[.]");
            String prefix = codes[0];
            String message = resultResponse.getMessage();
            //若message不为空
            if (StringUtils.isNotBlank(message)) {

                message = message.trim();
                String[] messageArray = message.split("\\|\\|");
                message = messageArray[0];

                //最常见情况
                if (i18nCache.messageExist(prefix + "-" + messageArray[0].trim(), language)) {
                    message = i18nCache.getMessage(prefix + "-" + messageArray[0].trim(), language);
                    for (int i = 1; i < messageArray.length; i++) {
                        if (i18nCache.messageExist(prefix + "-" + messageArray[i], language)) {
                            messageArray[i] = i18nCache.getMessage(prefix + "-" + messageArray[i], language);
                        }
                        message = message.replace("{" + (i - 1) + "}", messageArray[i]);
                    }
                }
            } else {
                //若message为空，则翻译code
                if (i18nCache.messageExist(prefix + "-" + codes[3], language)) {
                    codes[2] = i18nCache.getMessage(prefix + "-" + codes[2], language);
                    message = i18nCache.getMessage(prefix + "-" + codes[3], language, new String[]{codes[2]});
                }
            }
            resultResponse.setMessage(message);
            long useTime = System.currentTimeMillis() - startTime;
            if (useTime > 2000) {
                log.error("Translate i18n message time out,time=" + useTime + ",code=" + resultResponse.getCode() + "message=" + resultResponse.getMessage());
            }
            return resultResponse;
        }
    }
    
}
