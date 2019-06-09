package com.funi.platform.lshc.entity.house;

import com.funi.platform.lshc.entity.BaseEntity;

import java.io.Serializable;

/**
 * GHOUSE_HOUSE_OPERATE
 * @author 
 */
public class HouseOperate extends BaseEntity implements Serializable {
    private String houseId;

    private String operateType;

    private String operateUser;

    private String operateDate;

    private String operateRemark;

    private static final long serialVersionUID = 1L;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark;
    }

}