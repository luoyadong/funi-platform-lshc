package com.funi.platform.lshc.dto;

import com.funi.framework.biz.eic.bo.CurrentUser;

import java.math.BigDecimal;

/**
 * 用于收取保证金
 * Created by sam on 2018/11/18.11:34 AM
 */
public class ChargeDepositDto {
    /** 合同ID*/
    private String contractId;
    /** 保证金实收金额*/
    private BigDecimal depositAmount;
    /** 操作用户*/
    private CurrentUser userInfo;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public CurrentUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(CurrentUser userInfo) {
        this.userInfo = userInfo;
    }
}
