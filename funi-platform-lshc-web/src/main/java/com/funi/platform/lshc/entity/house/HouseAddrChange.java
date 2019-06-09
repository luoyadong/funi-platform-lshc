package com.funi.platform.lshc.entity.house;

import com.funi.platform.lshc.entity.BaseEntity;

/**
 * GHOUSE_HOUSE_ADDR_CHANGE
 * @author 
 */
public class HouseAddrChange extends BaseEntity {
    private String houseId;

    private String addressChangeId;

    private String beforeAddress;

    private String afterAddress;

    private String changeUser;

    private String changeDate;

    private String zoneId;

    private String policeStation;

    private String changeRemark;

    private static final long serialVersionUID = 1L;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getAddressChangeId() {
        return addressChangeId;
    }

    public void setAddressChangeId(String addressChangeId) {
        this.addressChangeId = addressChangeId;
    }

    public String getBeforeAddress() {
        return beforeAddress;
    }

    public void setBeforeAddress(String beforeAddress) {
        this.beforeAddress = beforeAddress;
    }

    public String getAfterAddress() {
        return afterAddress;
    }

    public void setAfterAddress(String afterAddress) {
        this.afterAddress = afterAddress;
    }

    public String getChangeUser() {
        return changeUser;
    }

    public void setChangeUser(String changeUser) {
        this.changeUser = changeUser;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

    public String getChangeRemark() {
        return changeRemark;
    }

    public void setChangeRemark(String changeRemark) {
        this.changeRemark = changeRemark;
    }

}