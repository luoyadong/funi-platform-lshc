package com.funi.platform.lshc.entity.charge;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.entity.BaseEntity;
import com.funi.platform.lshc.support.GhouseConstants;

import java.io.Serializable;
import java.util.Date;

/**
 * 票据管理表
 * @author sam
 * @date 2018-11-12 10:11:25
 */
public class Bill extends BaseEntity implements Serializable {
    /** 票据编号 */
    private String billNo;
    /** 票据编号前缀 */
    private String billNoHeader = GhouseConstants.BIZ_PREFIX_RECEIPT;
    /** 票据子番号 */
    private String billNoSeq;
    /** 票据打印模板ID */
    private String billTempletId;
    /** 模板名称，用于调用打印服务*/
    private String billTempletName;
    /** 领票人 */
    private String billReceiveUser;
    /** 领票日期 */
    private String billReceiveDate;
    /** 票据所有人 */
    private String billUserId;
    /** 票据状态，0：有效，1：作废 */
    private String billStatus = "0";
    /** 票据处理状态 */
    private String billHandleStatus;
    /** 票据收费状态 */
    private String billChargeStatus;
    /** 出票方式 */
    private String billType;
    /** 开票人 */
    private String billOutUserId;
    /** 开票时间 */
    private Date billOutTime;
    /** 打印人 */
    private String billPrintUserId;
    /** 打印时间 */
    private Date billPrintTime;
    /** 撤销收费人 */
    private String revokeUserId;
    /** 撤销时间 */
    private String revokeTime;
    /** 作废人 */
    private String billInvalidUserId;
    /** 作废原因 */
    private String billInvalidReason;
    /** 作废时间 */
    private Date billInvalidTime;
    /** 备注 */
    private String billRemark;

    public Bill(CurrentUser userInfo, String billNoSeq) {
        String username = userInfo.getName();
        Date date = new Date();
        this.billNoSeq = billNoSeq;
        this.billOutUserId = username;
        this.billPrintUserId = username;
        this.billOutTime = date;
        this.billPrintTime = date;
    }

    public Bill() {
    }

    public String getBillTempletName() {
        return billTempletName;
    }

    public void setBillTempletName(String billTempletName) {
        this.billTempletName = billTempletName;
    }

    private static final long serialVersionUID = 1L;

    public String getBillNo() {
        return getBillNoHeader() + billNoSeq;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillNoHeader() {
        return billNoHeader;
    }

    public void setBillNoHeader(String billNoHeader) {
        this.billNoHeader = billNoHeader;
    }

    public String getBillNoSeq() {
        return billNoSeq;
    }

    public void setBillNoSeq(String billNoSeq) {
        this.billNoSeq = billNoSeq;
    }

    public String getBillTempletId() {
        return billTempletId;
    }

    public void setBillTempletId(String billTempletId) {
        this.billTempletId = billTempletId;
    }

    public String getBillReceiveUser() {
        return billReceiveUser;
    }

    public void setBillReceiveUser(String billReceiveUser) {
        this.billReceiveUser = billReceiveUser;
    }

    public String getBillReceiveDate() {
        return billReceiveDate;
    }

    public void setBillReceiveDate(String billReceiveDate) {
        this.billReceiveDate = billReceiveDate;
    }

    public String getBillUserId() {
        return billUserId;
    }

    public void setBillUserId(String billUserId) {
        this.billUserId = billUserId;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getBillHandleStatus() {
        return billHandleStatus;
    }

    public void setBillHandleStatus(String billHandleStatus) {
        this.billHandleStatus = billHandleStatus;
    }

    public String getBillChargeStatus() {
        return billChargeStatus;
    }

    public void setBillChargeStatus(String billChargeStatus) {
        this.billChargeStatus = billChargeStatus;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillOutUserId() {
        return billOutUserId;
    }

    public void setBillOutUserId(String billOutUserId) {
        this.billOutUserId = billOutUserId;
    }

    public Date getBillOutTime() {
        return billOutTime;
    }

    public void setBillOutTime(Date billOutTime) {
        this.billOutTime = billOutTime;
    }

    public String getBillPrintUserId() {
        return billPrintUserId;
    }

    public void setBillPrintUserId(String billPrintUserId) {
        this.billPrintUserId = billPrintUserId;
    }

    public Date getBillPrintTime() {
        return billPrintTime;
    }

    public void setBillPrintTime(Date billPrintTime) {
        this.billPrintTime = billPrintTime;
    }

    public String getRevokeUserId() {
        return revokeUserId;
    }

    public void setRevokeUserId(String revokeUserId) {
        this.revokeUserId = revokeUserId;
    }

    public String getRevokeTime() {
        return revokeTime;
    }

    public void setRevokeTime(String revokeTime) {
        this.revokeTime = revokeTime;
    }

    public String getBillInvalidUserId() {
        return billInvalidUserId;
    }

    public void setBillInvalidUserId(String billInvalidUserId) {
        this.billInvalidUserId = billInvalidUserId;
    }

    public String getBillInvalidReason() {
        return billInvalidReason;
    }

    public void setBillInvalidReason(String billInvalidReason) {
        this.billInvalidReason = billInvalidReason;
    }

    public Date getBillInvalidTime() {
        return billInvalidTime;
    }

    public void setBillInvalidTime(Date billInvalidTime) {
        this.billInvalidTime = billInvalidTime;
    }

    public String getBillRemark() {
        return billRemark;
    }

    public void setBillRemark(String billRemark) {
        this.billRemark = billRemark;
    }
}