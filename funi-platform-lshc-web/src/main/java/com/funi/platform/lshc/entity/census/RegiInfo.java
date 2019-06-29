package com.funi.platform.lshc.entity.census;

import com.funi.platform.lshc.entity.SuperEntity;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 普查信息
 * @author sam
 * @date 2019-06-14 22:18:28
 */
public class RegiInfo extends SuperEntity implements Serializable {
    /** 房屋编号 */
    private String houseId;

    /** 普查系统编号 */
    private String sysCode;

    /** 普查信息状态 */
    private String houseStatus;

    /** 审批状态 */
    private String approvalStatus;

    /** 区县 */
    private String region;

    /** 街道 */
    private String street;

    /** 项目名称 */
    private String projectName;

    /** 楼栋地图编号 */
    private String mapCode;

    /** 物管单位 */
    private String estateUnitName;

    /** 实际地址(市) */
    private String addressCity;

    /** 实际地址(区) */
    private String addressRegion;

    /** 实际地址(县) */
    private String addressCounty;

    /** 实际地址，非数据库字段，由市区县拼接而成*/
    private String addressDetail;

    /** 门牌号 */
    private String apt;

    /** 物理楼栋 */
    private String buildNo;

    /** 单元 */
    private String unitNo;

    /** 楼层 */
    private String layer;

    /** 房屋号 */
    private String roomNo;

    /** 产权证地址 */
    private String rightAddr;

    /** 建筑面积 */
    private BigDecimal houseArea;

    /** 套内面积 */
    private BigDecimal innerHouseArea;

    /** 房屋户型(室) */
    private String houseRoom;

    /** 房屋户型(厅) */
    private String houseHall;

    /** 房屋户型(卫) */
    private String houseBathroom;

    /** 是否办理产权 */
    private String isRegi;

    /** 产权证号 */
    private String rightNo;

    /** 建成年份 */
    private String buildDate;

    /** 房屋类别 */
    private String houseType;

    /** 房屋结构 */
    private String houseStructure;

    /** 房屋用途 */
    private String houseUse;

    /** 土地性质 */
    private String landStatus;

    /** 预售许可证 */
    private String preSaleNo;

    /** 装修状态 */
    private String fitStatus;

    /** 是否入住 */
    private String isCheckIn;

    /** 居住人数 */
    private Integer personNum;

    /** 是否出租 */
    private String isRent;

    /** 出租开始时间 */
    private String rentStartDate;

    /** 出租结束时间 */
    private String rentEndDate;

    /** 填报者机构编码 */
    private String orgCode;

    /** 机构名称 */
    private String orgName;

    /** 填报单位 */
    private String unitName;

    /** 填报人 */
    private String applyUser;

    /** 填报时间 */
    private String reportDate;

    /** 备用 */
    private String common;

    /** 楼栋名称*/
    private String buildName;
    /** 社区名称*/
    private String communityName;
    /** 总层数 */
    private String totalLayer;

    private static final long serialVersionUID = 1L;

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getTotalLayer() {
        return totalLayer;
    }

    public void setTotalLayer(String totalLayer) {
        this.totalLayer = totalLayer;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getEstateUnitName() {
        return estateUnitName;
    }

    public void setEstateUnitName(String estateUnitName) {
        this.estateUnitName = estateUnitName;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressRegion() {
        return addressRegion;
    }

    public void setAddressRegion(String addressRegion) {
        this.addressRegion = addressRegion;
    }

    public String getAddressCounty() {
        return addressCounty;
    }

    public void setAddressCounty(String addressCounty) {
        this.addressCounty = addressCounty;
    }

    public String getAddressDetail() {
        StringBuilder regiInfoBuilder = new StringBuilder();
        String addressCity = getAddressCity();
        if(StringUtils.isNotBlank(addressCity)) {
            regiInfoBuilder.append(addressCity);
        }
        String addressRegion = getRegion();
        if(StringUtils.isNotBlank(addressRegion)) {
            regiInfoBuilder.append(addressRegion);
        }
        String addressCounty = getStreet();
        if(StringUtils.isNotBlank(addressCounty)) {
            regiInfoBuilder.append(addressCounty);
        }
        return regiInfoBuilder.toString();
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public String getBuildNo() {
        return buildNo;
    }

    public void setBuildNo(String buildNo) {
        this.buildNo = buildNo;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRightAddr() {
        return rightAddr;
    }

    public void setRightAddr(String rightAddr) {
        this.rightAddr = rightAddr;
    }

    public BigDecimal getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(BigDecimal houseArea) {
        this.houseArea = houseArea;
    }

    public BigDecimal getInnerHouseArea() {
        return innerHouseArea;
    }

    public void setInnerHouseArea(BigDecimal innerHouseArea) {
        this.innerHouseArea = innerHouseArea;
    }

    public String getHouseRoom() {
        return houseRoom;
    }

    public void setHouseRoom(String houseRoom) {
        this.houseRoom = houseRoom;
    }

    public String getHouseHall() {
        return houseHall;
    }

    public void setHouseHall(String houseHall) {
        this.houseHall = houseHall;
    }

    public String getHouseBathroom() {
        return houseBathroom;
    }

    public void setHouseBathroom(String houseBathroom) {
        this.houseBathroom = houseBathroom;
    }

    public String getIsRegi() {
        return isRegi;
    }

    public void setIsRegi(String isRegi) {
        this.isRegi = isRegi;
    }

    public String getRightNo() {
        return rightNo;
    }

    public void setRightNo(String rightNo) {
        this.rightNo = rightNo;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseStructure() {
        return houseStructure;
    }

    public void setHouseStructure(String houseStructure) {
        this.houseStructure = houseStructure;
    }

    public String getHouseUse() {
        return houseUse;
    }

    public void setHouseUse(String houseUse) {
        this.houseUse = houseUse;
    }

    public String getLandStatus() {
        return landStatus;
    }

    public void setLandStatus(String landStatus) {
        this.landStatus = landStatus;
    }

    public String getPreSaleNo() {
        return preSaleNo;
    }

    public void setPreSaleNo(String preSaleNo) {
        this.preSaleNo = preSaleNo;
    }

    public String getFitStatus() {
        return fitStatus;
    }

    public void setFitStatus(String fitStatus) {
        this.fitStatus = fitStatus;
    }

    public String getIsCheckIn() {
        return isCheckIn;
    }

    public void setIsCheckIn(String isCheckIn) {
        this.isCheckIn = isCheckIn;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public String getIsRent() {
        return isRent;
    }

    public void setIsRent(String isRent) {
        this.isRent = isRent;
    }

    public String getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(String rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public String getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(String rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }
}