package com.funi.platform.lshc.vo;

/**
 * 房屋信息，用于地图模块
 */
public class MapHouseVo{
    /**
     * 房屋系统Id
     */
    private String houseId;
    /**
     * 房屋户号
     */
    private String houseNo;
    private String createUnitId;
    private String createUnitName;
    private String hourseUseType;
    private String manageType;
    private String bulidArea;
    private String rentArea;
    private String rentMan;
    private String telNum;
    private String safetyLevel;

    public String getCreateUnitId() {
        return createUnitId;
    }

    public void setCreateUnitId(String createUnitId) {
        this.createUnitId = createUnitId;
    }

    public String getCreateUnitName() {
        return createUnitName;
    }

    public void setCreateUnitName(String createUnitName) {
        this.createUnitName = createUnitName;
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

    public String getRentMan() {
        return rentMan;
    }

    public void setRentMan(String rentMan) {
        this.rentMan = rentMan;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getSafetyLevel() {
        return safetyLevel;
    }

    public void setSafetyLevel(String safetyLevel) {
        this.safetyLevel = safetyLevel;
    }
}
