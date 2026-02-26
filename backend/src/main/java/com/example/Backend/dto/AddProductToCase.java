package com.example.Backend.dto;

public class AddProductToCase {

    private Integer articleNumber;
    private Integer quantity;


    private AddProductToCase(){

    }

    public Integer getArticleNumber(){
        return articleNumber;
    }

    public void setProductId(Integer articleNumber) {
        this.articleNumber = articleNumber;
    }

    public Integer getQuantity(){
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
