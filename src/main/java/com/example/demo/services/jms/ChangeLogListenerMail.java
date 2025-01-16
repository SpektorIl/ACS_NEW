package com.example.demo.services.jms;

import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.models.ChangeLog;
import com.example.demo.models.NotificationCondition;
import com.example.demo.models.repositories.AuthorRepository;
import com.example.demo.models.repositories.BookRepository;
import com.example.demo.models.repositories.ChangeLogRepository;
import com.example.demo.models.repositories.NotificationConditionRepository;
import com.example.demo.services.EmailService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ChangeLogListenerMail {
    private final EmailService emailService;

    private final NotificationConditionRepository notificationConditionRepo;
    private final AuthorRepository authorRepo;
    private final BookRepository bookRepo;

    public ChangeLogListenerMail(EmailService emailService, NotificationConditionRepository notificationConditionRepo, AuthorRepository authorRepo, BookRepository bookRepo) {
        this.emailService = emailService;
        this.notificationConditionRepo = notificationConditionRepo;
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    @JmsListener(destination = "jms/ChangeLogQueueMail")
    public void onChange(ChangeLog message) {
        // Получаем имя сущности и отправляем уведомление
        String entityName = message.getEntityClass();
        // Логика проверки условий
        List<NotificationCondition> conditions = getConditionsForEntity(entityName);


        for (NotificationCondition condition : conditions) {
            if(Objects.equals(entityName, "Author"))
            {
                if (authorRepo.findById(message.getEntityId()).isPresent()){
                    Author author;
                    author = authorRepo.findById(message.getEntityId()).get();
                    if(condition.getAttributeCondition().contains(author.getName())) {
                        emailService.sendEmail(
                                condition.getEmail(),
                                "Entity Updated: " + entityName,
                                "Details: " + condition.getAttributeCondition()
                        );
                    }

                }
            }

            if(Objects.equals(entityName, "Book"))
            {
                if (bookRepo.findById(message.getEntityId()).isPresent()){
                    Book book;
                    book = bookRepo.findById(message.getEntityId()).get();
                    if(condition.getAttributeCondition().contains(book.getTitle())) {
                        emailService.sendEmail(
                                condition.getEmail(),
                                "Entity Updated: " + entityName,
                                "Details: " + condition.getAttributeCondition()
                        );
                    }

                }
            }


        }
    }

    private List<NotificationCondition> getConditionsForEntity(String entityName) {
        List<NotificationCondition> conditions = new ArrayList<>();
        conditions = notificationConditionRepo.findAllByEntityNameEquals(entityName);
        return conditions;
    }
}
