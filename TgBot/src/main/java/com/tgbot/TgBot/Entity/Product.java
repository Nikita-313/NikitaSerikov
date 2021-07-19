package com.tgbot.TgBot.Entity;


import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id; // уникальный идентификатор

    @Column(nullable = false, length = 50,unique = true)
    private String name; // название

    @Column(nullable = false, length = 400)
    private String description; // описание

    @Column(nullable = false, length = 15,precision = 2)
    private Double price; // стоимость

    @ManyToOne
    private Category category; //идентификатор категории

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }
}
