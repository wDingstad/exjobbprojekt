package com.example.Backend.dto;

public class CaseDTO {

    private Integer id;
    private String case_name;
    private String info;
    private Integer customerId;
    private String status;


    public CaseDTO(Integer id, String case_name, String info, Integer customerId, String status){

        this.id = id;
        this.case_name = case_name;
        this.info = info;
        this.customerId = customerId;
        this.status = status;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo(){
        return this.info;
    }
    public void setInfo(String info){
        this.info = info;
    }

    public String getCase_name() {
        return case_name;
    }
    public void setCase_name(String case_name){
        this.case_name = case_name;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public Integer getCustomerId(){
        return customerId;
    }
    public void setCustomerId(Integer customerId){
        this.customerId = customerId;
    }
}
