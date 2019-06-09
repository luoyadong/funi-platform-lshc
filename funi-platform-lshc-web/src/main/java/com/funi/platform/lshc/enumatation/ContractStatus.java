package com.funi.platform.lshc.enumatation;

/**
 * 合同状态
 * @author 3
 */
public enum ContractStatus {

    VALID("有效"),
    EXAMINING("审批中"),
    INVALID("未生效"),
    CANCEL("已注销"),
    OVERDUE("已过期");

    private String description;

    ContractStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
