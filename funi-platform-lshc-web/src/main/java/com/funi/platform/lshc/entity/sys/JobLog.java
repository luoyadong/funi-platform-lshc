package com.funi.platform.lshc.entity.sys;

import com.funi.platform.lshc.entity.BaseEntity;


public class JobLog extends BaseEntity {


    /**
     * 工作主表id
     */
    private String jobAcceptId;

    /**
     * 审核人
     */
    private String auditName;

    /**
     * 审核人姓名
     */
    private String auditId;

    /**
     * 结论
     */
    private String jobResult;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 意见
     */
    private String jobOpinion;

    /**
     * 操作人单位
     */
    private String unitName;

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getJobAcceptId() {
        return jobAcceptId;
    }

    public void setJobAcceptId(String jobAcceptId) {
        this.jobAcceptId = jobAcceptId;
    }

    public String getJobOpinion() {
        return jobOpinion;
    }

    public void setJobOpinion(String jobOpinion) {
        this.jobOpinion = jobOpinion;
    }

    public String getJobResult() {
        return jobResult;
    }

    public void setJobResult(String jobResult) {
        this.jobResult = jobResult;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}