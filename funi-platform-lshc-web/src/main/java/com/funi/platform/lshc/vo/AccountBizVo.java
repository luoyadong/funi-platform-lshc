package com.funi.platform.lshc.vo;

import com.funi.platform.lshc.entity.AccountBizEntity;

/**
 * 用于业务处理等的对象
 * @author
 */
public class AccountBizVo extends AccountBizEntity {
    /**
     * 监管编号
     */
    private String supAccountCode;
    /**
     * 预售资金监管账号
     */
    private String accountNum;

    /**
     * 项目分期名称
     */
    private String periodName;
    /**
     * 监管范围
     */
    private String supRange;
    //分期业务键号
    private String periodCode;
    /**
     * 监管账户id
     */
    private String accountId;

    private String bizId;
    //开发企业名字
    private String companyName;
    //区域
    private String areaName;
    //业务类型名称
    private String bizTypeName;
    //变更提示
    private String bizChangeMessage;
    //银行机构编码
    private String bankOrg;

    public String getBankOrg() {
        return bankOrg;
    }

    public void setBankOrg(String bankOrg) {
        this.bankOrg = bankOrg;
    }

    public String getBizChangeMessage() {
        return bizChangeMessage;
    }

    public void setBizChangeMessage(String bizChangeMessage) {
        this.bizChangeMessage = bizChangeMessage;
    }

    public String getBizTypeName() {
        return bizTypeName;
    }

    public void setBizTypeName(String bizTypeName) {
        this.bizTypeName = bizTypeName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(String periodCode) {
        this.periodCode = periodCode;
    }

    public String getSupAccountCode() {
        return supAccountCode;
    }

    public void setSupAccountCode(String supAccountCode) {
        this.supAccountCode = supAccountCode;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public String getSupRange() {
        return supRange;
    }

    public void setSupRange(String supRange) {
        this.supRange = supRange;
    }
}
