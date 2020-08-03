package com.flx.springboot.scaffold.mybatis.plus.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 获取字段别名，用于Mybatis条件构造器
 * @author fenglixiong
 */
@Slf4j
@Configuration
public class ScanFieldAlias implements ResourceLoaderAware {

    /**
     * 表名前缀
     */
    @Value("${spring.flx.table.prefix:basic_}")
    private String tablePrefix;
    @Value("${spring.flx.entity.package:com/flx/springboot/scaffold/mybatis/plus/entity}")
    private String entityPackage;

    public static Map<String, String> fieldAliasMap = new HashMap<>();
    public static List<String> tableName = new ArrayList<>();

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    @Transactional(rollbackFor = Exception.class)
    public void getTableFieldValue() throws Exception {
        long start=System.currentTimeMillis();
        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        MetadataReaderFactory metaReader = new CachingMetadataReaderFactory(resourceLoader);
        entityPackage = Objects.requireNonNull(entityPackage).replaceAll("\\.","/");
        String realLocations = "classpath*:"+entityPackage+"/*.class";
        Resource[] resources = resolver.getResources(realLocations);
        for (Resource r : resources) {
            MetadataReader reader = metaReader.getMetadataReader(r);
            ClassMetadata entityClass = reader.getClassMetadata();
            Class<?> clazz = Class.forName(entityClass.getClassName());
            if (clazz.getAnnotation(TableName.class) != null) {
                TableName entity = clazz.getAnnotation(TableName.class);
                if (StringUtils.isNotEmpty(entity.value()) && entity.value().startsWith(tablePrefix)) {
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        if (field.getAnnotation(TableField.class) != null) {
                            TableField t = field.getAnnotation(TableField.class);
                            if (StringUtils.isNotEmpty(t.value())) {
                                fieldAliasMap.put(field.getName(), t.value());
                            }
                        }
                    }
                    tableName.add(entity.value());
                }
            }

        }

        log.info("GetTable:"+(System.currentTimeMillis()-start));
        log.info("Get table field success,fieldAlias=" + fieldAliasMap.toString());

    }


}
