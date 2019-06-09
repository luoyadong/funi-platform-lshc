package com.funi.platform.lshc.vo;

import java.util.Map;

/**
 * Created by sam on 2018/11/6.11:31 PM
 */
public class AddressVo {
    /**数据库主键ID*/
    private String id;

    /** 序号 */
    private Integer serialNo;

    /** 区域名称 */
    private String regionName;

    /** 小区名称 */
    private String livingAreaName;

    /** 街道（路/巷） */
    private String streetName;

    /** 号 */
    private String streetNo;

    /** 备注 */
    private String addrRemark;

    /** 坐标X，经度 */
    private String positionX;

    /** 坐标Y，纬度 */
    private String positionY;

    /** 小区对应房屋套数*/
    private Integer houseNum;

    /** 是否变更，默认未变更 */
    private Integer isChange;

    /** 显示查看的URL*/
    private String showChangeDesc;
    /** 操作链接展示*/
    private String operateDesc;
    /** 业务件创建者所属的机构编码 BIZ_CREATE_ORG_CODE */
    private String bizCreateOrgCode;

    /** 权限map*/
    private Map<String, Boolean> authorityMap;

    public Map<String, Boolean> getAuthorityMap() {
        return authorityMap;
    }

    public void setAuthorityMap(Map<String, Boolean> authorityMap) {
        this.authorityMap = authorityMap;
    }

    public String getBizCreateOrgCode() {
        return bizCreateOrgCode;
    }

    public void setBizCreateOrgCode(String bizCreateOrgCode) {
        this.bizCreateOrgCode = bizCreateOrgCode;
    }

    public String getOperateDesc() {
        return operateDesc;
    }

    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc;
    }

    public String getShowChangeDesc() {
        return showChangeDesc;
    }

    public void setShowChangeDesc(String showChangeDesc) {
        this.showChangeDesc = showChangeDesc;
    }

    public Integer getIsChange() {
        return isChange;
    }

    public void setIsChange(Integer isChange) {
        this.isChange = isChange;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getLivingAreaName() {
        return livingAreaName;
    }

    public void setLivingAreaName(String livingAreaName) {
        this.livingAreaName = livingAreaName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getAddrRemark() {
        return addrRemark;
    }

    public void setAddrRemark(String addrRemark) {
        this.addrRemark = addrRemark;
    }

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }

    public Integer getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }
}
