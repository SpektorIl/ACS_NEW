package com.example.demo.services.jms;

import org.jvnet.hk2.annotations.Service;
import org.springframework.jms.core.JmsTemplate;

@Service
public class JmsSender {

    private final JmsTemplate jmsTemplate;



    public JmsSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String message) {
        jmsTemplate.convertAndSend("jms/ChangeLogQueue", message);
    }

}
