package com.lab2.model;

import com.lab2.annotations.PrimaryKey;
import com.lab2.annotations.TableName;

@TableName(name="subscription")
public class Subscription {
    @PrimaryKey
    Long sid;
    String type;
    Integer price;
    String validity;
    Long owner;

    public Subscription() {}

    public Subscription(Long sid, String type, Integer price, String validity, Long owner) {
        this.sid = sid;
        this.type = type;
        this.price = price;
        this.validity = validity;
        this.owner = owner;
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
