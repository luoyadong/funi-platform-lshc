package com.funi.platform.lshc.dto;

import java.math.BigDecimal;

/**
 * 收费数据传输对象
 * Created by sam on 2018/11/15.9:00 PM
 */
public class ChargeDto {
    /**
     * 合同ID
     */
    private String contractId;

    /**
     * 租金交至月份
     */
    private String chargeEndDate;

    /**
     * 实收金额
     */
    private BigDecimal actualAmount;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getChargeEndDate() {
        return chargeEndDate;
    }

    public void setChargeEndDate(String chargeEndDate) {
        this.chargeEndDate = chargeEndDate;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }
}
