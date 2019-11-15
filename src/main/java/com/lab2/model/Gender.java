package com.lab2.model;

public enum Gender {
    Male("Male"),
    Female("Female");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
