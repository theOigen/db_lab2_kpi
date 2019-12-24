package com.lab2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    private String type;
    private Integer price;
    private String validity;

    @Column(name = "owner", insertable = false, updatable = false)
    private Long owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Reader ownerOf;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "subscription_book",
            joinColumns = @JoinColumn(name = "sid"),
            inverseJoinColumns = @JoinColumn(name = "bid")
    )
    private List<Book> books = new ArrayList<>();

    public Subscription() {}

    public Subscription(Long sid, String type, Integer price, String validity, Long owner) {
        this.sid = sid;
        this.type = type;
        this.price = price;
        this.validity = validity;
        this.owner = owner;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Reader getOwnerOf() {
        return ownerOf;
    }

    public void setOwnerOf(Reader ownerOf) {
        this.ownerOf = ownerOf;
    }

    public Long getSid() {
        return sid;
    }

    public String getType() {
        return type;
    }

    public Integer getPrice() {
        return price;
    }

    public String getValidity() {
        return validity;
    }

    public Long getOwner() {
        return owner;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }
}
