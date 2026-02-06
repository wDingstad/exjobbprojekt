package com.example.Backend.models;


import jakarta.persistence.*;

@Entity
@Table(name = "customers")  // om din tabell heter customers
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customer_name;
    private String organisation_number;
    private String customer_number;
    private String customer_email;

    public Customer(){

    }
    public Customer(String customer_email, String customer_name, String organisation_number, String customer_number){
        this.customer_email = customer_email;
        this.customer_name = customer_name;
        this.customer_number = customer_number;
        this.organisation_number = organisation_number;
    }




    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCustomer_name(){
        return customer_name;
    }
    public void setCustomer_name(String customer_name){
        this.customer_name = customer_name;

    }

    public String getOrganisation_number(){
        return organisation_number;
    }
    public void setOrganisation_number(String organisation_number){
        this.organisation_number = organisation_number;
    }
    public String getCustomer_number(){
        return customer_number;
    }
    public void setCustomer_number(String customer_number){
        this.customer_number = customer_number;
    }
    public String getCustomer_email(){
        return customer_email;
    }
    public void setCustomer_email(String customer_email){
        this.customer_email = customer_email;
    }



}
