package com.example.Backend.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")  // om din tabell heter customers
public class Customer {
    @Id
    private Long id;
    private String customer_name;
    private String organisation_number;
    private String customer_number;
    private String customer_email;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCustomer_name(){
        return customer_name;
    }
    public String getOrganisation_number(){
        return organisation_number;
    }
    public String getCustomer_number(){
        return customer_number;
    }
    public String getCustomer_email(){
        return customer_email;
    }



}
