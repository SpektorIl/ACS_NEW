package com.example.demo.models;

import jakarta.persistence.*;


@Entity
@SuppressWarnings("unused")
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String title;

    private String publication_date;

    private String genre;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book() {

    }

    public Book(Long id, String title, String publication_date, String genre, Long author_id, Author author) {
        this.id = id;
        this.title = title;
        this.publication_date = publication_date;
        this.genre = genre;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
