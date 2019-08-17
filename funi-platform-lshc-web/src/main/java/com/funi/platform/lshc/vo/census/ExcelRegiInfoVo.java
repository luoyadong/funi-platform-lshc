package com.funi.platform.lshc.vo.census;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by sam on 2019/6/15.1:09 PM
 */
public class ExcelRegiInfoVo {
    @Excel(name="填报单位")
    private String unitName;
    @Excel(name="填报人员")
    private String applyUser;
    @Excel(name="填报时间")
    private String reportDate;
    @Excel(name="项目（小区）名称")
    private String projectName;
    @Excel(name="物业管理单位")
    private String estateUnitName;
    @Excel(name="楼栋地图编号")
    private String mapCode;
    @Excel(name="楼栋名称")
    private String buildName;
    @Excel(name="楼栋总层数")
    private String totalLayer;
    @Excel(name="房屋编号")
    private String houseId;
    @Excel(name="权利人（共有权利人）")
    private String entName;
    @Excel(name="权利人身份证号（统一社会信用代码）")
    private String idNo;
    @Excel(name="人员类别")
    private String entType;
    @Excel(name="性别")
    private String sex;
    @Excel(name="民族")
    private String entNation;
    @Excel(name="籍贯")
    private String entNative;
    @Excel(name="联系电话")
    private String tel;
    @Excel(name="婚姻状态")
    private String marriageStatus;
    @Excel(name="区县")
    private String region;
    @Excel(name="街道（乡镇）")
    private String street;
    @Excel(name="门牌号")
    private String apt;
    @Excel(name="栋")
    private String buildNo;
    @Excel(name="单元")
    private String unitNo;
    @Excel(name="层")
    private String layer;
    @Excel(name="号")
    private String roomNo;
    @Excel(name="房屋坐落")
    private String rightAddr;
    @Excel(name="房屋面积（m²）")
    private BigDecimal houseArea;
    @Excel(name="套内面积（m²）")
    private BigDecimal innerHouseArea;
    @Excel(name="室")
    private String houseRoom;
    @Excel(name="厅")
    private String houseHall;
    @Excel(name="是否办理产权")
    private String isRegi;
    @Excel(name="产权证号")
    private String rightNo;
    @Excel(name="建成年份")
    private String buildDate;
    @Excel(name="房屋类别")
    private String houseType;
    @Excel(name="房屋结构")
    private String houseStructure;
    @Excel(name="房屋用途")
    private String houseUse;
    @Excel(name="土地性质")
    private String landStatus;
    @Excel(name="网签合同号")
    private String preSaleNo;
    @Excel(name="装修状态")
    private String fitStatus;
    @Excel(name="是否入住")
    private String isCheckIn;
    @Excel(name="是否出租")
    private String isRent;
    @Excel(name="出租开始日期")
    private String rentStartDate;
    @Excel(name="出租截止日期")
    private String rentEndDate;
    @Excel(name="居住人口数量")
    private Integer personNum;

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

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

}
