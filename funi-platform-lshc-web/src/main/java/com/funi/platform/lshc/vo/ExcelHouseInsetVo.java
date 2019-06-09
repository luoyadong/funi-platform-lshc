package com.funi.platform.lshc.vo;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.math.BigDecimal;

public class ExcelHouseInsetVo {
    @Excel(name="户号*")
    private String houseNo="";
    @Excel(name="小区名称*")
    private String livingAreaName="";
    @Excel(name="房号*")
    private String addressRoomNo="";
    @Excel(name="室**")
    private BigDecimal bedroomQuantity;
    @Excel(name="厅*")
    private BigDecimal livingroomQuantity;
    @Excel(name="卫*")
    private BigDecimal bathroomQuantity;
    @Excel(name="物业类型")
    private String hourseUseType="";

    @Excel(name="房屋类型*")
    private String manageType;
    @Excel(name="建筑面积(㎡)*")
    private BigDecimal bulidArea;
    @Excel(name="计租面积(㎡)*")
    private BigDecimal rentArea;
    @Excel(name="评估单价(元/套/月)")
    private BigDecimal evaluatePrice;
    @Excel(name="建筑结构")
    private String buildStructure;
    @Excel(name="安全等级")
    private String safetyLevel;

    @Excel(name="栋*")
    private String addressBuilding;
    @Excel(name="单元*")
    private String addressUnit;
    @Excel(name="楼层*")
    private String addressFloor;
//    @Excel(name="房屋状态*")
//    private String houseStatus="";

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    public String getLivingAreaName() {
        return livingAreaName;
    }

    public void setLivingAreaName(String livingAreaName) {
        this.livingAreaName = livingAreaName;
    }


    public String getAddressRoomNo() {
        return addressRoomNo;
    }

    public void setAddressRoomNo(String addressRoomNo) {
        this.addressRoomNo = addressRoomNo;
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

    public String getHourseUseType() {
        return hourseUseType;
    }

    public void setHourseUseType(String hourseUseType) {
        this.hourseUseType = hourseUseType;
    }


    public BigDecimal getBulidArea() {
        return bulidArea;
    }

    public void setBulidArea(BigDecimal bulidArea) {
        this.bulidArea = bulidArea;
    }

    public BigDecimal getRentArea() {
        return rentArea;
    }

    public void setRentArea(BigDecimal rentArea) {
        this.rentArea = rentArea;
    }

    public BigDecimal getEvaluatePrice() {
        return evaluatePrice;
    }

    public void setEvaluatePrice(BigDecimal evaluatePrice) {
        this.evaluatePrice = evaluatePrice;
    }

    public String getBuildStructure() {
        return buildStructure;
    }

    public void setBuildStructure(String buildStructure) {
        this.buildStructure = buildStructure;
    }

    public String getSafetyLevel() {
        return safetyLevel;
    }

    public void setSafetyLevel(String safetyLevel) {
        this.safetyLevel = safetyLevel;
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
}
