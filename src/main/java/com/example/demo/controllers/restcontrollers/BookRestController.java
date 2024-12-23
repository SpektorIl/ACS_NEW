package com.example.demo.controllers.restcontrollers;

import com.example.demo.models.Book;
import com.example.demo.services.AuthorService;
import com.example.demo.services.BookService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    private final AuthorService authorService;

    public BookRestController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    // CRUD for books
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping(value = "/xml", produces = "application/xml")
    @ResponseBody
    public List<Book> getBooksAsXml() {
        return bookService.findAll();
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping(value = "/{bookId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Optional<Book> getBookById(@PathVariable Long bookId) {
        return bookService.findById(bookId);
    }

    @PutMapping(value= "/{bookId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Book updateBook(@PathVariable Long bookId, @RequestBody Book book) {
        book.setId(bookId);
        return bookService.save(book);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteById(bookId);
    }
}

