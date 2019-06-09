package com.funi.platform.lshc.query;


public class HouseListQuery extends GhouseBaseQuery {

    private String hourseUseType;

    private String safetyLevel;

    private String manageType;

    private String regionCode;

    private String hasProperty;

    private String manaageOrg;

    private String houseStatus;



    private String isVacant;

    private String searchContent;

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public String getManaageOrg() {
        return manaageOrg;
    }

    public void setManaageOrg(String manaageOrg) {
        this.manaageOrg = manaageOrg;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public String getIsVacant() {
        return isVacant;
    }

    public void setIsVacant(String isVacant) {
        this.isVacant = isVacant;
    }

    public String getHasProperty() {
        return hasProperty;
    }

    public void setHasProperty(String hasProperty) {
        this.hasProperty = hasProperty;
    }

    public String getHourseUseType() {
        return hourseUseType;
    }

    public void setHourseUseType(String hourseUseType) {
        this.hourseUseType = hourseUseType;
    }

    public String getSafetyLevel() {
        return safetyLevel;
    }

    public void setSafetyLevel(String safetyLevel) {
        this.safetyLevel = safetyLevel;
    }

    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
}
