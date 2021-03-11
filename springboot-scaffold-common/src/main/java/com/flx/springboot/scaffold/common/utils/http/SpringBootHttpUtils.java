package com.flx.springboot.scaffold.common.utils.http;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.Map;

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
     * RestAPI 调用器
     */
    @Getter
    private final static RestTemplate restTemplate = new RestTemplate();

    private static HttpHeaders headers = new HttpHeaders();

    /**
     * 发送 get 请求
     */
    public static JSONObject get(String url) {
        return getNative(url, null, null).getBody();
    }

    /**
     * 发送 get 请求
     */
    public static JSONObject get(String url, JSONObject variables) {
        return getNative(url, variables, null).getBody();
    }

    /**
     * 发送 get 请求
     */
    public static JSONObject get(String url, JSONObject variables, JSONObject params) {
        return getNative(url, variables, params).getBody();
    }

    /**
     * 发送 get 请求，返回原生 ResponseEntity 对象
     */
    public static ResponseEntity<JSONObject> getNative(String url, JSONObject variables,
                                                       JSONObject params) {
        return request(url, HttpMethod.GET, variables, params);
    }

    /**
     * 发送 Post 请求
     */
    public static JSONObject post(String url) {
        return postNative(url, null, null).getBody();
    }

    /**
     * 发送 Post 请求
     */
    public static JSONObject post(String url, JSONObject params) {
        return postNative(url, null, params).getBody();
    }

    /**
     * 发送 Post 请求
     */
    public static JSONObject post(String url, JSONObject variables, JSONObject params) {
        return postNative(url, variables, params).getBody();
    }

    /**
     * 发送 POST 请求，返回原生 ResponseEntity 对象
     */
    public static ResponseEntity<JSONObject> postNative(String url, JSONObject variables,
                                                        JSONObject params) {
        return request(url, HttpMethod.POST, variables, params);
    }

    /**
     * 发送 put 请求
     */
    public static JSONObject put(String url) {
        return putNative(url, null, null).getBody();
    }

    /**
     * 发送 put 请求
     */
    public static JSONObject put(String url, JSONObject params) {
        return putNative(url, null, params).getBody();
    }

    /**
     * 发送 put 请求
     */
    public static JSONObject put(String url, JSONObject variables, JSONObject params) {
        return putNative(url, variables, params).getBody();
    }

    /**
     * 发送 put 请求，返回原生 ResponseEntity 对象
     */
    public static ResponseEntity<JSONObject> putNative(String url, JSONObject variables,
                                                       JSONObject params) {
        return request(url, HttpMethod.PUT, variables, params);
    }

    /**
     * 发送 delete 请求
     */
    public static JSONObject delete(String url) {
        return deleteNative(url, null, null).getBody();
    }

    /**
     * 发送 delete 请求
     */
    public static JSONObject delete(String url, JSONObject variables, JSONObject params) {
        return deleteNative(url, variables, params).getBody();
    }

    /**
     * 发送 delete 请求，返回原生 ResponseEntity 对象
     */
    public static ResponseEntity<JSONObject> deleteNative(String url, JSONObject variables, JSONObject params) {
        return request(url, HttpMethod.DELETE, null, variables, params, JSONObject.class);
    }

    /**
     * 发送请求
     */
    public static ResponseEntity<JSONObject> request(String url, HttpMethod method,
                                                     JSONObject variables, JSONObject params) {
        return request(url, method, getHeaders(), variables, params, JSONObject.class);
    }

    /**
     * 发送请求
     * @param url 请求地址
     * @param method 请求方式
     * @param headers 请求头 可空
     * @param variables 请求url参数 可空
     * @param params 请求body参数 可空
     * @param responseType 返回类型
     * @return ResponseEntity<responseType>
     */
    public static <T> ResponseEntity<T> request(String url, HttpMethod method,
                                                HttpHeaders headers, JSONObject variables, JSONObject params,
                                                Class<T> responseType) {
        if (StringUtils.isEmpty(url)) {
            throw new RuntimeException("Request url is blank !");
        }
        if (method == null) {
            throw new RuntimeException("Request method is blank !");
        }
        if (headers == null) {
            headers = new HttpHeaders();
        }
        if (variables != null) {
            url += ("?" + asUrlVariables(variables));
        }
        String body = "";
        if (params != null) {
            body = params.toJSONString();
        }
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        return restTemplate.exchange(url, method, request, responseType);
    }

    public void setHeaders(HttpHeaders headers) {
        SpringBootHttpUtils.headers = headers;
    }

    /**
     * 设置请求头
     */
    public void addHeaders(Map<String,String> headMap) {
        Iterator<String> it = headMap.keySet().iterator();
        while (it.hasNext()){
            String key = it.next();
            SpringBootHttpUtils.headers.add(key, headMap.get(key));
        }
    }

    /**
     * 重置header
     */
    public static void resetHeader(){
        headers = new HttpHeaders();
    }

    /**
     * 获取请求头
     */
    public static HttpHeaders getHeaders() {
        return headers.isEmpty() ? getDefaultHeaders():headers;
    }

    public static HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");
        return headers;
    }

    /**
     * 将 JSONObject 转为 a=1&b=2&c=3...&n=n 的形式
     */
    private static String asUrlVariables(JSONObject variables) {
        Map<String, Object> source = variables.getInnerMap();
        Iterator<String> it = source.keySet().iterator();
        StringBuilder urlVariables = new StringBuilder();
        while (it.hasNext()) {
            String key = it.next();
            String value = "";
            Object object = source.get(key);
            if (object != null) {
                if (!StringUtils.isEmpty(object.toString())) {
                    value = object.toString();
                }
            }
            urlVariables.append("&").append(key).append("=").append(value);
        }
        // 去掉第一个&
        return urlVariables.substring(1);
    }

}
