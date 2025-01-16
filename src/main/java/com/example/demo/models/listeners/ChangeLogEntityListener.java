package com.example.demo.models.listeners;


import com.example.demo.models.ChangeLog;
import com.example.demo.models.NotificationCondition;
import com.example.demo.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Слушает изменения помеченных объектов,
 * отправляет сообщения в очередь для получения их в ChangeLogListener
 */
@Component
public class ChangeLogEntityListener {

    private JmsTemplate jmsTemplate;

    public ChangeLogEntityListener(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostPersist
    public void afterCreate(Object entity) {
            sendChangeLog("CREATE", entity);
    }

    @PostUpdate
    public void afterUpdate(Object entity) {
        sendChangeLog("UPDATE", entity);
    }

    @PostRemove
    public void afterRemove(Object entity) {
        sendChangeLog("DELETE", entity);
    }

    private void sendChangeLog(String changeType, Object entity) {
        ChangeLog message = new ChangeLog(changeType, entity.getClass().getSimpleName(),
                getEntityId(entity), entity.toString());
        jmsTemplate.convertAndSend("jms/ChangeLogQueue", message);
    }

    private Long getEntityId(Object entity) {
        try {
            return (Long) entity.getClass().getMethod("getId").invoke(entity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve ID from entity", e);
        }
    }
}