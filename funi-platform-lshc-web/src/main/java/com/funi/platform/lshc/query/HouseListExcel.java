package com.funi.platform.lshc.query;

import com.funi.framework.biz.eic.bo.Dict;

import java.util.List;

public class HouseListExcel {

    private String hourseUseType;

    private String safetyLevel;

    private String manageType;

    private String regionCode;

    private String hasProperty;

    private String isVacant;

    private String searchContent;

    private String manaageOrg;

    private String houseStatus;

    private List<Dict> authorityList;
    private String userId;


    public List<Dict> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<Dict> authorityList) {
        this.authorityList = authorityList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getHasProperty() {
        return hasProperty;
    }

    public void setHasProperty(String hasProperty) {
        this.hasProperty = hasProperty;
    }

    public String getIsVacant() {
        return isVacant;
    }

    public void setIsVacant(String isVacant) {
        this.isVacant = isVacant;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
}
