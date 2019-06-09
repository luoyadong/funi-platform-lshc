package com.funi.platform.lshc.dto;

/**
 * @author 3
 */
public class JobDto {

    private String id;
    private String businessName;
    private String nodeName;
    private String currentConclusion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCurrentConclusion() {
        return currentConclusion;
    }

    public void setCurrentConclusion(String currentConclusion) {
        this.currentConclusion = currentConclusion;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
