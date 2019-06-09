package com.funi.platform.lshc.query.charge;

import com.funi.platform.lshc.query.GhouseBaseQuery;

/**
 * Created by sam on 2018/11/16.8:48 PM
 */
public class ChargeHistoryQuery extends GhouseBaseQuery {
    /** 合同编号 */
    private String contractNo;
    /** 承租方，个人：lesseeName，集体：organizationName */
    private String lesseeName;
    /** 房号*/
    private String houseNo;
    /** 收费开始时间 */
    private String chargeStartTime;
    /** 收费结束时间 */
    private String chargeEndTime;
    /** 收款人 */
    private String chargeUserId;
    /** 撤销开始日期 */
    private String backStartDate;
    /** 撤销结束日期 */
    private String backEndDate;
    /** 退费人 */
    private String backOpeUserId;
    /** 退费标识,0:已收费，1：已撤销 */
    private String backFlag;

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getLesseeName() {
        return lesseeName;
    }

    public void setLesseeName(String lesseeName) {
        this.lesseeName = lesseeName;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
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

    public String getBackStartDate() {
        return backStartDate;
    }

    public void setBackStartDate(String backStartDate) {
        this.backStartDate = backStartDate;
    }

    public String getBackEndDate() {
        return backEndDate;
    }

    public void setBackEndDate(String backEndDate) {
        this.backEndDate = backEndDate;
    }

    public String getBackOpeUserId() {
        return backOpeUserId;
    }

    public void setBackOpeUserId(String backOpeUserId) {
        this.backOpeUserId = backOpeUserId;
    }

    public String getBackFlag() {
        return backFlag;
    }

    public void setBackFlag(String backFlag) {
        this.backFlag = backFlag;
    }
}
