package com.example.Backend.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;


enum CaseStatus {
    NEW,
    BLOCKED,
    FINISHED,
    CLOSED,
    INPROGRESS,
    CANCELLED
}


@Entity
@Table(name = "cases")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String case_name;
    private String info;
    //private int customer_id;
    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn (name ="customer_id", nullable = false)

    private Customer customer;


    public Case(){

    }

    public Case(String case_name, String info, int customer_id, LocalDateTime created_at){
        this.case_name = case_name;
        this.created_at = created_at;
        this.info = info;


    }



    public int getId(){
        return id;
    }

    public String getCase_name() {
        return case_name;
    }
    public void setCase_name(){

    }
    public String getInfo(){
        return info;
    }
    public int getCustomer_id(){
        return customer_id;
    }
    public LocalDateTime getCreated_at(){
        return created_at;
    }
}
