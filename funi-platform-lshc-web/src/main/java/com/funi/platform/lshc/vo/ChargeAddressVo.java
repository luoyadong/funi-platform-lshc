package com.funi.platform.lshc.vo;

/**
 * 收费详情中的房屋地址
 * Created by sam on 2018/11/14.11:00 AM
 */
public class ChargeAddressVo {
    /** 区域*/
    private String regionName;
    /** 小区地址 */
    private String addressDetail;
    /** 栋*/
    private String addressBuilding;
    /** 单元*/
    private String addressUnit;
    /** 楼*/
    private String addressFloor;
    /** 号*/
    private String addressRoomNo;
    /** 小区名称 */
    private String livingAreaName;
    /** 房号*/
    private String houseNo;

    public String getLivingAreaName() {
        return livingAreaName;
    }

    public void setLivingAreaName(String livingAreaName) {
        this.livingAreaName = livingAreaName;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
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
}
