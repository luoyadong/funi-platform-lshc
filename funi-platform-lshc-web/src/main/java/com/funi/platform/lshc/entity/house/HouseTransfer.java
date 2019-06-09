package com.funi.platform.lshc.entity.house;

import com.funi.platform.lshc.entity.BaseEntity;

/**
 * GHOUSE_HOUSE_TRANSFER
 * @author 
 */
public class HouseTransfer extends BaseEntity {
    private String houseId;

    private String acceptUnit;

    private String acceptRemark;

    private String transferUserId;

    private String transferDate;

    private static final long serialVersionUID = 1L;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getAcceptUnit() {
        return acceptUnit;
    }

    public void setAcceptUnit(String acceptUnit) {
        this.acceptUnit = acceptUnit;
    }

    public String getAcceptRemark() {
        return acceptRemark;
    }

    public void setAcceptRemark(String acceptRemark) {
        this.acceptRemark = acceptRemark;
    }

    public String getTransferUserId() {
        return transferUserId;
    }

    public void setTransferUserId(String transferUserId) {
        this.transferUserId = transferUserId;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

}