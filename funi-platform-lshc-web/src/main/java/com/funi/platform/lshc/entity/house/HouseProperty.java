package com.funi.platform.lshc.entity.house;

import com.funi.platform.lshc.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * GHOUSE_HOUSE_PROPERTY
 * @author 
 */
public class HouseProperty extends BaseEntity {
    private String houseId;

    private String propertyNo;

    private String propertyCertificateNo;

    private String propertyUserName;

    private String propertyUserIdNumber;

    private String propertyUserTel;

    private String commonFlag;

    private String commonUserName;

    private String commonUserIdNumber;

    private String commonUserTel;

    private BigDecimal privateBulidArea;

    private BigDecimal publicBulidArea;

    private String landCertificateNo;

    private BigDecimal landUseYearLimit;

    private String landUseStartDate;

    private String landUseEndDate;

    private String landUseType;

    private BigDecimal landUseArea;

    private String ownershipRemark;

    private static final long serialVersionUID = 1L;
    public boolean isHasIsVache(){
        if(!"".equals(landCertificateNo))
            return true;
        if(!"".equals(landUseYearLimit))
            return true;
        if(!"".equals(landUseStartDate))
            return true;
        if(!"".equals(landUseEndDate))
            return true;
        if(!"".equals(landUseType))
            return true;
        if(!"".equals(landUseArea))
            return true;
        return false;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getPropertyNo() {
        return propertyNo;
    }

    public void setPropertyNo(String propertyNo) {
        this.propertyNo = propertyNo;
    }

    public String getPropertyCertificateNo() {
        return propertyCertificateNo;
    }

    public void setPropertyCertificateNo(String propertyCertificateNo) {
        this.propertyCertificateNo = propertyCertificateNo;
    }

    public String getPropertyUserName() {
        return propertyUserName;
    }

    public void setPropertyUserName(String propertyUserName) {
        this.propertyUserName = propertyUserName;
    }

    public String getPropertyUserIdNumber() {
        return propertyUserIdNumber;
    }

    public void setPropertyUserIdNumber(String propertyUserIdNumber) {
        this.propertyUserIdNumber = propertyUserIdNumber;
    }

    public String getPropertyUserTel() {
        return propertyUserTel;
    }

    public void setPropertyUserTel(String propertyUserTel) {
        this.propertyUserTel = propertyUserTel;
    }

    public String getCommonFlag() {
        return commonFlag;
    }

    public void setCommonFlag(String commonFlag) {
        this.commonFlag = commonFlag;
    }

    public String getCommonUserName() {
        return commonUserName;
    }

    public void setCommonUserName(String commonUserName) {
        this.commonUserName = commonUserName;
    }

    public String getCommonUserIdNumber() {
        return commonUserIdNumber;
    }

    public void setCommonUserIdNumber(String commonUserIdNumber) {
        this.commonUserIdNumber = commonUserIdNumber;
    }

    public String getCommonUserTel() {
        return commonUserTel;
    }

    public void setCommonUserTel(String commonUserTel) {
        this.commonUserTel = commonUserTel;
    }

    public BigDecimal getPrivateBulidArea() {
        return privateBulidArea;
    }

    public void setPrivateBulidArea(BigDecimal privateBulidArea) {
        this.privateBulidArea = privateBulidArea;
    }

    public BigDecimal getPublicBulidArea() {
        return publicBulidArea;
    }

    public void setPublicBulidArea(BigDecimal publicBulidArea) {
        this.publicBulidArea = publicBulidArea;
    }

    public String getLandCertificateNo() {
        return landCertificateNo;
    }

    public void setLandCertificateNo(String landCertificateNo) {
        this.landCertificateNo = landCertificateNo;
    }

    public BigDecimal getLandUseYearLimit() {
        return landUseYearLimit;
    }

    public void setLandUseYearLimit(BigDecimal landUseYearLimit) {
        this.landUseYearLimit = landUseYearLimit;
    }

    public String getLandUseStartDate() {
        return landUseStartDate;
    }

    public void setLandUseStartDate(String landUseStartDate) {
        this.landUseStartDate = landUseStartDate;
    }

    public String getLandUseEndDate() {
        return landUseEndDate;
    }

    public void setLandUseEndDate(String landUseEndDate) {
        this.landUseEndDate = landUseEndDate;
    }

    public String getLandUseType() {
        return landUseType;
    }

    public void setLandUseType(String landUseType) {
        this.landUseType = landUseType;
    }

    public BigDecimal getLandUseArea() {
        return landUseArea;
    }

    public void setLandUseArea(BigDecimal landUseArea) {
        this.landUseArea = landUseArea;
    }

    public String getOwnershipRemark() {
        return ownershipRemark;
    }

    public void setOwnershipRemark(String ownershipRemark) {
        this.ownershipRemark = ownershipRemark;
    }

}