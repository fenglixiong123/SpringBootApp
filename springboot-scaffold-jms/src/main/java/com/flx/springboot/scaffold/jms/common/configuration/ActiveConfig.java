package com.flx.springboot.scaffold.jms.common.configuration;

import com.flx.springboot.scaffold.jms.common.constants.ActiveConstant;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Topic;

/**
 * @Author Fenglixiong
 * @Create 2019.01.24 18:19
 * @Description
 **/
@Configuration
public class ActiveConfig {

    @Bean
    public Destination queue(){
        return new ActiveMQQueue(ActiveConstant.ActiveQueuePoint);
    }

    @Bean
    public Topic topic(){
        return new ActiveMQTopic(ActiveConstant.ActiveQueueTopic);
    }

    /**
     * 配置ActiveMq连接工厂
     * @return
     */
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setUserName("admin");
        activeMQConnectionFactory.setUserName("admin123");
        activeMQConnectionFactory.setBrokerURL("tcp://192.168.56.104:61616");
        return activeMQConnectionFactory;
    }

    /**
     * ActiveMQ为我们提供了一个PooledConnectionFactory，通过往里面注入一个ActiveMQConnectionFactory
     *         可以用来将Connection、Session和MessageProducer池化，这样可以大大的减少我们的资源消耗。
     *         要依赖于 activemq-pool包
     * @return
     */
    @Bean
    public PooledConnectionFactory pooledConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory){
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory);
        pooledConnectionFactory.setMaxConnections(50);
        return pooledConnectionFactory;
    }

    /**
     * Spring用于管理真正的ConnectionFactory的ConnectionFactory
     * @return
     */
    @Bean
    public SingleConnectionFactory singleConnectionFactory(PooledConnectionFactory pooledConnectionFactory){
        SingleConnectionFactory connectionFactory = new SingleConnectionFactory(pooledConnectionFactory);
        connectionFactory.setReconnectOnException(true);
        return connectionFactory;
    }

    /**
     * 原始的JmsTemplate
     * @return
     */
    @Bean
    public JmsTemplate jmsTemplate(SingleConnectionFactory singleConnectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate(singleConnectionFactory);
        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
        jmsTemplate.setPriority(999);
        return jmsTemplate;
    }

    /**
     * 封装了JmsTemplate
     * @param jmsTemplate
     * @return
     */
    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(JmsTemplate jmsTemplate){
        return new JmsMessagingTemplate(jmsTemplate);
    }

    /**
     * 点对点
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactoryPoint(SingleConnectionFactory singleConnectionFactory) {
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setConnectionFactory(singleConnectionFactory);
        jmsListenerContainerFactory.setPubSubDomain(false);
        return jmsListenerContainerFactory;
    }

    /**
     * 订阅
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactoryTopic(SingleConnectionFactory singleConnectionFactory) {
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setConnectionFactory(singleConnectionFactory);
        jmsListenerContainerFactory.setPubSubDomain(true);
        return jmsListenerContainerFactory;
    }

}
