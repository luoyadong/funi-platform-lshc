package com.funi.platform.lshc.vo;

import com.funi.platform.lshc.entity.house.HouseInfo;

public class HouseVo extends HouseInfo {
        private String houseAddress;
        private String houseRomm;
        private String regionName;
        private String houseRommNo;
        private String renter;

        private String sellerPhone;

        private String organizationName;


        private String manageOrg;

        private String livingAreaName;

        private String housePropertyId;

        private String houseArchivesId;

    public String getHouseRommNo() {
        return houseRommNo;
    }

    public void setHouseRommNo(String houseRommNo) {
        this.houseRommNo = houseRommNo;
    }

    public String getManageOrg() {
        return manageOrg;
    }

    public void setManageOrg(String manageOrg) {
        this.manageOrg = manageOrg;
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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getHousePropertyId() {
        return housePropertyId;
    }

    public void setHousePropertyId(String housePropertyId) {
        this.housePropertyId = housePropertyId;
    }

    public String getHouseArchivesId() {
        return houseArchivesId;
    }

    public void setHouseArchivesId(String houseArchivesId) {
        this.houseArchivesId = houseArchivesId;
    }

    public String getLivingAreaName() {
        return livingAreaName;
    }

    public void setLivingAreaName(String livingAreaName) {
        this.livingAreaName = livingAreaName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseRomm() {
        StringBuffer result=new StringBuffer();
        if(super.getBedroomQuantity()!=null){
            result.append(super.getBedroomQuantity()+"室");
        }
        if(super.getLivingroomQuantity()!=null){
            result.append(super.getLivingroomQuantity()+"厅");
        }
        if(super.getBathroomQuantity()!=null){
            result.append(super.getBathroomQuantity()+"卫");
        }

        return result.toString();
    }

    public void setHouseRomm(String houseRomm) {
        this.houseRomm = houseRomm;
    }
}
