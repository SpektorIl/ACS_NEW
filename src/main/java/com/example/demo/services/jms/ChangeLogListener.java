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
public class ChangeLogListener {

    private final ChangeLogRepository changeLogRepository;

    public ChangeLogListener(ChangeLogRepository changeLogRepository) {
        this.changeLogRepository = changeLogRepository;
    }

    @JmsListener(destination = "jms/ChangeLogQueue")
    public void handleMessage(ChangeLog message) {
        ChangeLog changeLog = new ChangeLog();
        changeLog.setChangeType(message.getChangeType());
        changeLog.setEntityClass(message.getEntityClass());
        changeLog.setEntityId(message.getEntityId());
        changeLog.setChangeDetails(message.getChangeDetails());
        changeLogRepository.save(changeLog);
    }
}
