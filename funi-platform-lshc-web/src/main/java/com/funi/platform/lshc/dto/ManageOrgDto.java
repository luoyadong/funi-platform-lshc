package com.funi.platform.lshc.dto;

/**
 * 管理机构
 * Created by yadong on 2018/12/1.9:00 PM
 */
public class ManageOrgDto {
    /**
     * 管理机构ID
     */
    private String id;

    /**
     * 管理机构编码
     */
    private String orgCode;
    /**
     * 管理机构名称
     */
    private String orgName;

    /**
     * 管理机构类型码值
     */
    private String orgType;
    /**
     * 区域编码
     */
    private String regionCode;
    /**
     * 区域名称
     */
    private String regionName;
    /**
     * 管理机构类型名称
     */
    private String typeName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
