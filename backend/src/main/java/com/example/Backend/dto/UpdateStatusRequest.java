package com.example.Backend.dto;

import com.example.Backend.models.CaseStatus;

public class UpdateStatusRequest {
    private CaseStatus status;

    public CaseStatus getStatus() {
        return status;
    }
    public void setStatus(CaseStatus status){
        this.status = status;
    }
}
