package com.funi.platform.lshc.entity.repair;

import com.funi.platform.lshc.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * GHOUSE_REPAIR
 * @author 
 */
public class Repair extends BaseEntity {
    private String repairNo;

    private String houseId;

    private String repairContent;

    private String requestUser;

    private String requestUserTel;

    private String requestTime;

    private String repairAddFlag;

    private String repairCompleteTime;

    private String repairUnitUser;

    private BigDecimal repairMoney;

    private String repairCompleteComment;

    private BigDecimal repairQuaDur;

    private String repairStatus;

    /** 创建者所属机构ID*/
    private String createUnitId;

    public String getCreateUnitId() {
        return createUnitId;
    }

    public void setCreateUnitId(String createUnitId) {
        this.createUnitId = createUnitId;
    }

    private static final long serialVersionUID = 1L;

    public String getRepairNo() {
        return repairNo;
    }

    public void setRepairNo(String repairNo) {
        this.repairNo = repairNo;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getRepairContent() {
        return repairContent;
    }

    public void setRepairContent(String repairContent) {
        this.repairContent = repairContent;
    }

    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
    }

    public String getRequestUserTel() {
        return requestUserTel;
    }

    public void setRequestUserTel(String requestUserTel) {
        this.requestUserTel = requestUserTel;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRepairAddFlag() {
        return repairAddFlag;
    }

    public void setRepairAddFlag(String repairAddFlag) {
        this.repairAddFlag = repairAddFlag;
    }

    public String getRepairCompleteTime() {
        return repairCompleteTime;
    }

    public void setRepairCompleteTime(String repairCompleteTime) {
        this.repairCompleteTime = repairCompleteTime;
    }

    public String getRepairUnitUser() {
        return repairUnitUser;
    }

    public void setRepairUnitUser(String repairUnitUser) {
        this.repairUnitUser = repairUnitUser;
    }

    public BigDecimal getRepairMoney() {
        return repairMoney;
    }

    public void setRepairMoney(BigDecimal repairMoney) {
        this.repairMoney = repairMoney;
    }

    public String getRepairCompleteComment() {
        return repairCompleteComment;
    }

    public void setRepairCompleteComment(String repairCompleteComment) {
        this.repairCompleteComment = repairCompleteComment;
    }

    public BigDecimal getRepairQuaDur() {
        return repairQuaDur;
    }

    public void setRepairQuaDur(BigDecimal repairQuaDur) {
        this.repairQuaDur = repairQuaDur;
    }

    public String getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }

}