package com.example.demo.controllers.restcontrollers;

import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.models.wrappers.AuthorsWrapper;
import com.example.demo.models.wrappers.BooksWrapper;
import com.example.demo.services.AuthorService;
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
@RequestMapping("/api/authors")
public class AuthorRestController {

    private final AuthorService authorService;


    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //CRUD for authors
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Author addAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @GetMapping(value = "/{authorId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
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

    @GetMapping(value = "/{authorId}/books", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Book> getBooksByAuthor_Id(@PathVariable Long authorId) {
        return authorService.findBooksByAuthor_Id(authorId);
    }

    //============================XML-Answer-Controllers==========================================

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAuthorsAsXmlWithXsl() {
        try {
            // Получаем список авторов
            List<Author> authors = authorService.findAll();

            // Оборачиваем список авторов в корневой элемент
            AuthorsWrapper wrapper = new AuthorsWrapper();
            wrapper.setAuthors(authors);

            // Преобразуем в XML
            JAXBContext jaxbContext = JAXBContext.newInstance(AuthorsWrapper.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            StringWriter xmlWriter = new StringWriter();
            marshaller.marshal(wrapper, xmlWriter);

            String xml = xmlWriter.toString();
            String xmlHeader = """
                    <?xml version="1.0" encoding="UTF-8"?>
                    <?xml-stylesheet type="text/xsl" href="/authors.xsl"?>
                    """;
            // Добавляем XSL ссылку
            String xmlWithXsl = xmlHeader + xml;


            return ResponseEntity.ok(xmlWithXsl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/{authorId}/books/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getBooksByAuthorIdAsXmlWithXsl(@PathVariable Long authorId) {
        try {
            // Получаем список авторов
            List<Book> books = authorService.findBooksByAuthor_Id(authorId).stream().toList();

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

    @GetMapping(value = "/{authorId}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAuthorsByIdAsXmlWithXsl(@PathVariable Long authorId) {
        try {
            // Получаем список авторов
            List<Author> authors = authorService.findById(authorId).stream().toList();

            // Оборачиваем список авторов в корневой элемент
            AuthorsWrapper wrapper = new AuthorsWrapper();
            wrapper.setAuthors(authors);

            // Преобразуем в XML
            JAXBContext jaxbContext = JAXBContext.newInstance(AuthorsWrapper.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            StringWriter xmlWriter = new StringWriter();
            marshaller.marshal(wrapper, xmlWriter);

            String xml = xmlWriter.toString();
            String xmlHeader =  """
                    <?xml version="1.0" encoding="UTF-8"?>
                    <?xml-stylesheet type="text/xsl" href="/authors.xsl"?>
                    """;
            // Добавляем XSL ссылку
            String xmlWithXsl = xmlHeader + xml;


            return ResponseEntity.ok(xmlWithXsl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
