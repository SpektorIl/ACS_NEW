package com.example.demo.services;

import com.example.demo.models.Book;
import com.example.demo.models.repositories.BookRepository;
import com.example.demo.services.jms.ChangeLogMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final JmsTemplate jmsTemplate;

    public BookService(BookRepository bookRepository, JmsTemplate jmsTemplate) {
        this.bookRepository = bookRepository;
        this.jmsTemplate = jmsTemplate;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }

    public Book save(Book book){
        Book savedBook = bookRepository.save(book);
        sendChangeLog("CREATE_OR_UPDATE", "Book", savedBook.getId(), "Book saved or updated");
        return bookRepository.save(book);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
        sendChangeLog("DELETE", "Book", id, "Book deleted");
    }

    private void sendChangeLog(String changeType, String entityClass, Long entityId, String details) {
        ChangeLogMessage message = new ChangeLogMessage(changeType, entityClass, entityId, details);
        jmsTemplate.convertAndSend("jms/ChangeLogQueue", message);
    }

}