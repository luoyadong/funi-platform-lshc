package com.funi.platform.lshc.vo;

import com.funi.platform.lshc.utils.DateUtils;
import com.funi.platform.lshc.utils.RentUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by sam on 2018/11/17.6:19 PM
 */
public class ArrearageVo {
    /** 合同ID */
    private String contractId;
    /** 合同编号 */
    @Excel(name="合同编号")
    private String contractNo;
    /** 房屋套数 */
    @Excel(name="房屋套数")
    private Integer houseCount;
    /** 单位名称*/
    @Excel(name="单位名称")
    private String organizationName;
    /** 承租人*/
    @Excel(name="承租人")
    private String lesseeName;
    /** 联系电话，个人：personalTel，集体：organizationTel */
    @Excel(name="联系电话")
    private String telephone;
    /** 计租面积 */
    @Excel(name="计租面积(㎡)")
    private BigDecimal rentArea;
    /** 月租金 */
    @Excel(name="月租金(元)")
    private BigDecimal rentalMonthAmount;
    /** 租金交至日期 */
    @Excel(name="租金交至")
    private String rentalPaytoDate;
    /** 欠租终止年月日 */
    private String backRentEndDate;
    /** 累计欠租金额 */
    @Excel(name="累计欠租")
    private BigDecimal backRentAmount;
    /** 租赁开始日期 */
    private String rentStartDate;
    /** 租赁结束日期 */
    private String rentEndDate;
    /** 租金结算方式 0月结，1年结*/
    private String rentalPaymentType;
    /** 房屋地址集合 */
    private List<ChargeAddressVo> chargeAddressVoList;
    /** 打印操作链接*/
    private String printOperateDesc;
    /** 业务件创建者所属的机构编码*/
    private String bizCreateOrgCode;

    public String getPrintOperateDesc() {
        return printOperateDesc;
    }

    public void setPrintOperateDesc(String printOperateDesc) {
        this.printOperateDesc = printOperateDesc;
    }

    public String getBizCreateOrgCode() {
        return bizCreateOrgCode;
    }

    public void setBizCreateOrgCode(String bizCreateOrgCode) {
        this.bizCreateOrgCode = bizCreateOrgCode;
    }

    public String getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(String rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public List<ChargeAddressVo> getChargeAddressVoList() {
        return chargeAddressVoList;
    }

    public void setChargeAddressVoList(List<ChargeAddressVo> chargeAddressVoList) {
        this.chargeAddressVoList = chargeAddressVoList;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Integer getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(Integer houseCount) {
        this.houseCount = houseCount;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getLesseeName() {
        return lesseeName;
    }

    public void setLesseeName(String lesseeName) {
        this.lesseeName = lesseeName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public BigDecimal getRentArea() {
        return rentArea;
    }

    public void setRentArea(BigDecimal rentArea) {
        this.rentArea = rentArea;
    }

    public BigDecimal getRentalMonthAmount() {
        if(rentalMonthAmount == null) {
            return BigDecimal.ZERO;
        }
        return rentalMonthAmount;
    }

    public void setRentalMonthAmount(BigDecimal rentalMonthAmount) {
        this.rentalMonthAmount = rentalMonthAmount;
    }

    public String getRentalPaytoDate() {
        if (StringUtils.isBlank(rentalPaytoDate)) {
            return getRentStartDate();
        }
        return rentalPaytoDate;

    }

    public void setRentalPaytoDate(String rentalPaytoDate) {
        this.rentalPaytoDate = rentalPaytoDate;
    }

    public BigDecimal getBackRentAmount() {
        if (isArrearsRent()) {
            // 获得欠租开始日期
            String backRentStartDate = getBackRentStartDate();
            // 获得欠租结束日期
            String backRentEndDate = getBackRentEndDate();
            return RentUtils.calcRent(backRentStartDate, backRentEndDate, getRentalMonthAmount());
        }
        return BigDecimal.ZERO;
    }

    /**
     * 获得欠租截止日期
     * @return
     */
    public String getBackRentEndDate() {
        // 如果欠租
        if (isArrearsRent()) {
            // 租金结算方式 0月结，1年结，默认为年结
            String rentalPaymentType = getRentalPaymentType();
            if(StringUtils.isBlank(rentalPaymentType)) {
                rentalPaymentType = "1";
            }
            // 获取欠租开始年月日
            String backRentStartDate = getBackRentStartDate();
            // 合同截止日期
            String rentEndDate = getRentEndDate();
            // 如果是年结
            if("1".equals(rentalPaymentType)) {
                String addOneYearDate = DateUtils.addYear(backRentStartDate, 1);
                // 如果添加一年后，日期大于租赁截止日期，欠租截止日期为租赁截止日期
                if(DateUtils.getDifferDay(rentEndDate, addOneYearDate) > 0) {
                    return rentEndDate;
                } else {
                    return addOneYearDate;
                }
            } else {
                // 如果是月结
                String addOneMonthDate = DateUtils.addMonth(backRentStartDate, 1);
                if(DateUtils.getDifferDay(rentEndDate, addOneMonthDate) > 0) {
                    return rentEndDate;
                } else {
                    return addOneMonthDate;
                }
            }
        }
        return backRentEndDate;
    }

    public String getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(String rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public void setBackRentEndDate(String backRentEndDate) {
        this.backRentEndDate = backRentEndDate;
    }

    public String getRentalPaymentType() {
        return rentalPaymentType;
    }

    public void setRentalPaymentType(String rentalPaymentType) {
        this.rentalPaymentType = rentalPaymentType;
    }

    /**
     * 获取欠租开始日期
     * @return
     */
    public String getBackRentStartDate() {
        // 如果欠租，默认欠租时间为租金交至日期
        if (isArrearsRent()) {
            return getRentalPaytoDate();
        }
        return null;
    }

    /**
     * 是否欠租
     *
     * @return
     */
    private boolean isArrearsRent() {
        int differDay = DateUtils.getDifferDay(getRentalPaytoDate(), DateUtils.getNow());
        return differDay > 0;
    }

    public void setBackRentAmount(BigDecimal backRentAmount) {
        this.backRentAmount = backRentAmount;
    }
}
