package com.example.Backend.dto;

import java.util.List;

public class CaseDTO {

    private Integer id;
    private String case_name;
    private String info;
    private Integer customerId;
    private String status;
    private List<CaseProductDTO> products; // måste ha getter och setter



    public CaseDTO(Integer id, String case_name, String info, String status, Integer customerId, List<CaseProductDTO> products){

        this.id = id;
        this.case_name = case_name;
        this.info = info;
        this.customerId = customerId;
        this.status = status;
        this.products = products;



    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCase_name() { return case_name; }
    public void setCase_name(String case_name) { this.case_name = case_name; }

    public String getInfo() { return info; }
    public void setInfo(String info) { this.info = info; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public List<CaseProductDTO> getProducts() { return products; }
    public void setProducts(List<CaseProductDTO> products) { this.products = products; }
}
