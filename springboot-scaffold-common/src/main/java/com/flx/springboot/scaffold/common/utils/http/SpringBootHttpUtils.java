package com.flx.springboot.scaffold.common.utils.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/15 16:52
 * @Description:
 * // 出现中文乱码现象
 * String json = HttpUtil.get(url, null);
 * json = new String(json.getBytes("ISO-8859-1"), "UTF-8");
 */
@Slf4j
public class SpringBootHttpUtils {

    /**
     * get请求
     *
     * @param url
     * @param params 请求参数
     * @return
     */
    public static String get(String url, MultiValueMap<String, String> params) {
        return get(url, params, null);
    }

    /**
     * get请求
     *
     * @param url
     * @param params  请求参数
     * @param headers 请求头
     * @return
     */
    public static String get(String url, MultiValueMap<String, String> params, MultiValueMap<String, String> headers) {
        return request(url, params, headers, HttpMethod.GET);
    }

    /**
     * post请求
     *
     * @param url
     * @param params 请求参数
     * @return
     */
    public static String post(String url, MultiValueMap<String, String> params) {
        return post(url, params, null);
    }

    /**
     * post请求
     *
     * @param url
     * @param params  请求参数
     * @param headers 请求头
     * @return
     */
    public static String post(String url, MultiValueMap<String, String> params, MultiValueMap<String, String> headers) {
        return request(url, params, headers, HttpMethod.POST);
    }

    /**
     * put请求
     *
     * @param url
     * @param params 请求参数
     * @return
     */
    public static String put(String url, MultiValueMap<String, String> params) {
        return put(url, params, null);
    }

    /**
     * put请求
     *
     * @param url
     * @param params  请求参数
     * @param headers 请求头
     * @return
     */
    public static String put(String url, MultiValueMap<String, String> params, MultiValueMap<String, String> headers) {
        return request(url, params, headers, HttpMethod.PUT);
    }

    /**
     * delete请求
     *
     * @param url
     * @param params 请求参数
     * @return
     */
    public static String delete(String url, MultiValueMap<String, String> params) {
        return delete(url, params, null);
    }

    /**
     * delete请求
     *
     * @param url
     * @param params  请求参数
     * @param headers 请求头
     * @return
     */
    public static String delete(String url, MultiValueMap<String, String> params, MultiValueMap<String, String> headers) {
        return request(url, params, headers, HttpMethod.DELETE);
    }

    /**
     * 表单请求
     *
     * @param url
     * @param params  请求参数
     * @param headers 请求头
     * @param method  请求方式
     * @return
     */
    public static String request(String url, MultiValueMap<String, String> params, MultiValueMap<String, String> headers, HttpMethod method) {
        if (params == null) {
            params = new LinkedMultiValueMap<>();
        }
        return request(url, params, headers, method, MediaType.APPLICATION_FORM_URLENCODED);
    }

    /**
     * http请求
     *
     * @param url
     * @param params    请求参数
     * @param headers   请求头
     * @param method    请求方式
     * @param mediaType 参数类型
     * @return
     */
    public static String request(String url, Object params, MultiValueMap<String, String> headers, HttpMethod method, MediaType mediaType) {
        if (url == null || url.trim().isEmpty()) {
            return null;
        }
        RestTemplate client = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            httpHeaders.addAll(headers);
        }
        httpHeaders.setContentType(mediaType);
        HttpEntity<Object> httpEntity = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<String> response = client.exchange(url, method, httpEntity, String.class);
        return response.getBody();
    }

}
