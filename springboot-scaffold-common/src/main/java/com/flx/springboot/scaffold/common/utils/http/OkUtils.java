package com.flx.springboot.scaffold.common.utils.http;

import com.alibaba.fastjson.JSON;
import com.flx.springboot.scaffold.common.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Fenglixiong
 * @Date: 2021/3/10 16:20
 * @Description:
 */
@Slf4j
public class OkUtils {

    /**
     * 获取OKHttpClient
     */
    public static OkHttpClient getOK(){
        return getOK(30,30);
    }

    public static OkHttpClient getOK(long connectTime,long readTime){
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectTimeout(connectTime, TimeUnit.SECONDS)
                .readTimeout(readTime, TimeUnit.SECONDS)
                .build();
    }

    public static <T> T get(String url, Class z) throws Exception{
        return get(url,null,z);
    }

    public static <T> T get(String url, Map<String, String> queries, Class z) throws Exception {
        Request request = new Request.Builder()
                .url(getPerfectUrl(url,queries)).get().build();
        Response response = getOK()
                .newCall(request)
                .execute();
        return parseResult(response,z);
    }

    public static <T> T post(String url,String jsonData,Class z) throws Exception{
        return post(url,null,jsonData,z);
    }

    public static <T> T post(String url,Map<String, String> queries,String jsonData,Class z) throws Exception {
        RequestBody requestBody = RequestBody.create(jsonData, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(getPerfectUrl(url,queries)).post(requestBody).build();
        Response response = getOK()
                .newCall(request)
                .execute();
        ResponseBody responseBody = response.body();
        String resultString = responseBody==null ? null : responseBody.string();
        return JSON.parseObject(resultString, (Type) z);
    }

    /**
     * get返回字符串
     */
    public static String getForString(String url, Map<String, String> queries) throws Exception {
        return Optional.ofNullable(getOK().newCall(new Request.Builder().url(getPerfectUrl(url,queries)).get().build()).execute().body()).map(responseBody -> {
            try {
                return responseBody.string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).orElse(null);
    }

    /**
     * post返回字符串
     */
    public static String postForString(String url,Map<String, String> queries,String jsonData) throws Exception {
        return Optional.ofNullable(getOK().newCall(new Request.Builder().url(getPerfectUrl(url,queries)).post(RequestBody.create(jsonData,MediaType.parse("application/json; charset=utf-8"))).build()).execute().body()).map(responseBody -> {
            try {
                return responseBody.string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).orElse(null);

    }

    /**
     * 处理url，将query数据代入
     */
    private static String getPerfectUrl(String url, Map<String, String> queries) throws Exception {
        if(StringUtils.isBlank(url)){
            throw new Exception("url is empty !");
        }
        StringBuilder sb = new StringBuilder(url);
        if (!CollectionUtils.isEmpty(queries)) {
            int i = 0;
            for (Map.Entry<String, String> query : queries.entrySet()){
                if(i==0){
                    sb.append("?").append(query.getKey()).append("=").append(query.getValue());
                }else {
                    sb.append("&").append(query.getKey()).append("=").append(query.getValue());
                }
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * 处理返回结果
     */
    private static <T> T parseResult(Response response,Class z) throws IOException {
        ResponseBody responseBody = response.body();
        String resultString = responseBody==null ? null : responseBody.string();
        return JSON.parseObject(resultString, (Type) z);
    }

}
