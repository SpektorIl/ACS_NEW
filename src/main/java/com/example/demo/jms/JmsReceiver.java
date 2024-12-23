package com.example.demo.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class JmsReceiver {

    @JmsListener(destination = "java:/jms/queue/MyQueue")
    public void receiveMessage(String message) {
        System.out.println("Received Message: " + message);
    }
}