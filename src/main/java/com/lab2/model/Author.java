package com.lab2.model;


import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;

    private String name;
    private Date birth_date;
    private String nationality;
    private boolean isalive;

    @OneToMany(mappedBy = "authorOf", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    public Author() {}

    public Author(Long aid, String name, Date birth_date, String nationality, boolean isALive) {
        this.aid = aid;
        this.name = name;
        this.birth_date = birth_date;
        this.nationality = nationality;
        this.isalive = isALive;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getAid() {
        return aid;
    }

    public String getName() {
        return name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public String getNationality() {
        return nationality;
    }

    public boolean isAlive() {
        return isalive;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAlive(boolean alive) {
        isalive = alive;
    }
}
