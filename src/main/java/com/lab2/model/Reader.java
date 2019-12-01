package com.lab2.model;

import com.lab2.annotations.PrimaryKey;
import com.lab2.annotations.TableName;

@TableName(name="reader")
public class Reader {
    @PrimaryKey
    Long rid;
    String name;
    String favourite_genre;
    Integer age;
    Integer finished_books;

    public Reader() {}

    public Reader(Long rid, String name, String favourite_genre, Integer age, Integer finished_books) {
        this.rid = rid;
        this.name = name;
        this.favourite_genre = favourite_genre;
        this.age = age;
        this.finished_books = finished_books;
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
