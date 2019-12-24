package com.lab2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid;

    private String title;
    private Integer pages_count;
    private String genre;
    private String original_language;

    @Column(name = "author", insertable = false, updatable = false)
    private Long author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private Author authorOf;

    @ManyToMany(mappedBy = "books")
    private List<Reader> readers = new ArrayList<>();

    @ManyToMany(mappedBy = "books")
    private List<Subscription> subscriptions = new ArrayList<>();

    public Book() {}

    public Book(Long bid, String title, Integer pages_count, String genre, String original_language, Long author) {
        this.bid = bid;
        this.title = title;
        this.pages_count = pages_count;
        this.genre = genre;
        this.original_language = original_language;
        this.author = author;
    }

    public Author getAuthorOf() {
        return authorOf;
    }

    public void setAuthorOf(Author authorOf) {
        this.authorOf = authorOf;
    }

    public Long getBid() {
        return bid;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPages_count() {
        return pages_count;
    }

    public String getGenre() {
        return genre;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public Long getAuthor() {
        return author;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPages_count(Integer pages_count) {
        this.pages_count = pages_count;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }
}
