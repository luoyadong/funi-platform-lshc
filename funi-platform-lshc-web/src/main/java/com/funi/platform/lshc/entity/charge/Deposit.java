package com.funi.platform.lshc.entity.charge;

import com.funi.platform.lshc.dto.ChargeDepositDto;
import com.funi.platform.lshc.entity.BaseEntity;
import com.funi.platform.lshc.entity.contr.Contr;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 保证金管理表
 * @author sam
 * @date 2018-11-06 00:55:50
 */
public class Deposit extends BaseEntity implements Serializable {
    /** 合同编号 */
    private String contrId;

    /** 保证金实收金额*/
    private BigDecimal depositAmount;
    /** 收费人 */
    private String chargeUserId;

    /** 收费时间 */
    private Date chargeTime;

    /** 退费人 */
    private String refundUserId;

    /** 退费时间 */
    private Date refundTime;

    /** 退费原因 */
    private String refundReason;

    public Deposit() {
    }

    public Deposit(Contr contr, ChargeDepositDto chargeDepositDto) {
        this.contrId = contr.getId();
        // 设置实收保证金金额
        this.depositAmount = chargeDepositDto.getDepositAmount();
        this.chargeUserId = chargeDepositDto.getUserInfo().getUsername();
        this.chargeTime = new Date();
    }

    private static final long serialVersionUID = 1L;

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getContrId() {
        return contrId;
    }

    public void setContrId(String contrId) {
        this.contrId = contrId;
    }

    public String getChargeUserId() {
        return chargeUserId;
    }

    public void setChargeUserId(String chargeUserId) {
        this.chargeUserId = chargeUserId;
    }

    public Date getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(Date chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String getRefundUserId() {
        return refundUserId;
    }

    public void setRefundUserId(String refundUserId) {
        this.refundUserId = refundUserId;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }
}