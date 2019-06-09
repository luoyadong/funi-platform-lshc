package com.funi.platform.lshc.dto;

import com.funi.framework.utils.StringUtils;
import com.funi.platform.lshc.enumatation.ContractStatus;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 3
 */
public class ContractDto implements Serializable {

    //
    private String id;
    //合同编号
    @Excel(name="合同编号")
    private String contractNo;
    //房屋套数
    @Excel(name="房屋套数")
    private Integer houseNum;
    //计算面积
    private BigDecimal houseArea;
    //每月租金
    @Excel(name="每月租金")
    private BigDecimal rentPerMonth;
    //单位名称
    @Excel(name="单位名称")
    private String organizationName;
    //联系人
    private String contacts;
    //联系电话
    @Excel(name="联系电话")
    private String organizationPhone;
    //承租人
    @Excel(name="承租人")
    private String renter;
    //承租人身份证
    private String cardNum;
    //承租人电话
    private String sellerPhone;
    //租赁开始时间
    @Excel(name="租赁开始时间")
    private String rentStartDate;
    //租赁结束时间
    @Excel(name="租赁结束时间")
    private String rentEndDate;
    //租金交至日期
    private String rentalPayToDate;
    //合同失效日期
    private String contractInvalidDate;
    //合同失效原因
    private String contractInvalidReason;
    //计租面积
    @Excel(name="计租面积")
    private BigDecimal rentArea;
    //业务件号
    private String serviceNum;
    //受理id
    private String jobAcceptId;
    //业务类型名称
    private String businessTypeName;
    //节点名称
    private String nodeName;
    //  r
    private String contractStatus;
    //
    private String contractStatusStr;
    //
    private String organizationLesseeName;
    //
    private String organizationLesseeIdNo;
    //
    private String organizationLesseeTel;

    public String getOrganizationLesseeIdNo() {
        return organizationLesseeIdNo;
    }

    public void setOrganizationLesseeIdNo(String organizationLesseeIdNo) {
        this.organizationLesseeIdNo = organizationLesseeIdNo;
    }

    public String getOrganizationLesseeName() {
        return organizationLesseeName;
    }

    public void setOrganizationLesseeName(String organizationLesseeName) {
        this.organizationLesseeName = organizationLesseeName;
    }

    public String getOrganizationLesseeTel() {
        return organizationLesseeTel;
    }

    public void setOrganizationLesseeTel(String organizationLesseeTel) {
        this.organizationLesseeTel = organizationLesseeTel;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getContractStatusStr() {
        if(StringUtils.hasText(contractStatus)) {
            return Enum.valueOf(ContractStatus.class, contractStatus).getDescription();
        }
        return null;
    }



    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(String serviceNum) {
        this.serviceNum = serviceNum;
    }

    public BigDecimal getRentArea() {
        return rentArea;
    }

    public void setRentArea(BigDecimal rentArea) {
        this.rentArea = rentArea;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContractInvalidDate() {
        return contractInvalidDate;
    }

    public void setContractInvalidDate(String contractInvalidDate) {
        this.contractInvalidDate = contractInvalidDate;
    }

    public String getContractInvalidReason() {
        return contractInvalidReason;
    }

    public void setContractInvalidReason(String contractInvalidReason) {
        this.contractInvalidReason = contractInvalidReason;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public BigDecimal getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(BigDecimal houseArea) {
        this.houseArea = houseArea;
    }

    public Integer getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationPhone() {
        return organizationPhone;
    }

    public void setOrganizationPhone(String organizationPhone) {
        this.organizationPhone = organizationPhone;
    }

    public String getRentalPayToDate() {
        return rentalPayToDate;
    }

    public void setRentalPayToDate(String rentalPayToDate) {
        this.rentalPayToDate = rentalPayToDate;
    }

    public String getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(String rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public BigDecimal getRentPerMonth() {
        return rentPerMonth;
    }

    public void setRentPerMonth(BigDecimal rentPerMonth) {
        this.rentPerMonth = rentPerMonth;
    }

    public String getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(String rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public String getJobAcceptId() {
        return jobAcceptId;
    }

    public void setJobAcceptId(String jobAcceptId) {
        this.jobAcceptId = jobAcceptId;
    }
}
