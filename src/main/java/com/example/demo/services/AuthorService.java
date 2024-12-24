package com.example.demo.services;

import com.example.demo.models.Author;
import com.example.demo.models.repositories.AuthorRepository;
import com.example.demo.models.Book;
import com.example.demo.models.repositories.BookRepository;
import com.example.demo.services.jms.ChangeLogMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final JmsTemplate jmsTemplate;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository, JmsTemplate jmsTemplate) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.jmsTemplate = jmsTemplate;
    }

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Long id){
        return authorRepository.findById(id);
    }

    public Author save(Author author){
        Author savedAuthor = authorRepository.save(author);
        sendChangeLog("CREATE_OR_UPDATE", "Author", savedAuthor.getId(), "Author saved or updated");
        return savedAuthor;
    }

    public void deleteById(Long id){
        List<Book> books = bookRepository.findByAuthor_Id(id);
        for (Book book : books) {
            bookRepository.deleteById(book.getId());
        }
        authorRepository.deleteById(id);
    };

    public List<Book> findBooksByAuthor_Id(Long authorId) {
        return bookRepository.findByAuthor_Id(authorId);
    }

    private void sendChangeLog(String changeType, String entityClass, Long entityId, String details) {
        ChangeLogMessage message = new ChangeLogMessage(changeType, entityClass, entityId, details);
        jmsTemplate.convertAndSend("jms/ChangeLogQueue", message);
    }
}
