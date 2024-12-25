package com.example.demo.services.jms;


import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import jakarta.jms.ConnectionFactory;

import java.util.Arrays;


/**
 * Конфигураитор JMS
 *
 */
@Configuration
@EnableJms
public class JmsConfig {

    // Настройка встроенного брокера ActiveMQ
    @Bean
    public BrokerService broker() throws Exception {
        BrokerService broker = new BrokerService();
        broker.setBrokerName("embedded");
        broker.addConnector("vm://embedded");
        broker.setPersistent(false); // Включить хранение данных только в памяти
        return broker;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://embedded");
        connectionFactory.setTrustedPackages(Arrays.asList("com.example.demo.models", "java.util", "java.lang", "java.time"));
        return connectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1-1"); // Настройка минимального и максимального числа потоков
        factory.setPubSubDomain(false); // Использование очередей (point-to-point)
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(false); // Использование очередей
        return jmsTemplate;
    }
}
