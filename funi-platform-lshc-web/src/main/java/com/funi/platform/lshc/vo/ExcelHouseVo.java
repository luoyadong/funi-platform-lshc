package com.funi.platform.lshc.vo;

import org.jeecgframework.poi.excel.annotation.Excel;

public class ExcelHouseVo {
    @Excel(name="房屋户号")
    private String houseNoSys;

    @Excel(name="小区名称")
    private String livingAreaName="";
    @Excel(name="区域")
    private String regionName="";
    @Excel(name="房号")
    private String addressRoomNo="";
    @Excel(name="房屋地址")
    private String houseAddress="";
    @Excel(name="套型")
    private String houseRomm="";
    @Excel(name="管理机构")
    private String manageOrg="";
    @Excel(name="物业类型")
    private String hourseUseType="";
    @Excel(name="房屋类型")
    private String manageType="";
    @Excel(name="建造面积")
    private String bulidArea="";
    @Excel(name="计租面积")
    private String rentArea="";
    @Excel(name="评估时间")
    private String evaluatePrice="";
    @Excel(name="评估时间")
    private String evaluateDate="";

    @Excel(name="是否空置")
    private String isVacant="";

    @Excel(name="单位名称")
    private String organizationName="";
    @Excel(name="承租人姓名")
    private String renter="";
    @Excel(name="承租人联系电话")
    private String sellerPhone="";
    @Excel(name="栋")
    private String addressBuilding;
    @Excel(name="单元")
    private String addressUnit;
    @Excel(name="楼层")
    private String addressFloor;

    public String getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public String getIsVacant() {
        return isVacant;
    }

    public void setIsVacant(String isVacant) {
        this.isVacant = isVacant;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getHouseNoSys() {
        return houseNoSys;
    }

    public void setHouseNoSys(String houseNoSys) {
        this.houseNoSys = houseNoSys;
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

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseRomm() {
        return houseRomm;
    }

    public void setHouseRomm(String houseRomm) {
        this.houseRomm = houseRomm;
    }

    public String getManageOrg() {
        return manageOrg;
    }

    public void setManageOrg(String manageOrg) {
        this.manageOrg = manageOrg;
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

    public String getBulidArea() {
        return bulidArea;
    }

    public void setBulidArea(String bulidArea) {
        this.bulidArea = bulidArea;
    }

    public String getRentArea() {
        return rentArea;
    }

    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }

    public String getEvaluatePrice() {
        return evaluatePrice;
    }

    public void setEvaluatePrice(String evaluatePrice) {
        this.evaluatePrice = evaluatePrice;
    }




}
