package com.flx.springboot.scaffold.jms.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Fenglixiong
 * @Create 2019.01.25 12:28
 * @Description
 **/
//@Component
//@ConfigurationProperties(prefix="jms.activemq")
public class ActiveMQProperties {
    //消息队列名称
    private String queueName;

    //通知主题名称
    private String topicName;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

}
