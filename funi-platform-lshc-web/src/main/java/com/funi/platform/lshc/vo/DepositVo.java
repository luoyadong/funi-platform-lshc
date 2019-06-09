package com.funi.platform.lshc.vo;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sam on 2018/11/17.8:52 PM
 */
public class DepositVo {
    /** 合同ID */
    private String contractId;
    /** 合同编号 */
    @Excel(name="合同编号")
    private String contractNo;
    /** 房屋套数 */
    @Excel(name="房屋套数")
    private Integer houseCount;
    /** 保证金金额*/
    @Excel(name="保证金（元）")
    private BigDecimal depositAmount;
    /** 单位名称*/
    @Excel(name="单位名称")
    private String organizationName;
    /** 承租人*/
    @Excel(name="承租人")
    private String lesseeName;
    /** 联系电话，个人：personalTel，集体：organizationTel */
    @Excel(name="联系电话")
    private String telephone;
    /** 收费/退费状态,null：未收，1：已收，2：已退 */
    private String depositStatus;
    /** 收费状态描述*/
    @Excel(name="收费状态")
    private String depositStatusDesc;
    /** 收费人 */
    @Excel(name="收费人")
    private String chargeUserId;
    /** 收费时间 */
    @Excel(name="收费时间", exportFormat="yyyy-mm-dd HH:mm:ss")
    private Date chargeTime;
    /** 退费人 */
    @Excel(name="退费人")
    private String refundUserId;
    /** 退费时间 */
    @Excel(name="退费时间", exportFormat="yyyy-mm-dd HH:mm:ss")
    private Date refundTime;
    @Excel(name="退费原因")
    /** 退费原因 */
    private String refundReason;
    /** 房屋地址集合 */
    private List<ChargeAddressVo> chargeAddressVoList;

    private String depositOperateDesc;

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

    public String getDepositOperateDesc() {
       return depositOperateDesc;
    }

    public void setDepositOperateDesc(String depositOperateDesc) {
        this.depositOperateDesc = depositOperateDesc;
    }

    public String getDepositStatusDesc() {
        String depositStatus = getDepositStatus();
        if(StringUtils.isBlank(depositStatus)) {
            return "未收";
        } else if("1".equals(depositStatus)) {
            return "已收";
        } else if("2".equals(depositStatus)) {
            return "已退";
        }
        return depositStatusDesc;
    }

    public void setDepositStatusDesc(String depositStatusDesc) {
        this.depositStatusDesc = depositStatusDesc;
    }

    public String getDepositStatus() {
        return depositStatus;
    }

    public void setDepositStatus(String depositStatus) {
        this.depositStatus = depositStatus;
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

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
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

    public String getChargeUserId() {
        return chargeUserId;
    }

    public void setChargeUserId(String chargeUserId) {
        this.chargeUserId = chargeUserId;
    }

    public Date getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(Date chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String getRefundUserId() {
        return refundUserId;
    }

    public void setRefundUserId(String refundUserId) {
        this.refundUserId = refundUserId;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }
}
