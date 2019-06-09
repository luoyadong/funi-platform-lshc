package com.funi.platform.lshc.dto;

import java.math.BigDecimal;

/**
 * @author
 */
public class ProtocolDto {

    private String contractNo;
    //保证金
    private BigDecimal depositAmount;
    //栋
    private String addressBuilding;
    //单元
    private String addressUnit;
    //楼层
    private String addressFloor;
    //房号
    private String addressRoomNo;
    //房屋类型
    private String manageType;
    //租赁开始时间
    private String rentStartDate;
    //租赁结束时间
    private String rentEndDate;
    //承租人
    private String renter;
    //建筑面积
    private BigDecimal houseArea;
    //计租面积
    private BigDecimal rentArea;
    //地址
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public BigDecimal getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(BigDecimal houseArea) {
        this.houseArea = houseArea;
    }

    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    public BigDecimal getRentArea() {
        return rentArea;
    }

    public void setRentArea(BigDecimal rentArea) {
        this.rentArea = rentArea;
    }

    public String getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(String rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public String getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(String rentStartDate) {
        this.rentStartDate = rentStartDate;
    }
}
