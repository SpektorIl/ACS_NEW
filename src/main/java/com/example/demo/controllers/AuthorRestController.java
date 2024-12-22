package com.example.demo.controllers;

import com.example.demo.models.Author;
import com.example.demo.services.AuthorService;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    // CRUD for authors
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Author addAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @GetMapping(value ="/{authorId}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Optional<Author> getAuthorById(@PathVariable Long authorId) {
        return authorService.findById(authorId);
    }

    @PutMapping(value = "/{authorId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Author updateAuthor(@PathVariable Long authorId, @RequestBody Author author) {
        author.setId(authorId);
        return authorService.save(author);
    }

    @DeleteMapping("/{authorId}")
    public void deleteAuthor(@PathVariable Long authorId) {
        authorService.deleteById(authorId);
    }
}