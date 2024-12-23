package com.example.demo.controllers.restcontrollers;

import com.example.demo.models.Book;
import com.example.demo.models.wrappers.BooksWrapper;
import com.example.demo.services.BookService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;


    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    // CRUD for books
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Book> getAllBooks() {
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


    //=================XML-Answer-Controllers=================

    @GetMapping(value = "/xml", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> getBooksAsXmlWithXsl() {
        try{
            List<Book> books = bookService.findAll();

            BooksWrapper wrapper = new BooksWrapper(books);

            JAXBContext jaxbContext = JAXBContext.newInstance(BooksWrapper.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            StringWriter xmlWriter = new StringWriter();
            marshaller.marshal(wrapper, xmlWriter);

            String xml = xmlWriter.toString();
            String xmlHeader = """
                    <?xml version="1.0" encoding="UTF-8"?>
                    <?xml-stylesheet type="text/xsl" href="/books.xsl"?>
                    """;

            String xmlWithXls = xmlHeader + xml;

            return ResponseEntity.ok(xmlWithXls);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping(value = "/{bookId}/xml", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> getBooksByIdAsXmlWithXsl(@PathVariable Long bookId) {
        try{
            List<Book> books = bookService.findById(bookId).stream().toList();

            BooksWrapper wrapper = new BooksWrapper(books);

            JAXBContext jaxbContext = JAXBContext.newInstance(BooksWrapper.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            StringWriter xmlWriter = new StringWriter();
            marshaller.marshal(wrapper, xmlWriter);

            String xml = xmlWriter.toString();
            String xmlHeader = """
                    <?xml version="1.0" encoding="UTF-8"?>
                    <?xml-stylesheet type="text/xsl" href="/books.xsl"?>
                    """;

            String xmlWithXls = xmlHeader + xml;

            return ResponseEntity.ok(xmlWithXls);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }
}


