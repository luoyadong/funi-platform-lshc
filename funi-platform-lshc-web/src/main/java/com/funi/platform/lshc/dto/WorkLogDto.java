package com.funi.platform.lshc.dto;

/**
 * @author 3
 */
public class WorkLogDto {

    private String auditName;
    private String nodeName;
    private String jobOpinion;
    private String createDateStr;
    private String conclusion;
    private String unitName;

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getJobOpinion() {
        return jobOpinion;
    }

    public void setJobOpinion(String jobOpinion) {
        this.jobOpinion = jobOpinion;
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
