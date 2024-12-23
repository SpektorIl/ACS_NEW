package com.example.demo.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import jakarta.jms.ConnectionFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Configuration
public class JmsConfig {

    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
        properties.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
        properties.put(Context.PROVIDER_URL, "iiop://localhost:3700");
        InitialContext ctx = new InitialContext(properties);
        return (ConnectionFactory) ctx.lookup("jms/ConnectionFactory");
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setDefaultDestinationName("jms/queue/MyQueue");
        return template;
    }
}
