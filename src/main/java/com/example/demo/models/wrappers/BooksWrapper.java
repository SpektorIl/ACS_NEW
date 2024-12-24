package com.example.demo.models.wrappers;

import com.example.demo.models.Book;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "books")
public class BooksWrapper {

    private List<Book> books;

    public BooksWrapper() {}

    public BooksWrapper(List<Book> books) {

        this.books = books;
    }

    @XmlElement(name = "book")
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
