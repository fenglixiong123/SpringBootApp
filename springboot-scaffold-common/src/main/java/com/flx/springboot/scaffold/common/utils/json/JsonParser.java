package com.flx.springboot.scaffold.common.utils.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Fenglixiong
 * @Create 2018.12.03 17:53
 * @Description
 **/
public class JsonParser {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        // 为保持对象版本兼容性,忽略未知的属性
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 序列化的时候，跳过null值
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // date类型转化
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MAPPER.setDateFormat(fmt);
    }

    /**
     * 将一个json字符串解码为java对象
     *
     * 注意：如果传入的字符串为null，那么返回的对象也为null
     *
     * @param json json字符串
     * @param classZ  对象类型
     * @return 解析后的java对象
     * @throws RuntimeException 若解析json过程中发生了异常
     */
    private static <T> T toObject(String json, Class<T> classZ) {
        if (json == null) {
            return null;
        }
        try {
            return MAPPER.readValue(json, classZ);
        } catch (Exception e) {
            return null;
        }
    }



    /**
     * 对于正确JSON及存在的Path下获取到最终指定值并转成字符串，其他情况一律返回 null
     * @param json JSON串
     * @param key 点分隔的字段路径
     * @return 相应字段的字符串值
     */
    public static String readVal(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        HashMap map = toObject(json,HashMap.class);
        if (map == null) {
            return null;
        }
        String[] subPaths = key.split("\\.");
        return readVal(map, subPaths);
    }


    private static String readVal(HashMap map, String[] subPaths) {
        Object val = map;
        try {
            for (String subPath : subPaths) {
                if (val instanceof Map) {
                    val = ((Map) val).get(subPath);
                } else {
                    return null;
                }
            }
            return val == null ? null : val.toString();
        } catch (Exception ex) {
            return null;
        }
    }

}
