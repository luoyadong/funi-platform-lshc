package com.funi.platform.lshc.vo;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by sam on 2018/11/16.8:55 PM
 */
public class ChargeHistoryVo {
    /** 历史记录ID */
    private String historyId;
    /** 合同ID */
    private String contractId;
    /** 合同编号 */
    private String contractNo;
    /** 房屋套数 */
    private Integer houseCount;
    /** 单位名称*/
    private String organizationName;
    /** 承租人*/
    private String lesseeName;
    /** 联系电话，个人：personalTel，集体：organizationTel */
    private String telephone;
    /** 收费金额 */
    private BigDecimal actualAmount;
    /** 租金开始年月日 */
    private String rentStartDate;
    /** 租金终止年月日 */
    private String rentEndDate;
    /** 收费时间 */
    private String chargeTime;
    /** 收款人 */
    private String chargeUserId;
    /** 退费标识,0:已收费，1：已撤销 */
    private String backFlag;
    /** 退费状态描述 */
    private String backFlagDesc;
    /** 退费操作人 */
    private String backOpeUserId;
    /** 退费日期 */
    private String backDate;
    /** 退费原因 */
    private String backReason;
    private String operateDesc;
    /** 票据模板名称，用于打印*/
    private String billTempletName;
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

    public String getBizCreateOrgCode() {
        return bizCreateOrgCode;
    }

    public void setBizCreateOrgCode(String bizCreateOrgCode) {
        this.bizCreateOrgCode = bizCreateOrgCode;
    }

    public String getBillTempletName() {
        return billTempletName;
    }

    public void setBillTempletName(String billTempletName) {
        this.billTempletName = billTempletName;
    }

    public String getOperateDesc() {
        return operateDesc;
    }

    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc;
    }

    public String getBackFlagDesc() {
        String backFlag = getBackFlag();
        if(StringUtils.isNotBlank(backFlag)) {
            if("0".equals(backFlag)) {
                return "已收费";
            } else {
                return "已撤销";
            }
        }
        return backFlagDesc;
    }

    public void setBackFlagDesc(String backFlagDesc) {
        this.backFlagDesc = backFlagDesc;
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
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

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
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

    public String getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String getChargeUserId() {
        return chargeUserId;
    }

    public void setChargeUserId(String chargeUserId) {
        this.chargeUserId = chargeUserId;
    }

    public String getBackFlag() {
        return backFlag;
    }

    public void setBackFlag(String backFlag) {
        this.backFlag = backFlag;
    }

    public String getBackOpeUserId() {
        return backOpeUserId;
    }

    public void setBackOpeUserId(String backOpeUserId) {
        this.backOpeUserId = backOpeUserId;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public String getBackReason() {
        return backReason;
    }

    public void setBackReason(String backReason) {
        this.backReason = backReason;
    }
}
