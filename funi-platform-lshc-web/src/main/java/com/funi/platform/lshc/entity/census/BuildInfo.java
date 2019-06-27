package com.funi.platform.lshc.entity.census;

import com.funi.platform.lshc.entity.SuperEntity;
import java.io.Serializable;

/**
 * 楼栋信息
 * @author sam
 * @date 2019-06-14 17:37:19
 */
public class BuildInfo extends SuperEntity implements Serializable {
    /** 序号 */
    private Integer serialNo;
    /** 区县 */
    private String region;
    /** 街道 */
    private String street;
    /** 项目名称 */
    private String projectName;
    /** 楼栋地图编号 */
    private String mapCode;
    /** 实际地址 */
    private String address;
    /** 备用 */
    private String common;
    /** 楼栋名称*/
    private String buildName;
    /** 社区名称*/
    private String communityName;

    private static final long serialVersionUID = 1L;

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}