package com.funi.platform.lshc.vo;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 用于收费列表页的展示
 * Created by sam on 2018/11/12.9:37 AM
 */
public class ChargeVo {
    /**
     * 合同ID
     */
    private String contractId;
    /**
     * 合同编号
     */
    private String contractNo;
    /**
     * 房屋套数
     */
    private Integer houseCount;
    /**
     * 承租方，个人：lesseeName，集体：organizationName
     */
    private String lesseeName;
    /**
     * 身份证号码或单位代码，个人：uniqueId，集体：organizationId
     */
    private String uniqueId;

    /**
     * 联系人，个人：lesseeName，集体：organizationContract
     */
    private String organizationContract;

    /**
     * 联系电话，个人：personalTel，集体：organizationTel
     */
    private String telephone;
    /**
     * 联系地址，个人：personalPermanentAddress，集体：organizationAddress
     */
    private String organizationAddress;
    /**
     * 租金交至日期
     */
    private String rentalPaytoDate;
    /**
     * 月租金
     */
    private BigDecimal rentalMonthAmount;

    /** 租赁开始日期 */
    private String rentStartDate;

    /**
     * 租赁结束日期
     */
    private String rentEndDate;

    /**
     * 收费操作链接描述
     */
    private String chargeOperateDesc;

    /** 业务件创建者所属的机构编码*/
    private String bizCreateOrgCode;

    /** 权限map*/
    private Map<String, Boolean> authorityMap;

    public Map<String, Boolean> getAuthorityMap() {
        return authorityMap;
    }

    public void setAuthorityMap(Map<String, Boolean> authorityMap) {
        this.authorityMap = authorityMap;
    }

    public String getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(String rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public String getBizCreateOrgCode() {
        return bizCreateOrgCode;
    }

    public void setBizCreateOrgCode(String bizCreateOrgCode) {
        this.bizCreateOrgCode = bizCreateOrgCode;
    }

    public String getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(String rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public String getChargeOperateDesc() {
        return chargeOperateDesc;
    }

    public void setChargeOperateDesc(String chargeOperateDesc) {
        this.chargeOperateDesc = chargeOperateDesc;
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

    public String getLesseeName() {
        return lesseeName;
    }

    public void setLesseeName(String lesseeName) {
        this.lesseeName = lesseeName;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getOrganizationContract() {
        return organizationContract;
    }

    public void setOrganizationContract(String organizationContract) {
        this.organizationContract = organizationContract;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
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

    public BigDecimal getRentalMonthAmount() {
        return rentalMonthAmount;
    }

    public void setRentalMonthAmount(BigDecimal rentalMonthAmount) {
        this.rentalMonthAmount = rentalMonthAmount;
    }
}
