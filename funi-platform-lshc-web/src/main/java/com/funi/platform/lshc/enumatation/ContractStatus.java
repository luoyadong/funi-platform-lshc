package com.funi.platform.lshc.enumatation;

/**
 * 合同状态
 * @author 3
 */
public enum ContractStatus {

    NEW("新建"),
    DCS("待初审"),
    CSTG("初审通过"),
    CSBTG("初审不通过"),
    FSTG("复审通过"),
    FSBTG("复审不通过"),
    TH("退回");

    private String description;

    ContractStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
