package com.funi.platform.lshc.query.charge;

import com.funi.platform.lshc.query.GhouseBaseQuery;

/**
 * 保证金查询对象
 * Created by sam on 2018/11/17.8:41 PM
 */
public class DepositQuery extends GhouseBaseQuery {
    /** 地址 */
    private String addressDetail;
    /** 承租人 */
    private String lesseeName;
    /** 合同编号 */
    private String contractNo;
    /** 创建者机构编码 */
    private String createUnitId;
    /** 房屋使用性质，物业类型*/
    private String hourseUseType;
    /** 收费/退费状态,null：未收，1：已收，2：已退 */
    private String depositStatus;
    /** 收费人 */
    private String chargeUserId;
    /** 收费开始时间 */
    private String chargeStartTime;
    /** 收费结束时间 */
    private String chargeEndTime;
    /** 退费人 */
    private String refundUserId;
    /** 退费开始时间 */
    private String refundStartTime;
    /** 退费开始时间 */
    /** 退费结束时间 */
    private String refundEndTime;

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getLesseeName() {
        return lesseeName;
    }

    public void setLesseeName(String lesseeName) {
        this.lesseeName = lesseeName;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getCreateUnitId() {
        return createUnitId;
    }

    public void setCreateUnitId(String createUnitId) {
        this.createUnitId = createUnitId;
    }

    public String getHourseUseType() {
        return hourseUseType;
    }

    public void setHourseUseType(String hourseUseType) {
        this.hourseUseType = hourseUseType;
    }

    public String getDepositStatus() {
        return depositStatus;
    }

    public void setDepositStatus(String depositStatus) {
        this.depositStatus = depositStatus;
    }

    public String getChargeUserId() {
        return chargeUserId;
    }

    public void setChargeUserId(String chargeUserId) {
        this.chargeUserId = chargeUserId;
    }

    public String getChargeStartTime() {
        return chargeStartTime;
    }

    public void setChargeStartTime(String chargeStartTime) {
        this.chargeStartTime = chargeStartTime;
    }

    public String getChargeEndTime() {
        return chargeEndTime;
    }

    public void setChargeEndTime(String chargeEndTime) {
        this.chargeEndTime = chargeEndTime;
    }

    public String getRefundUserId() {
        return refundUserId;
    }

    public void setRefundUserId(String refundUserId) {
        this.refundUserId = refundUserId;
    }

    public String getRefundStartTime() {
        return refundStartTime;
    }

    public void setRefundStartTime(String refundStartTime) {
        this.refundStartTime = refundStartTime;
    }

    public String getRefundEndTime() {
        return refundEndTime;
    }

    public void setRefundEndTime(String refundEndTime) {
        this.refundEndTime = refundEndTime;
    }
}
