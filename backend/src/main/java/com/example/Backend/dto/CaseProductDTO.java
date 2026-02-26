package com.example.Backend.dto;

public class CaseProductDTO {

    private Integer articleNumber;
    private String name;
    private Integer quantity;

    public CaseProductDTO(Integer articleNumber, String name, Integer quantity) {
        this.articleNumber = articleNumber;
        this.name = name;
        this.quantity = quantity;
    }

    public Integer getArticleNumber() { return articleNumber; }
    public String getName() { return name; }
    public Integer getQuantity() { return quantity; }
}
