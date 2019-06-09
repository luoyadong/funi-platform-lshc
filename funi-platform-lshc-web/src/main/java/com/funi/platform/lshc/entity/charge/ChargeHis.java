package com.funi.platform.lshc.entity.charge;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.entity.BaseEntity;
import com.funi.platform.lshc.utils.DateUtils;
import com.funi.platform.lshc.vo.ChargeDetailVo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 收费记录管理表
 * @author sam
 * @date 2018-11-12 10:12:21
 */
public class ChargeHis extends BaseEntity implements Serializable {
    /** 票据ID */
    private String billId;
    /** 合同ID */
    private String contrId;
    /** 租金开始年月日 */
    private String rentStartDate;
    /** 租金终止年月日 */
    private String rentEndDate;
    /** 租金金额 */
    private BigDecimal rentAmount;
    /** 欠租开始年月日 */
    private String backRentStartDate;
    /** 欠租终止年月日 */
    private String backRentEndDate;
    /** 欠租金额 */
    private BigDecimal backRentAmount;
    /** 滞纳金开始年月日 */
    private String lateFeeStartDate;
    /** 滞纳金终止年月日 */
    private String lateFeeEndDate;
    /** 滞纳金金额 */
    private BigDecimal lateFeeAmount;
    /** 应收金额 */
    private BigDecimal receivableAmount;
    /** 实收金额 */
    private BigDecimal actualAmount;
    /** 预缴金额 */
    private BigDecimal prepaidAmount;
    /** 收款人 */
    private String chargeUserId;
    /** 收费时间 */
    private String chargeTime;
    /** 退费标识,0:已收费，1：已撤销 */
    private String backFlag = "0";
    /** 退费人 */
    private String backUser;
    /** 退费金额 */
    private BigDecimal backAmount;
    /** 退费至日期 */
    private String backToDate;
    /** 退费操作人 */
    private String backOpeUserId;
    /** 退费日期 */
    private String backDate;
    /** 退费原因 */
    private String backReason;
    /** 备注 */
    private String chargeRemark;
    /** 收款单位名称*/
    private String chargeUnit;
    /** 实收金额大写*/
    private String amountCapitalization;
    /** 创建者所属机构ID*/
    private String createUnitId;

    public ChargeHis(ChargeDetailVo chargeDetailVo, Bill bill, CurrentUser userInfo) {
        this.billId = bill.getId();
        this.contrId = chargeDetailVo.getContractId();
        this.rentStartDate = chargeDetailVo.getThisRentDate();
        this.rentEndDate = chargeDetailVo.getChargeEndDate();
        this.rentAmount = chargeDetailVo.getRentAmount();
        this.backRentStartDate = chargeDetailVo.getBackRentStartDate();
        this.backRentEndDate = chargeDetailVo.getBackRentEndDate();
        this.backRentAmount = chargeDetailVo.getBackRentAmount();
        this.lateFeeStartDate = chargeDetailVo.getBackRentStartDate();
        this.lateFeeEndDate = chargeDetailVo.getBackRentEndDate();
        this.lateFeeAmount = chargeDetailVo.getLateFeeAmount();
        this.receivableAmount = chargeDetailVo.getReceivableAmount();
        this.actualAmount = chargeDetailVo.getActualAmount();
        this.prepaidAmount = chargeDetailVo.getPrepaidAmount();
        this.chargeUserId = userInfo.getName();
        this.chargeTime = DateUtils.getNow();
        this.chargeUnit = chargeDetailVo.getUnitName();
        this.amountCapitalization = chargeDetailVo.getActualAmountDesc();
        this.createUnitId = userInfo.getOrganization().getDm();
    }

    public ChargeHis() {
    }

    private static final long serialVersionUID = 1L;


    public String getCreateUnitId() {
        return createUnitId;
    }

    public void setCreateUnitId(String createUnitId) {
        this.createUnitId = createUnitId;
    }

    public String getChargeUnit() {
        return chargeUnit;
    }

    public void setChargeUnit(String chargeUnit) {
        this.chargeUnit = chargeUnit;
    }

    public String getAmountCapitalization() {
        return amountCapitalization;
    }

    public void setAmountCapitalization(String amountCapitalization) {
        this.amountCapitalization = amountCapitalization;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getContrId() {
        return contrId;
    }

    public void setContrId(String contrId) {
        this.contrId = contrId;
    }

    public String getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(String rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public String getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(String rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public BigDecimal getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(BigDecimal rentAmount) {
        this.rentAmount = rentAmount;
    }

    public String getBackRentStartDate() {
        return backRentStartDate;
    }

    public void setBackRentStartDate(String backRentStartDate) {
        this.backRentStartDate = backRentStartDate;
    }

    public String getBackRentEndDate() {
        return backRentEndDate;
    }

    public void setBackRentEndDate(String backRentEndDate) {
        this.backRentEndDate = backRentEndDate;
    }

    public BigDecimal getBackRentAmount() {
        return backRentAmount;
    }

    public void setBackRentAmount(BigDecimal backRentAmount) {
        this.backRentAmount = backRentAmount;
    }

    public String getLateFeeStartDate() {
        return lateFeeStartDate;
    }

    public void setLateFeeStartDate(String lateFeeStartDate) {
        this.lateFeeStartDate = lateFeeStartDate;
    }

    public String getLateFeeEndDate() {
        return lateFeeEndDate;
    }

    public void setLateFeeEndDate(String lateFeeEndDate) {
        this.lateFeeEndDate = lateFeeEndDate;
    }

    public BigDecimal getLateFeeAmount() {
        return lateFeeAmount;
    }

    public void setLateFeeAmount(BigDecimal lateFeeAmount) {
        this.lateFeeAmount = lateFeeAmount;
    }

    public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getPrepaidAmount() {
        return prepaidAmount;
    }

    public void setPrepaidAmount(BigDecimal prepaidAmount) {
        this.prepaidAmount = prepaidAmount;
    }

    public String getChargeUserId() {
        return chargeUserId;
    }

    public void setChargeUserId(String chargeUserId) {
        this.chargeUserId = chargeUserId;
    }

    public String getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String getBackFlag() {
        return backFlag;
    }

    public void setBackFlag(String backFlag) {
        this.backFlag = backFlag;
    }

    public String getBackUser() {
        return backUser;
    }

    public void setBackUser(String backUser) {
        this.backUser = backUser;
    }

    public BigDecimal getBackAmount() {
        return backAmount;
    }

    public void setBackAmount(BigDecimal backAmount) {
        this.backAmount = backAmount;
    }

    public String getBackToDate() {
        return backToDate;
    }

    public void setBackToDate(String backToDate) {
        this.backToDate = backToDate;
    }

    public String getBackOpeUserId() {
        return backOpeUserId;
    }

    public void setBackOpeUserId(String backOpeUserId) {
        this.backOpeUserId = backOpeUserId;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public String getBackReason() {
        return backReason;
    }

    public void setBackReason(String backReason) {
        this.backReason = backReason;
    }

    public String getChargeRemark() {
        return chargeRemark;
    }

    public void setChargeRemark(String chargeRemark) {
        this.chargeRemark = chargeRemark;
    }

}