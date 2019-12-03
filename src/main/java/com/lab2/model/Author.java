package com.lab2.model;

import com.lab2.annotations.PrimaryKey;
import com.lab2.annotations.TableName;

import java.util.Date;

@TableName(name="author")
public class Author {
    @PrimaryKey
    Long aid;
    String name;
    Date birth_date;
    String nationality;
    boolean isalive;

    public Author() {}

    public Author(Long aid, String name, Date birth_date, String nationality, boolean isALive) {
        this.aid = aid;
        this.name = name;
        this.birth_date = birth_date;
        this.nationality = nationality;
        this.isalive = isALive;
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
