package com.example.Backend.models;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

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
    private int customer_id;
    private LocalDateTime created_at;
    private String status;


    public Case(){

    }

    public Case(String case_name, String info, int customer_id, LocalDateTime created_at, String status){
        this.case_name = case_name;
        this.created_at = created_at;
        this.info = info;
        this.customer_id = customer_id;
        this.status = status;


    }



    public int getId(){
        return id;
    }

    public String getCase_name() {
        return case_name;
    }
    public void setCase_name(String case_name){
        this.case_name = case_name;
    }
    public String getInfo(){
        return info;
    }
    public void setInfo(String info){
        this.info = info;
    }
    public int getCustomer_id(){
        return customer_id;
    }
    public void setCustomer_id(int customer_id){
        this.customer_id = customer_id;
    }
    public LocalDateTime getCreated_at(){
        return created_at;
    }
    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
    public void setStatus(String status){
        this.status = status;
    }

}
