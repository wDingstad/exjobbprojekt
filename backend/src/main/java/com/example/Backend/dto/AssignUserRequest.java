package com.example.Backend.dto;

public class AssignUserRequest {

    private Integer userId;

    public AssignUserRequest(){

    }

    public Integer getUserId(){
        return userId;
    }
    public void setUserID(Integer userId){
        this.userId = userId;
    }
}
