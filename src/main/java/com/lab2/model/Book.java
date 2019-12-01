package com.lab2.model;

import com.lab2.annotations.PrimaryKey;
import com.lab2.annotations.TableName;

@TableName(name="book")
public class Book {
    @PrimaryKey
    Long bid;
    String title;
    Integer pages_count;
    String genre;
    String original_language;
    Long author;

    public Book() {}

    public Book(Long bid, String title, Integer pages_count, String genre, String original_language, Long author) {
        this.bid = bid;
        this.title = title;
        this.pages_count = pages_count;
        this.genre = genre;
        this.original_language = original_language;
        this.author = author;
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
