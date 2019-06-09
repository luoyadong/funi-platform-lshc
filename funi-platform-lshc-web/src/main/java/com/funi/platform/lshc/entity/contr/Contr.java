package com.funi.platform.lshc.entity.contr;

import com.funi.platform.lshc.entity.BaseEntity;
import com.funi.platform.lshc.enumatation.BusinessType;
import com.funi.platform.lshc.enumatation.ContractStatus;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 合同
 * @author 3
 */
public class Contr extends BaseEntity {

    /**
     * 合同编号_前缀
     */
    private String contractNoPrefix;

    /**
     * 合同编号_序号
     */
    private String contractNoSeq;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 合同状态
     */
    private String contractStatus = ContractStatus.EXAMINING.name();

    /**
     * 合同流程状态
     */
    private String contractProcessStatus;

    /**
     * 合同类型
     */
    private String contractType;

    /**
     * 建租类别
     */
    private String rentType;

    /**
     * 承租人ID
     */
    private String lesseeId;

    /**
     * 租赁开始日期
     */
    private String rentStartDate;

    /**
     * 租赁结束日期
     */
    private String rentEndDate;

    /**
     * 租金交至日期
     */
    private String rentalPaytoDate;

    /**
     * 合同失效日期
     */
    private String contractInvalidDate;

    /**
     * 合同失效原因
     */
    private String contractInvalidReason;

    /**
     * 合同存放点_档案室
     */
    private String archiveRoomNo;

    /**
     * 合同存放点_档案柜
     */
    private String archiveCabinetNo;

    /**
     * 合同存放点_档案号
     */
    private String archiveFileNo;

    /**
     * 建筑面积
     */
    private BigDecimal bulidArea;

    /**
     * 计租面积
     */
    private BigDecimal rentArea;

    /**
     * 租金标准
     */
    private BigDecimal rentalStandAmount;

    /**
     * 月租金
     */
    private BigDecimal rentPerMonth;

    /**
     * 租金设置类型
     */
    private String rentalSettingType;

    /**
     * 租金结算方式
     */
    private String rentalPaymentType;

    /**
     * 是否收取滞纳金
     */
    private String latefeePayment;

    /**
     * 滞纳金收取设置类型
     */
    private String latefeePaymentType;

    /**
     * 滞纳金收取设置数量1
     */
    private Integer latefeePaymentValue1;

    /**
     * 滞纳金收取设置数量2
     */
    private Integer latefeePaymentValue2;

    /**
     * 保证金
     */
    private BigDecimal depositAmount;

    /**
     * 保证金收取状态，null未收取，1已收取，2已退
     */
    private String depositStatus;

    /**
     * 保证金票据号
     */
    private String depositBillNo;

    /**
     * 其他约定事项
     */
    private String addInformation;

    /**
     * 资料档案编号
     */
    private String documentNo;

    /**
     * 文件数量
     */
    private Integer documentCnt;

    /**
     * 档案存放时间
     */
    private String documentDate;

    /**
     * 档案备注
     */
    private String documentAddInfo;

    /**
     * 合同登记人
     */
    private String contractRegisterUserId;

    /**
     * 合同登记日期
     */
    private Date contractRegisterTime;

    /**
     * 合同发放人
     */
    private String contractIssueUserId;

    /**
     * 合同发放日期
     */
    private Date contractIssueTime;

    /**
     * 合同领取单位
     */
    private String contractReceiveOffice;

    /**
     * 合同领取人
     */
    private String contractReceiveUser;

    /**
     * 合同领取日期
     */
    private Date contractReceiveTime;

    /**
     * 合同交回人
     */
    private String contractReturnUser;

    /**
     * 合同回收人
     */
    private String contractRecycleUserId;

    /**
     * 创建者机构编码
     */
    private String createUnitId;

    /**
     * 合同编号(续签原合同编号)
     */
    private String contractNoExtended;

    /**
     * 合同回收日期
     */
    private Date contractRecycleTime;
    //评估单间
    private BigDecimal evaluatePrice;
    //业务类型
    private BusinessType businessType;
    //业务件号
    private String serviceNum;

    public String getDepositStatus() {
        return depositStatus;
    }

    public void setDepositStatus(String depositStatus) {
        this.depositStatus = depositStatus;
    }

    public BigDecimal getEvaluatePrice() {
        return evaluatePrice;
    }

    public void setEvaluatePrice(BigDecimal evaluatePrice) {
        this.evaluatePrice = evaluatePrice;
    }

    public String getAddInformation() {
        return addInformation;
    }

    public void setAddInformation(String addInformation) {
        this.addInformation = addInformation;
    }

    public String getArchiveCabinetNo() {
        return archiveCabinetNo;
    }

    public void setArchiveCabinetNo(String archiveCabinetNo) {
        this.archiveCabinetNo = archiveCabinetNo;
    }

    public String getArchiveFileNo() {
        return archiveFileNo;
    }

    public void setArchiveFileNo(String archiveFileNo) {
        this.archiveFileNo = archiveFileNo;
    }

    public String getArchiveRoomNo() {
        return archiveRoomNo;
    }

    public void setArchiveRoomNo(String archiveRoomNo) {
        this.archiveRoomNo = archiveRoomNo;
    }

    public BigDecimal getBulidArea() {
        return bulidArea;
    }

