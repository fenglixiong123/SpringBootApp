package com.flx.springboot.scaffold.common.utils.http;

import com.alibaba.fastjson.JSON;
import com.flx.springboot.scaffold.common.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
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
    public static OkHttpClient getOKHttp(){
        return getOKHttp(30,30);
    }

    public static OkHttpClient getOKHttp(long connectTime,long readTime){
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectTimeout(connectTime, TimeUnit.SECONDS)
                .readTimeout(readTime, TimeUnit.SECONDS)
                .build();
    }

    public static <T> T get(String url, Map<String, String> queries, Class z) throws Exception {
        Request request = new Request.Builder()
                .url(getPerfectUrl(url,queries)).get().build();
        Response response = getOKHttp()
                .newCall(request)
                .execute();
        return parseResult(response,z);
    }

    public static <T> T post(String url,Map<String, String> queries,String jsonData,Class z) throws Exception {
        RequestBody requestBody = RequestBody.create(jsonData, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(getPerfectUrl(url,queries)).post(requestBody).build();
        Response response = getOKHttp()
                .newCall(request)
                .execute();
        ResponseBody responseBody = response.body();
        String resultString = responseBody==null ? null : responseBody.string();
        return JSON.parseObject(resultString, (Type) z);
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
