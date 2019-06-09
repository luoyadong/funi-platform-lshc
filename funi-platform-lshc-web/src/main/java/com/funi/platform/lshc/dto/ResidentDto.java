package com.funi.platform.lshc.dto;

import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * 住户
 * @author 3
 */
public class ResidentDto {
    //姓名
    @Excel(name="姓名")
    private String residentName;
    //性别
    @Excel(name="性别")
    private String residentSex;
    //联系电话
    @Excel(name="联系电话")
    private String residentTelNo;
    //证件号码
    @Excel(name="证件号码")
    private String residentIdCardNo;
    //合同编号
    @Excel(name="合同编号")
    private String contractNo;
    //房屋编号
    @Excel(name="房屋编号")
    private String houseNoSys;
    //区域
    @Excel(name="区域")
    private String regionName;
    //小区名称
    @Excel(name="小区名称")
    private String livingAreaName;
    //房号
    @Excel(name="房号")
    private String houseCode;
    //房屋地址
    @Excel(name="房屋地址")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHouseNoSys() {
        return houseNoSys;
    }

    public void setHouseNoSys(String houseNoSys) {
        this.houseNoSys = houseNoSys;
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

    public String getResidentIdCardNo() {
        return residentIdCardNo;
    }

    public void setResidentIdCardNo(String residentIdCardNo) {
        this.residentIdCardNo = residentIdCardNo;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public String getResidentSex() {
        return residentSex;
    }

    public void setResidentSex(String residentSex) {
        this.residentSex = residentSex;
    }

    public String getResidentTelNo() {
        return residentTelNo;
    }

    public void setResidentTelNo(String residentTelNo) {
        this.residentTelNo = residentTelNo;
    }
}
