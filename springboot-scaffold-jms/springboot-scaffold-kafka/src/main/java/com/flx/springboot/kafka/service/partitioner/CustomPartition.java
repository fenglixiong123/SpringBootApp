package com.flx.springboot.kafka.service.partitioner;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.springframework.beans.factory.InitializingBean;

import java.util.Map;

/**
 * @Author Fenglixiong
 * @Create 2019.01.14 20:12
 * @Description 自定义分区规则
 * DefaultPartitioner 默认分区规则可以参考
 * 可以在配置文件中进行配置：spring.kafka.producer.properties.partitioner.class=
 * com.flx.springboot.scaffold.jms.message.kafka.partitioner.CustomPartition
 **/
@Slf4j
public class CustomPartition implements Partitioner , InitializingBean {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //拿到分区
        Integer count = cluster.partitionCountForTopic(topic);
        String keyString = key.toString();
        if(count==3 && StringUtils.isNotBlank(keyString)){
            if(keyString.startsWith("135")){
                return 0;
            }else if(keyString.startsWith("130")){
                return 1;
            }else if(keyString.startsWith("188")){
                return 2;
            }
        }
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(">>>>>>>>>>>>>CustomPartition Load<<<<<<<<<<<<<<");
    }
}
