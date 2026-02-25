package com.example.Backend.models;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private Integer articleNumber;
    private String name;
    private String description;
    private BigDecimal price;
    private String supplier;


    public Product(){

    }
    public Product(Integer articleNumber, String name, String description, BigDecimal price, String supplier){
        this.articleNumber = articleNumber;
        this.name = name;
        this.description = description;
        this.price = price;
        this.supplier = supplier;

    }


    public Integer getId(){
        return id;
    }

    public Integer getArticleNumber(){
        return articleNumber;
    }
    public void setArticleNumber(Integer articleNumber){
        this.articleNumber = articleNumber;
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
