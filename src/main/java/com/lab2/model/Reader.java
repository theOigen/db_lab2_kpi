package com.lab2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="reader")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    private String name;
    private String favourite_genre;
    private Integer age;
    private Integer finished_books;

    @OneToMany(mappedBy = "ownerOf", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscription> subscriptionList;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "reader_book",
            joinColumns = @JoinColumn(name = "rid"),
            inverseJoinColumns = @JoinColumn(name = "bid")
    )
    private List<Book> books = new ArrayList<>();

    public Reader() {}

    public Reader(Long rid, String name, String favourite_genre, Integer age, Integer finished_books) {
        this.rid = rid;
        this.name = name;
        this.favourite_genre = favourite_genre;
        this.age = age;
        this.finished_books = finished_books;
        this.subscriptionList = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    public Long getRid() {
        return rid;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getFavourite_genre() {
        return favourite_genre;
    }

    public Integer getFinished_books() {
        return finished_books;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setFavourite_genre(String favourite_genre) {
        this.favourite_genre = favourite_genre;
    }

    public void setFinished_books(Integer finished_books) {
        this.finished_books = finished_books;
    }
}
