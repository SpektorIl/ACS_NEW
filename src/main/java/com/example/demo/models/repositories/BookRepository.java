package com.example.demo.models.repositories;

import com.example.demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository <Book, Long> {
    public List<Book> findByAuthor_Id(Long id);
}