package com.example.Backend.models;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private Integer article_number;
    private String name;
    private String description;
    private BigDecimal price;
    private String supplier;


    public Product(){

    }
    public Product(Integer article_number, String name, String description, BigDecimal price, String supplier){
        this.article_number = article_number;
        this.name = name;
        this.description = description;
        this.price = price;
        this.supplier = supplier;

    }


    public Integer getId(){
        return id;
    }

    public Integer getArticle_number(){
        return article_number;
    }
    public void setArticle_number(Integer article_number){
        this.article_number = article_number;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public BigDecimal getPrice(){
        return price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public String getSupplier(){
        return supplier;
    }
    public void setSupplier(String supplier){
        this.supplier = supplier;
    }





}
