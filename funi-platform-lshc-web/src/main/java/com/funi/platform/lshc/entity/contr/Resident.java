package com.funi.platform.lshc.entity.contr;

import com.funi.platform.lshc.entity.BaseEntity;

import java.util.Date;

/**
 * 住户
 * @author 3
 */
public class Resident extends BaseEntity {

    /**
     * 住户姓名
     */
    private String residentName;

    /**
     * 住户性别
     */
    private String residentSex;

    /**
     * 身份证号码
     */
    private String residentIdCardNo;

    /**
     * 联系电话
     */
    private String residentTelNo;

    /**
     * 备注
     */
    private String residentComment;

    /**
     * 合同ID
     */
    private String contrId;

    /**
     * 入住区分
     */
    private String liveFlg;

    /**
     * 入住日期
     */
    private String checkInDate;

    /**
     * 退住日期
     */
    private String checkOutDate;

    /**
     * 经办人ID
     */
    private String operatorUserId;

    /**
     * 经办时间
     */
    private Date operatorTime;

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getContrId() {
        return contrId;
    }

    public void setContrId(String contrId) {
        this.contrId = contrId;
    }

    public String getLiveFlg() {
        return liveFlg;
    }

    public void setLiveFlg(String liveFlg) {
        this.liveFlg = liveFlg;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(String operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    public String getResidentComment() {
        return residentComment;
    }

    public void setResidentComment(String residentComment) {
        this.residentComment = residentComment;
    }

    public String getResidentIdCardNo() {
        return residentIdCardNo;
    }

    public void setResidentIdCardNo(String residentIdCardNo) {
        this.residentIdCardNo = residentIdCardNo;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public String getResidentSex() {
        return residentSex;
    }

    public void setResidentSex(String residentSex) {
        this.residentSex = residentSex;
    }

    public String getResidentTelNo() {
        return residentTelNo;
    }

    public void setResidentTelNo(String residentTelNo) {
        this.residentTelNo = residentTelNo;
    }
}