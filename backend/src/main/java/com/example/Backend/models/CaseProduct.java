package com.example.Backend.models;

import jakarta.persistence.*;


@Entity
@Table(name = "case_product")
public class CaseProduct {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "case_id" , nullable = false)
    private Case caseObject;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    //undra om den ska va float? typ för volym eller något typ distans
    @Column(nullable = false)
    private Integer quantity;

    public CaseProduct(){

    }

    public CaseProduct(Case caseObject, Product product, Integer quantity){

        this.caseObject=caseObject;
        this.product = product;
        this.quantity = quantity;
    }


    public Integer getId(){
        return id;
    }

    public Case getCaseObject(){
        return caseObject;
    }

    public void setCaseObject(Case caseObject) {
        this.caseObject = caseObject;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product){
        this.product = product;
    }

    public Integer getQuantity(){
        return quantity;
    }

    public void setQuantity(Integer quantity){
        this.quantity=quantity;
    }
}