    public void setBulidArea(BigDecimal bulidArea) {
        this.bulidArea = bulidArea;
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

    public Date getContractIssueTime() {
        return contractIssueTime;
    }

    public void setContractIssueTime(Date contractIssueTime) {
        this.contractIssueTime = contractIssueTime;
    }

    public String getContractIssueUserId() {
        return contractIssueUserId;
    }

    public void setContractIssueUserId(String contractIssueUserId) {
        this.contractIssueUserId = contractIssueUserId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractNoExtended() {
        return contractNoExtended;
    }

    public void setContractNoExtended(String contractNoExtended) {
        this.contractNoExtended = contractNoExtended;
    }

    public String getContractNoPrefix() {
        return contractNoPrefix;
    }

    public void setContractNoPrefix(String contractNoPrefix) {
        this.contractNoPrefix = contractNoPrefix;
    }

    public String getContractNoSeq() {
        return contractNoSeq;
    }

    public void setContractNoSeq(String contractNoSeq) {
        this.contractNoSeq = contractNoSeq;
    }

    public String getContractProcessStatus() {
        return contractProcessStatus;
    }

    public void setContractProcessStatus(String contractProcessStatus) {
        this.contractProcessStatus = contractProcessStatus;
    }

    public String getContractReceiveOffice() {
        return contractReceiveOffice;
    }

    public void setContractReceiveOffice(String contractReceiveOffice) {
        this.contractReceiveOffice = contractReceiveOffice;
    }

    public Date getContractReceiveTime() {
        return contractReceiveTime;
    }

    public void setContractReceiveTime(Date contractReceiveTime) {
        this.contractReceiveTime = contractReceiveTime;
    }

    public String getContractReceiveUser() {
        return contractReceiveUser;
    }

    public void setContractReceiveUser(String contractReceiveUser) {
        this.contractReceiveUser = contractReceiveUser;
    }

    public Date getContractRecycleTime() {
        return contractRecycleTime;
    }

    public void setContractRecycleTime(Date contractRecycleTime) {
        this.contractRecycleTime = contractRecycleTime;
    }

    public String getContractRecycleUserId() {
        return contractRecycleUserId;
    }

    public void setContractRecycleUserId(String contractRecycleUserId) {
        this.contractRecycleUserId = contractRecycleUserId;
    }

    public Date getContractRegisterTime() {
        return contractRegisterTime;
    }

    public void setContractRegisterTime(Date contractRegisterTime) {
        this.contractRegisterTime = contractRegisterTime;
    }

    public String getContractRegisterUserId() {
        return contractRegisterUserId;
    }

    public void setContractRegisterUserId(String contractRegisterUserId) {
        this.contractRegisterUserId = contractRegisterUserId;
    }

    public String getContractReturnUser() {
        return contractReturnUser;
    }

    public void setContractReturnUser(String contractReturnUser) {
        this.contractReturnUser = contractReturnUser;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getCreateUnitId() {
        return createUnitId;
    }

    public void setCreateUnitId(String createUnitId) {
        this.createUnitId = createUnitId;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getDepositBillNo() {
        return depositBillNo;
    }

    public void setDepositBillNo(String depositBillNo) {
        this.depositBillNo = depositBillNo;
    }

    public String getDocumentAddInfo() {
        return documentAddInfo;
    }

    public void setDocumentAddInfo(String documentAddInfo) {
        this.documentAddInfo = documentAddInfo;
    }

    public Integer getDocumentCnt() {
        return documentCnt;
    }

    public void setDocumentCnt(Integer documentCnt) {
        this.documentCnt = documentCnt;
    }

    public String getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }


    public String getLatefeePayment() {
        return latefeePayment;
    }

    public void setLatefeePayment(String latefeePayment) {
        this.latefeePayment = latefeePayment;
    }

    public String getLatefeePaymentType() {
        return latefeePaymentType;
    }

    public void setLatefeePaymentType(String latefeePaymentType) {
        this.latefeePaymentType = latefeePaymentType;
    }

    public Integer getLatefeePaymentValue1() {
        return latefeePaymentValue1;
    }

    public void setLatefeePaymentValue1(Integer latefeePaymentValue1) {
        this.latefeePaymentValue1 = latefeePaymentValue1;
    }

    public Integer getLatefeePaymentValue2() {
        return latefeePaymentValue2;
    }

    public void setLatefeePaymentValue2(Integer latefeePaymentValue2) {
        this.latefeePaymentValue2 = latefeePaymentValue2;
    }

    public String getLesseeId() {
        return lesseeId;
    }

    public void setLesseeId(String lesseeId) {
        this.lesseeId = lesseeId;
    }

    public BigDecimal getRentPerMonth() {
        return rentPerMonth;
    }

    public void setRentPerMonth(BigDecimal rentPerMonth) {
        this.rentPerMonth = rentPerMonth;
    }

    public String getRentalPaymentType() {
        return rentalPaymentType;
    }

    public void setRentalPaymentType(String rentalPaymentType) {
        this.rentalPaymentType = rentalPaymentType;
    }

    public String getRentalPaytoDate() {
        return rentalPaytoDate;
    }

    public void setRentalPaytoDate(String rentalPaytoDate) {
        this.rentalPaytoDate = rentalPaytoDate;
    }

    public String getRentalSettingType() {
        return rentalSettingType;
    }

    public void setRentalSettingType(String rentalSettingType) {
        this.rentalSettingType = rentalSettingType;
    }

    public BigDecimal getRentalStandAmount() {
        return rentalStandAmount;
    }

    public void setRentalStandAmount(BigDecimal rentalStandAmount) {
        this.rentalStandAmount = rentalStandAmount;
    }

    public BigDecimal getRentArea() {
        return rentArea;
    }

    public void setRentArea(BigDecimal rentArea) {
        this.rentArea = rentArea;
    }

    public String getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(String rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public String getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(String rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public String getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(String serviceNum) {
        this.serviceNum = serviceNum;
    }
}