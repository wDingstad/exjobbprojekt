package com.example.Backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cases")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String case_name;
    private String info;
    private LocalDateTime created_at;
    @Enumerated(EnumType.STRING) // sparar "OPEN", "BLOCKED" osv. i databasen istället för 0, 1, 2...
    private CaseStatus status;
    @Column(name = "customer_id")
    private Integer customerId;


    @OneToMany(mappedBy = "caseEntity", cascade = CascadeType.ALL)
    private List<CaseProduct> products = new ArrayList<>();

    public Case(){

    }

    public Case(String case_name, String info, Integer customer_id){
        this.case_name = case_name;
        this.created_at = LocalDateTime.now();
        this.info = info;
        this.customerId = customer_id;
        this.status = CaseStatus.OPEN;

    }



    public Integer getId(){
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
    public Integer getCustomerId(){
        return customerId;
    }
    public void setCustomerId(Integer customerId){
        this.customerId = customerId;
    }
    public LocalDateTime getCreated_at(){
        return created_at;
    }
    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
    public CaseStatus getStatus() {
        return status; }
    public void setStatus(CaseStatus status) {
        this.status = status; }

    public List<CaseProduct> getProducts(){
        return products;
    }
    public void setProducts(List<CaseProduct> products){
        this.products = products;
    }

}
