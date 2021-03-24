package com.flx.springboot.scaffold.common;

import com.flx.springboot.scaffold.common.http.OkUtils;

/**
 * @Author: Fenglixiong
 * @Date: 2021/3/24 13:53
 * @Description:
 */
public class HttpUtilsTest {

    public static void main(String[] args) throws Exception {

        String qrcodeUrl = "http://172.31.236.108:9001/rcs/ops-task/scan-code/query/overall-report";
        String result = OkUtils.postString(qrcodeUrl);
        System.out.println(result);
        String bucketUrl = "http://172.31.236.108:8071/api/wcs/bucket/valid/task/getBucketInfoAndValidateResult";
        result = OkUtils.getString(bucketUrl);
        System.out.println(result);
    }

}
