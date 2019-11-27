package com.lab2.model;

import com.lab2.annotations.DiscriminationColumn;
import com.lab2.annotations.PrimaryKey;

@DiscriminationColumn(name = "dtype")
public abstract class Human {

    @PrimaryKey
    Long id;
    String name;
    Integer age;
    Gender gender;

    public Human() {}

    public Human(Long id, String name, Integer age, Gender gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
