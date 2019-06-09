package com.funi.platform.lshc.entity.house;

import com.funi.platform.lshc.entity.BaseEntity;
import com.funi.platform.lshc.utils.DateUtils;

import java.util.Date;

/**
 * GHOUSE_ADDR_CHANGE
 * @author 
 */
public class AddrChange extends BaseEntity {
    private String addressId;

    private String beforeAddress;

    private String afterAddress;

    private String afterStreetName;

    private String afterStreetNo;

    private String changeUser;

    private String changeDate;

    private String changeRemark;

    public AddrChange() {

    }

    public AddrChange(AddrInfo oldAddrInfo, AddrInfo editAddrInfo, String oldRegionName, String newRegionName) {
        this.addressId = oldAddrInfo.getId();
        this.beforeAddress = oldRegionName + oldAddrInfo.getCompleteAddress(oldAddrInfo);
        this.afterAddress = newRegionName + editAddrInfo.getCompleteAddress(editAddrInfo);
        this.afterStreetName = editAddrInfo.getStreetName();
        this.afterStreetNo = editAddrInfo.getStreetNo();
        this.changeRemark = editAddrInfo.getAddrRemark();
        this.changeDate = DateUtils.parseFormatDate(new Date());
    }



    private static final long serialVersionUID = 1L;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
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

    public String getAfterStreetName() {
        return afterStreetName;
    }

    public void setAfterStreetName(String afterStreetName) {
        this.afterStreetName = afterStreetName;
    }

    public String getAfterStreetNo() {
        return afterStreetNo;
    }

    public void setAfterStreetNo(String afterStreetNo) {
        this.afterStreetNo = afterStreetNo;
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

    public String getChangeRemark() {
        return changeRemark;
    }

    public void setChangeRemark(String changeRemark) {
        this.changeRemark = changeRemark;
    }

}