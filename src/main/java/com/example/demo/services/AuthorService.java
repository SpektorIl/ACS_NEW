package com.example.demo.services;

import com.example.demo.models.Author;
import com.example.demo.models.repositories.AuthorRepository;
import com.example.demo.models.Book;
import com.example.demo.models.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Long id){
        return authorRepository.findById(id);
    }

    public Author save(Author author){
        return authorRepository.save(author);
    }

    public void deleteById(Long id){
        List<Book> books = bookRepository.findByAuthor_Id(id);
        for (Book book : books) {
            bookRepository.deleteById(book.getId());
        }
        authorRepository.deleteById(id);
    };
}
