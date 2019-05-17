package com.flx.springboot.scaffold.swagger.config;

import com.flx.springboot.scaffold.web.core.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.flx.springboot.scaffold.swagger.config.property.SwaggerInfoProperties;
import com.flx.springboot.scaffold.swagger.config.property.SwaggerSecurityProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2018.11.07 17:37
 * @Description 主要初始化了一个开发框架给定了一些默认配置(来自properties包里面)的swagger服务
 **/
@Slf4j
@Configuration
@EnableSwagger2
@EnableConfigurationProperties({
        SwaggerInfoProperties.class,
        SwaggerSecurityProperties.class})
public class SwaggerConfig {

    @Autowired
    private SwaggerInfoProperties swaggerInfoProperty;

    @Autowired
    private SwaggerSecurityProperties swaggerSecurityProperty;

    @Bean
    public Docket createRestApi(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName(swaggerInfoProperty.getGroupName())
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .apiInfo(apiInfo());
        if(swaggerSecurityProperty.isEnabled()){
            docket = docket
                    .securitySchemes(securitySchemes())
                    .securityContexts(securityContexts());
        }
        String basePackage = swaggerInfoProperty.getBasePackage();
        ApiSelectorBuilder apiSelectorBuilder;
        if(StringUtils.isBlank(basePackage)){
            apiSelectorBuilder = docket.select()
                    .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class));
        }else {
            apiSelectorBuilder = docket.select()
                    .apis(RequestHandlerSelectors.basePackage(basePackage));
        }
        log.info(">>>>>>>>>>Swagger UI 启动啦!<<<<<<<<<<<<");
        return apiSelectorBuilder.build();
    }

    private List<ApiKey> securitySchemes(){
        List<ApiKey> apiKeys = new ArrayList<>(swaggerSecurityProperty.getSchemes().size());
        swaggerSecurityProperty.getSchemes().forEach((key,value)->{
            apiKeys.add(new ApiKey(key,value.getKeyName(),value.getPassAs()));
        });
        return apiKeys;
    }

    private List<SecurityContext> securityContexts(){
        List<SecurityContext> securityContexts = new ArrayList<>(1);
        securityContexts.add(SecurityContext.builder()
        .securityReferences(securityReferences()).build());
        return securityContexts;
    }

    private List<SecurityReference> securityReferences(){
        AuthorizationScope[] authorizationScopes = {
                new AuthorizationScope(swaggerSecurityProperty.getScope(),
                        swaggerSecurityProperty.getScopeDescription())
        };
        List<SecurityReference> securityReferences = new ArrayList<>(swaggerSecurityProperty.getSchemes().size());
        swaggerSecurityProperty.getSchemes().forEach((key,value)->{
            securityReferences.add(new SecurityReference(key,authorizationScopes));
        });
        return securityReferences;
    }

    /**
     * 基本信息
     * @return
     */
    private ApiInfo apiInfo(){
        String name = swaggerInfoProperty.getAuthor();
        String homepage = swaggerInfoProperty.getHomepage();
        String email = swaggerInfoProperty.getEmail();
        Contact contact = new Contact(name,homepage,email);
        return new ApiInfoBuilder()
                .title(swaggerInfoProperty.getTitle())
                .description(swaggerInfoProperty.getDescription())
                .license(swaggerInfoProperty.getLicense())
                .licenseUrl(swaggerInfoProperty.getLicenseUrl())
                .contact(contact)
                .termsOfServiceUrl(swaggerInfoProperty.getTermsOfServiceUrl())
                .version(swaggerInfoProperty.getVersion())
                .build();
    }

}
