package com.funi.platform.lshc.vo;

import java.math.BigDecimal;

/**
 * 收费页面查看合同房屋列表
 * Created by sam on 2018/11/14.12:36 AM
 */
public class ChargeHouseVo {
    /**
     * 房屋户号
     */
    private String houseNo;
    /**
     * 小区名称
     */
    private String livingAreaName;
    /**
     * 栋单元楼号
     */
    private String houseAddress;
    /**
     * 计租面积
     */
    private BigDecimal rentArea;
    /**
     * 物业类型
     */
    private String hourseUseType;
    /**
     * 管理机构
     */
    private String createUnitId;

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getLivingAreaName() {
        return livingAreaName;
    }

    public void setLivingAreaName(String livingAreaName) {
        this.livingAreaName = livingAreaName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public BigDecimal getRentArea() {
        return rentArea;
    }

    public void setRentArea(BigDecimal rentArea) {
        this.rentArea = rentArea;
    }

    public String getHourseUseType() {
        return hourseUseType;
    }

    public void setHourseUseType(String hourseUseType) {
        this.hourseUseType = hourseUseType;
    }

    public String getCreateUnitId() {
        return createUnitId;
    }

    public void setCreateUnitId(String createUnitId) {
        this.createUnitId = createUnitId;
    }
}
