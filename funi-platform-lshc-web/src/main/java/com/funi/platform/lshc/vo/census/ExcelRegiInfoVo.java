package com.funi.platform.lshc.vo.census;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by sam on 2019/6/15.1:09 PM
 */
public class ExcelRegiInfoVo {
    /** 填报单位 */
    @Excel(name="填报单位")
    private String unitName;
    /** 填报人员 */
    @Excel(name="填报人员")
    private String applyUser;
    /** 填报时间 */
    @Excel(name="填报时间")
    private String reportDate;
    /** 项目名称 */
    @Excel(name="项目（小区）名称")
    private String projectName;
    /** 物管单位 */
    @Excel(name="物业管理单位")
    private String estateUnitName;
    /** 楼栋地图编号 */
    @Excel(name="楼栋地图编号")
    private String mapCode;
    /** 楼栋名称*/
    @Excel(name="楼栋名称")
    private String buildName;
    /** 楼栋总层数 */
    @Excel(name="楼栋总层数")
    private String totalLayer;
    /** 房屋编号 */
    @Excel(name="房屋编号")
    private String houseId;
    /** 区县 */
    @Excel(name="区县")
    private String region;
    /** 街道 */
    @Excel(name="街道（乡镇）")
    private String street;
    /** 门牌号 */
    @Excel(name="门牌号")
    private String apt;
    /** 物理楼栋 */
    @Excel(name="栋")
    private String buildNo;
    /** 单元 */
    @Excel(name="单元")
    private String unitNo;
    /** 楼层 */
    @Excel(name="层")
    private String layer;
    /** 房屋号 */
    @Excel(name="号")
    private String roomNo;
    /** 房屋坐落*/
    @Excel(name="房屋坐落")
    private String rightAddr;
    /** 建筑面积 */
    @Excel(name="建筑面积（m²）")
    private BigDecimal houseArea;
    /** 套内面积 */
    @Excel(name="套内面积（m²）")
    private BigDecimal innerHouseArea;
    /** 房屋户型(室) */
    @Excel(name="室")
    private String houseRoom;
    /** 房屋户型(厅) */
    @Excel(name="厅")
    private String houseHall;
    /** 房屋户型(卫) */
    @Excel(name="卫")
    private String houseBathroom;
    /** 是否办理产权 */
    @Excel(name="是否办理产权")
    private String isRegi;
    /** 产权证号 */
    @Excel(name="产权证号")
    private String rightNo;
    /** 建成年份 */
    @Excel(name="建成年份")
    private String buildDate;
    /** 房屋类别 */
    @Excel(name="房屋类别")
    private String houseType;
    /** 房屋结构 */
    @Excel(name="房屋结构")
    private String houseStructure;
    /** 房屋用途 */
    @Excel(name="房屋用途")
    private String houseUse;
    /** 土地性质 */
    @Excel(name="土地性质")
    private String landStatus;
    /** 预售许可证号 */
    @Excel(name="预售许可证号")
    private String preSaleNo;
    /** 装修状态 */
    @Excel(name="装修状态")
    private String fitStatus;
    /** 是否入住 */
    @Excel(name="是否入住")
    private String isCheckIn;
    /** 是否出租 */
    @Excel(name="是否出租")
    private String isRent;
    /** 出租开始日期 */
    @Excel(name="出租开始日期")
    private String rentStartDate;
    /** 出租截止日期 */
    @Excel(name="出租截止日期")
    private String rentEndDate;
    /** 居住人口数量 */
    @Excel(name="居住人口数量")
    private Integer personNum;
    /** 姓名 */
    @Excel(name="姓名")
    private String entName;
    /** 人员类别 */
    @Excel(name="人员类别")
    private String entType;
    /** 性别 */
    @Excel(name="性别")
    private String sex;
    /** 民族 */
    @Excel(name="民族")
    private String entNation;
    /** 籍贯 */
    @Excel(name="籍贯")
    private String entNative;
    /** 联系电话 */
    @Excel(name="联系电话")
    private String tel;
    /** 婚姻状态 */
    @Excel(name="婚姻状态")
    private String marriageStatus;
    /** 证件类型 */
    @Excel(name="证件类型")
    private String idType;
    /** 证件号码 */
    @Excel(name="证件号码")
    private String idNo;
    /** 职业 */
    @Excel(name="职业")
    private String career;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExcelRegiInfoVo)) {
            return false;
        }
        ExcelRegiInfoVo that = (ExcelRegiInfoVo) o;
        return Objects.equals(getMapCode(), that.getMapCode()) &&
                Objects.equals(getRegion(), that.getRegion()) &&
                Objects.equals(getStreet(), that.getStreet()) &&
                Objects.equals(getApt(), that.getApt()) &&
                Objects.equals(getBuildNo(), that.getBuildNo()) &&
                Objects.equals(getUnitNo(), that.getUnitNo()) &&
                Objects.equals(getLayer(), that.getLayer()) &&
                Objects.equals(getRoomNo(), that.getRoomNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMapCode(), getRegion(), getStreet(), getApt(), getBuildNo(), getUnitNo(), getLayer(), getRoomNo());
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

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getTotalLayer() {
        return totalLayer;
    }

    public void setTotalLayer(String totalLayer) {
        this.totalLayer = totalLayer;
    }

    public String getRightAddr() {
        return rightAddr;
    }

    public void setRightAddr(String rightAddr) {
        this.rightAddr = rightAddr;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEstateUnitName() {
        return estateUnitName;
    }

    public void setEstateUnitName(String estateUnitName) {
        this.estateUnitName = estateUnitName;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
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

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getEntType() {
        return entType;
    }

    public void setEntType(String entType) {
        this.entType = entType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEntNation() {
        return entNation;
    }

    public void setEntNation(String entNation) {
        this.entNation = entNation;
    }

    public String getEntNative() {
        return entNative;
    }

    public void setEntNative(String entNative) {
        this.entNative = entNative;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
}
