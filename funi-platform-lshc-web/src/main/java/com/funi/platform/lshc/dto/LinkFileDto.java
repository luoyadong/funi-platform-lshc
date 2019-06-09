package com.funi.platform.lshc.dto;

/**
 * @author 3
 */
public class LinkFileDto {

    private String lesseeId;
    private String contractId;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getLesseeId() {
        return lesseeId;
    }

    public void setLesseeId(String lesseeId) {
        this.lesseeId = lesseeId;
    }
}
