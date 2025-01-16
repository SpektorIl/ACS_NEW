package com.example.demo.services.jms;

import com.example.demo.models.ChangeLog;
import com.example.demo.models.repositories.ChangeLogRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;


/**
 * Слушает сообщения он ChangeLogEntityManager
 * при получении десериализует его и делает запись в бд
 */
@Service
public class ChangeLogListenerDB {

    private final ChangeLogRepository changeLogRepository;

    public ChangeLogListenerDB(ChangeLogRepository changeLogRepository) {
        this.changeLogRepository = changeLogRepository;
    }

    @JmsListener(destination = "jms/ChangeLogQueue")
    public void handleMessage(ChangeLog message) {
        changeLogRepository.save(message);
    }
}
