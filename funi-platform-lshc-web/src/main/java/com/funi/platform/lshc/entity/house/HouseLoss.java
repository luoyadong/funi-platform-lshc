package com.funi.platform.lshc.entity.house;

import com.funi.platform.lshc.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * GHOUSE_HOUSE_LOSS
 * @author 
 */
public class HouseLoss extends BaseEntity {
    private String houseId;

    private String lossStatus;

    private String cancelReason;

    private String cancelUserId;

    private BigDecimal finishAmount;

    private BigDecimal sellingPrice;

    private String payDate;

    private String removalPermitNo;

    private String removalProtocolNo;

    private String removalDate;

    private String removalProject;

    private String removalUnit;

    private BigDecimal demolishArea;

    private String lossRemark;

    private static final long serialVersionUID = 1L;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getLossStatus() {
        return lossStatus;
    }

    public void setLossStatus(String lossStatus) {
        this.lossStatus = lossStatus;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCancelUserId() {
        return cancelUserId;
    }

    public void setCancelUserId(String cancelUserId) {
        this.cancelUserId = cancelUserId;
    }

    public BigDecimal getFinishAmount() {
        return finishAmount;
    }

    public void setFinishAmount(BigDecimal finishAmount) {
        this.finishAmount = finishAmount;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getRemovalPermitNo() {
        return removalPermitNo;
    }

    public void setRemovalPermitNo(String removalPermitNo) {
        this.removalPermitNo = removalPermitNo;
    }

    public String getRemovalProtocolNo() {
        return removalProtocolNo;
    }

    public void setRemovalProtocolNo(String removalProtocolNo) {
        this.removalProtocolNo = removalProtocolNo;
    }

    public String getRemovalDate() {
        return removalDate;
    }

    public void setRemovalDate(String removalDate) {
        this.removalDate = removalDate;
    }

    public String getRemovalProject() {
        return removalProject;
    }

    public void setRemovalProject(String removalProject) {
        this.removalProject = removalProject;
    }

    public String getRemovalUnit() {
        return removalUnit;
    }

    public void setRemovalUnit(String removalUnit) {
        this.removalUnit = removalUnit;
    }

    public BigDecimal getDemolishArea() {
        return demolishArea;
    }

    public void setDemolishArea(BigDecimal demolishArea) {
        this.demolishArea = demolishArea;
    }

    public String getLossRemark() {
        return lossRemark;
    }

    public void setLossRemark(String lossRemark) {
        this.lossRemark = lossRemark;
    }

}