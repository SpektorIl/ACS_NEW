package com.example.demo.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository <Book, Long> {
    public List<Book> findByAuthor_Id(Long id);
}