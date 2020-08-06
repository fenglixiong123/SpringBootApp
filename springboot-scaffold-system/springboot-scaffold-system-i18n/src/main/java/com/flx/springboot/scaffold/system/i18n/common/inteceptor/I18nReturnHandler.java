package com.flx.springboot.scaffold.system.i18n.common.inteceptor;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.common.utils.web.CookieUtils;
import com.flx.springboot.scaffold.system.i18n.sdk.I18nResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Author: fenglixiong
 * @Date: 2019/5/5 18:02
 * @Version 2.0.0
 * 新增一种ReturnHandler处理器
 */
@Slf4j
public class I18nReturnHandler extends RequestResponseBodyMethodProcessor {

    public static final String COOKIE_NAME = "locale";
    public static final String requestTime = "start-time";

    @Autowired
    private I18nResponse i18nResponse;


    public I18nReturnHandler(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    /**
     * 该处理程序是否支持给定的方法返回类型(只有返回true才回去调用handleReturnValue)
     */
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return methodParameter.getParameterType() == ResultResponse.class;
    }

    /**
     * 处理给定的返回值
     * 通过向 ModelAndViewContainer 添加属性和设置视图或者
     * 通过调用 ModelAndViewContainer.setRequestHandled(true) 来标识响应已经被直接处理(不再调用视图解析器)
     */
    @Override
    public void handleReturnValue(Object result, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws IOException, HttpMediaTypeNotAcceptableException {

        if (result instanceof ResultResponse) {
            ResultResponse resultResponse = ((ResultResponse) result);
            if (!resultResponse.isSuccess()) {
                /**
                 * 标识请求是否已经在该方法内完成处理
                 */
                modelAndViewContainer.setRequestHandled(true);
                HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
                if(request==null){
                    super.handleReturnValue(result, methodParameter, modelAndViewContainer, nativeWebRequest);
                    return;
                }
                //获取开始时间
                Object startTimerObj = request.getAttribute(requestTime);
                if (!(startTimerObj instanceof Long)) {
                    //没有开始时间，不做处理的请求
                    super.handleReturnValue(result, methodParameter, modelAndViewContainer, nativeWebRequest);
                    return;
                }
                Long startTimer = (Long) startTimerObj;
                long millis = System.currentTimeMillis() - startTimer;
                log.debug("请求url：{} 耗时：{}", request.getRequestURL().toString(), millis);

                String language = CookieUtils.getValueFromCookie(request,COOKIE_NAME);

                if (StringUtils.isBlank(language)) {
                    language = LocaleContextHolder.getLocale().getLanguage();
                }
                try {
                    //对返回值进行国际化操作
                    resultResponse = i18nResponse.i18nTran(resultResponse, language);
                } catch (Exception e) {
                    e.printStackTrace();
                    resultResponse = ResultResponse.error("System.I18n.Fail", "I18n transfer fail!");
                }
                result = resultResponse;
            }
        }
        super.handleReturnValue(result, methodParameter, modelAndViewContainer, nativeWebRequest);
    }


}