package com.funi.platform.lshc.dto;

import java.math.BigDecimal;

/**
 * 房屋dto
 * @author 3
 */
public class HouseDto {

    //id
    private String houseId;
    //房屋户号
    private String houseNo;
    //区域
    private String regionName;
    //小区名称
    private String village;
    //房号
    private String houseCode;
    //房屋地址
    private String address;
    //建筑面积
    private BigDecimal houseArea;
    //计租面积
    private BigDecimal rentArea;
    //户型
    private String huxing;
    //管理机构
    private String manageOrganization;
    //房屋类型
    private String manageType;
    //物业类型
    private String houseUseType;
    //栋
    private String addressBuilding;
    //单元
    private String addressUnit;
    //楼层
    private String addressFloor;
    //房号
    private String addressRoomNo;
    //建筑结构
    private String buildStructure;
    private BigDecimal evaluatePrice;

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

    public String getAddressBuilding() {
        return addressBuilding;
    }

    public void setAddressBuilding(String addressBuilding) {
        this.addressBuilding = addressBuilding;
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

    public String getAddressUnit() {
        return addressUnit;
    }

    public void setAddressUnit(String addressUnit) {
        this.addressUnit = addressUnit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(BigDecimal houseArea) {
        this.houseArea = houseArea;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getHouseUseType() {
        return houseUseType;
    }

    public void setHouseUseType(String houseUseType) {
        this.houseUseType = houseUseType;
    }

    public String getHuxing() {
        return huxing;
    }

    public void setHuxing(String huxing) {
        this.huxing = huxing;
    }

    public String getManageOrganization() {
        return manageOrganization;
    }

    public void setManageOrganization(String manageOrganization) {
        this.manageOrganization = manageOrganization;
    }

    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public BigDecimal getRentArea() {
        return rentArea;
    }

    public void setRentArea(BigDecimal rentArea) {
        this.rentArea = rentArea;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }


}
