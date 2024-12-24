package com.example.demo.services.jms;

import com.example.demo.models.ChangeLog;
import com.example.demo.models.repositories.ChangeLogRepository;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/ChangeLogQueue"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
        }
)
public class ChangeLogMDB implements MessageListener {

    private final ChangeLogRepository changeLogRepository;

    @Autowired
    public ChangeLogMDB(ChangeLogRepository changeLogRepository) {
        this.changeLogRepository = changeLogRepository;
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String json = ((TextMessage) message).getText();
                ObjectMapper mapper = new ObjectMapper();
                ChangeLogMessage changeLogMessage = mapper.readValue(json, ChangeLogMessage.class);

                // Логика записи в таблицу
                ChangeLog log = new ChangeLog();
                log.setChangeType(changeLogMessage.getChangeType());
                log.setEntityClass(changeLogMessage.getEntityClass());
                log.setEntityId(changeLogMessage.getEntityId());
                log.setChangeDetails(changeLogMessage.getDetails());
                log.setChangeTimestamp(LocalDateTime.now());

                // Используем DAO или EntityManager для сохранения
                changeLogRepository.save(log);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
