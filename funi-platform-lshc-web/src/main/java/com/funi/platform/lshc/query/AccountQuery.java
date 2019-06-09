package com.funi.platform.lshc.query;

import com.funi.framework.biz.eic.bo.Dict;
import com.funi.framework.biz.query.PageableQuery;

import java.util.List;

/**
 * 账号查询,支持分页
 * @author
 */
public class AccountQuery extends PageableQuery {
    /**
     * 状态
     */
    private String status;
    /**
     * 业务键号
     */
    private String bizNo;
    /**
     * 监管编号
     */
    private String supCode;

    /**
     * 分期名称
     */
    private String periodName;
    /**
     * 预售监管账号
     */
    private String accountNum;
    //机构编码
    private List<Dict> bankId;
    //角色集合
    private List<Dict> roleIds;
    //开发企业名称
    private String companyName;

    //用户角色绑定区域集合
    private List<Dict> dataSources;
    //机构编码
    private String orgCode;
    //用户id
    private String userId;

    public List<Dict> getBankId() {
        return bankId;
    }

    public void setBankId(List<Dict> bankId) {
        this.bankId = bankId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Dict> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Dict> roleIds) {
        this.roleIds = roleIds;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getSupCode() {
        return supCode;
    }

    public void setSupCode(String supCode) {
        this.supCode = supCode;
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

    public List<Dict> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<Dict> dataSources) {
        this.dataSources = dataSources;
    }
}
