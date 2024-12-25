package com.example.demo.services.jms;


import jakarta.jms.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JmsConnectionTest {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Test
    public void testConnectionFactory() {
        assertNotNull(connectionFactory);
        System.out.println("Connection Factory is available.");
    }
}
