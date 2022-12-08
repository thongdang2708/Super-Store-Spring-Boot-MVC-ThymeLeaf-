package com.example.Superstore;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.Superstore.validation.GetPast;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Item {
    @NotBlank(message = "Please select category")
    private String category;
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;
    private double price;
    private double discount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @GetPast(message = "Date must be in the past or at least today")
    private Date date;
    private String id;

    public Item(String category, String name, double price, double discount, Date date, String id) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.date = date;
        this.id = id;
    }

    public Item() {
        this.id = UUID.randomUUID().toString();
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
