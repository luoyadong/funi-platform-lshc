package com.funi.platform.lshc.query;

import com.funi.framework.utils.StringUtils;

/**
 * @author 3
 */
public class HouseQuery extends GhouseBaseQuery {

    //关键字
    private String keyword;
    //区域
    private String regionCode;
    //管理机构id
    private String manageOrganizationId;
    //小区id
    private String villageId;
    //户号
    private String houseNo;

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = StringUtils.hasText(houseNo)?houseNo:null;
    }

    @Override
    public String getKeyword() {
        return keyword;
    }

    @Override
    public void setKeyword(String keyword) {
        this.keyword = StringUtils.hasText(keyword)?keyword:null;
    }

    public String getManageOrganizationId() {
        return manageOrganizationId;
    }

    public void setManageOrganizationId(String manageOrganizationId) {
        this.manageOrganizationId = StringUtils.hasText(manageOrganizationId)?manageOrganizationId:null;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = StringUtils.hasText(regionCode)?regionCode:null;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = StringUtils.hasText(villageId)?villageId:null;
    }
}
