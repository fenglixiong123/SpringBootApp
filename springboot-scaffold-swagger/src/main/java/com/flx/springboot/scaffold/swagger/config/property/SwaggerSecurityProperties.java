package com.flx.springboot.scaffold.swagger.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Fenglixiong
 * @Create 2018.11.07 17:37
 * @Description Swagger要测试Restful api接口时，往往要从header中获取一些信息，
 * 这些信息就可以通过SwaggerSecurityProperties传入到swagger的请求中
 **/
@Data
@ConfigurationProperties(prefix = SwaggerSecurityProperties.PREFIX)
public class SwaggerSecurityProperties {

    public static final String PREFIX = "smsf.com.flx.springboot.scaffold.swagger.security";

    private boolean enabled = false;

    private Map<String,ApiKey> schemes = new HashMap<>(0);

    private String scope = "global";

    private String scopeDescription = "Global authorization setting";

    @Data
    public static class ApiKey{
        String keyName;
        String passAs = "header";
    }

}
