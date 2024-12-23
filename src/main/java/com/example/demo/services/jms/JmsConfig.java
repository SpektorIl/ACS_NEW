package com.example.demo.services.jms;

import jakarta.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;


import javax.naming.NamingException;
import java.util.Properties;


@EnableJms
@Configuration
public class JmsConfig {

    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {
        JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
        factoryBean.setJndiName("jms/ChangeLogConnectionFactory");

        // Задаём свойства для удалённого JNDI
        Properties props = new Properties();
        props.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

        // ВАЖНО: Хост и порт GlassFish
        props.put("org.omg.CORBA.ORBInitialHost", "localhost");
        props.put("org.omg.CORBA.ORBInitialPort", "3700");

        // Для "внешнего" клиента обычно resourceRef = false
        factoryBean.setResourceRef(false);
        factoryBean.setProxyInterface(ConnectionFactory.class);
        factoryBean.setJndiEnvironment(props);
        factoryBean.setLookupOnStartup(false);
        factoryBean.afterPropertiesSet();
        return (ConnectionFactory) factoryBean.getObject();
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory(ConnectionFactory connectionFactory) {
        return new CachingConnectionFactory(connectionFactory);
    }

    @Bean
    public JmsTemplate jmsTemplate(CachingConnectionFactory cachingConnectionFactory) {
        return new JmsTemplate(cachingConnectionFactory);
    }
}