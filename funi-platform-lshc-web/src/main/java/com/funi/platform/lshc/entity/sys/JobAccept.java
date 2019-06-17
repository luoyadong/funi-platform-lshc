package com.funi.platform.lshc.entity.sys;

import com.funi.platform.lshc.entity.SuperEntity;

import java.util.Date;

public class JobAccept extends SuperEntity {

    /**
     * 受理号
     */
    private String serviceNum;

    /**
     * 工作类型
     */
    private String typeName;

    /**
     * 状态
     */
    private int status;

    /**
     * 当前状态
     */
    private String curStatus;

    /**
     * 受理时间
     */
    private Date acceptTime;

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getCurStatus() {
        return curStatus;
    }

    public void setCurStatus(String curStatus) {
        this.curStatus = curStatus;
    }

    public String getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(String serviceNum) {
        this.serviceNum = serviceNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}