package com.funi.platform.lshc.entity.house;

import com.funi.platform.lshc.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * GHOUSE_HOUSE_INFO
 * @author 
 */
public class HouseInfo extends BaseEntity {
    private String hourseNoPrefix;

    private String houseNoSys;

    private String houseNo;

    private String addressId;

    private String addressBuilding;

    private String addressUnit;

    private String addressFloor;

    private String addressRoomNo;

    private String houseSource;

    private String exchangeHourseNo;

    private String sourceUnit;

    private String transferDate;

    private String transferBatch;

    private BigDecimal bulidArea;

    private BigDecimal stepsArea;

    private BigDecimal rentArea;

    private BigDecimal nonRentArea;

    private BigDecimal innerArea;

    private BigDecimal poolArea;

    private BigDecimal bedroomQuantity;

    private BigDecimal livingroomQuantity;

    private BigDecimal bathroomQuantity;

    private BigDecimal evaluatePrice;

    private String evaluateDate;

    private String buildStructure;

    private String hourseUseType;

    private String manageType;

    private String finshDate;

    private String safetyLevel;

    private String houseStatus;

    private String houseRemark;
    /** 是否有产权，默认为0：没有产权，1：有产权*/
    private String hasProperty;

    private BigDecimal isVacant;

    private String createUnitId;

    private String rentStatus;

    /** 公产建筑面积 PUBLIC_BUILT_AREA*/
    private BigDecimal publicBuiltArea;
    /** 不增间建筑面积 UN_ADD_BUILT_AREA*/
    private BigDecimal unAddBuiltArea;
    /** 增间建筑面积 ADD_BUILT_AREA*/
    private BigDecimal addBuiltArea;

    private String HouseKeeper;

    public String getHouseKeeper() {
        return HouseKeeper;
    }

    public void setHouseKeeper(String houseKeeper) {
        HouseKeeper = houseKeeper;
    }

    public BigDecimal getPublicBuiltArea() {
        return publicBuiltArea;
    }

    public void setPublicBuiltArea(BigDecimal publicBuiltArea) {
        this.publicBuiltArea = publicBuiltArea;
    }

    public BigDecimal getUnAddBuiltArea() {
        return unAddBuiltArea;
    }

    public void setUnAddBuiltArea(BigDecimal unAddBuiltArea) {
        this.unAddBuiltArea = unAddBuiltArea;
    }

    public BigDecimal getAddBuiltArea() {
        return addBuiltArea;
    }

    public void setAddBuiltArea(BigDecimal addBuiltArea) {
        this.addBuiltArea = addBuiltArea;
    }

    public String getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(String rentStatus) {
        this.rentStatus = rentStatus;
    }

    private static final long serialVersionUID = 1L;

    public String getHourseNoPrefix() {
        return hourseNoPrefix;
    }

    public void setHourseNoPrefix(String hourseNoPrefix) {
        this.hourseNoPrefix = hourseNoPrefix;
    }

    public String getHouseNoSys() {
        return houseNoSys;
    }

    public void setHouseNoSys(String houseNoSys) {
        this.houseNoSys = houseNoSys;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressBuilding() {
        return addressBuilding;
    }

    public void setAddressBuilding(String addressBuilding) {
        this.addressBuilding = addressBuilding;
    }

    public String getAddressUnit() {
        return addressUnit;
    }

    public void setAddressUnit(String addressUnit) {
        this.addressUnit = addressUnit;
    }

    public String getAddressFloor() {
        return addressFloor;
    }

    public void setAddressFloor(String addressFloor) {
        this.addressFloor = addressFloor;
    }

    public String getAddressRoomNo() {
        return addressRoomNo;
    }

    public void setAddressRoomNo(String addressRoomNo) {
        this.addressRoomNo = addressRoomNo;
    }

    public String getHouseSource() {
        return houseSource;
    }

    public void setHouseSource(String houseSource) {
        this.houseSource = houseSource;
    }

    public String getExchangeHourseNo() {
        return exchangeHourseNo;
    }

    public void setExchangeHourseNo(String exchangeHourseNo) {
        this.exchangeHourseNo = exchangeHourseNo;
    }

    public String getSourceUnit() {
        return sourceUnit;
    }

    public void setSourceUnit(String sourceUnit) {
        this.sourceUnit = sourceUnit;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getTransferBatch() {
        return transferBatch;
    }

    public void setTransferBatch(String transferBatch) {
        this.transferBatch = transferBatch;
    }

    public BigDecimal getBulidArea() {
        return bulidArea;
    }

    public void setBulidArea(BigDecimal bulidArea) {
        this.bulidArea = bulidArea;
    }

    public BigDecimal getStepsArea() {
        return stepsArea;
    }

    public void setStepsArea(BigDecimal stepsArea) {
        this.stepsArea = stepsArea;
    }

    public BigDecimal getRentArea() {
        return rentArea;
    }

    public void setRentArea(BigDecimal rentArea) {
        this.rentArea = rentArea;
    }

    public BigDecimal getNonRentArea() {
        return nonRentArea;
    }

    public void setNonRentArea(BigDecimal nonRentArea) {
        this.nonRentArea = nonRentArea;
    }

    public BigDecimal getInnerArea() {
        return innerArea;
    }

    public void setInnerArea(BigDecimal innerArea) {
        this.innerArea = innerArea;
    }

    public BigDecimal getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(BigDecimal poolArea) {
        this.poolArea = poolArea;
    }

    public BigDecimal getBedroomQuantity() {
        return bedroomQuantity;
    }

    public void setBedroomQuantity(BigDecimal bedroomQuantity) {
        this.bedroomQuantity = bedroomQuantity;
    }

    public BigDecimal getLivingroomQuantity() {
        return livingroomQuantity;
    }

    public void setLivingroomQuantity(BigDecimal livingroomQuantity) {
        this.livingroomQuantity = livingroomQuantity;
    }

    public BigDecimal getBathroomQuantity() {
        return bathroomQuantity;
    }

    public void setBathroomQuantity(BigDecimal bathroomQuantity) {
        this.bathroomQuantity = bathroomQuantity;
    }

    public BigDecimal getEvaluatePrice() {
        return evaluatePrice;
    }

    public void setEvaluatePrice(BigDecimal evaluatePrice) {
        this.evaluatePrice = evaluatePrice;
    }

    public String getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public String getBuildStructure() {
        return buildStructure;
    }

    public void setBuildStructure(String buildStructure) {
        this.buildStructure = buildStructure;
    }

    public String getHourseUseType() {
        return hourseUseType;
    }

    public void setHourseUseType(String hourseUseType) {
        this.hourseUseType = hourseUseType;
    }

    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    public String getFinshDate() {
        return finshDate;
    }

    public void setFinshDate(String finshDate) {
        this.finshDate = finshDate;
    }

    public String getSafetyLevel() {
        return safetyLevel;
    }

    public void setSafetyLevel(String safetyLevel) {
        this.safetyLevel = safetyLevel;
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public String getHouseRemark() {
        return houseRemark;
    }

    public void setHouseRemark(String houseRemark) {
        this.houseRemark = houseRemark;
    }

    public String getHasProperty() {
        return hasProperty;
    }

    public void setHasProperty(String hasProperty) {
        this.hasProperty = hasProperty;
    }

    public BigDecimal getIsVacant() {
        return isVacant;
    }

    public void setIsVacant(BigDecimal isVacant) {
        this.isVacant = isVacant;
    }

    public String getCreateUnitId() {
        return createUnitId;
    }

    public void setCreateUnitId(String createUnitId) {
        this.createUnitId = createUnitId;
    }

}