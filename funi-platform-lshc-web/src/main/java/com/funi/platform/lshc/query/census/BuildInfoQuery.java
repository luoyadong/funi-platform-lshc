package com.funi.platform.lshc.query.census;

import com.funi.framework.biz.eic.bo.Dict;
import com.funi.platform.lshc.query.BaseQuery;

import java.util.List;

/**
 * Created by sam on 2019/6/14.12:10 AM
 */
public class BuildInfoQuery extends BaseQuery {
    /** 区县 */
    private String region;
    /** 街道 */
    private String street;
    /** 项目名称（小区名称） */
    private String projectName;
    /** 楼栋地图编号 */
    private String mapCode;
    /** 楼栋实际地址 */
    private String address;
    /** 用于工作流数据权限控制*/
    private List<Dict> owner;
    /** 工作流数据权限控制类型，默认使用角色*/
    private String ownerType = "R";
    /** 查询类型*/
    private String queryType;
    /** 楼栋名称*/
    private String buildName;
    /** 社区名称*/
    private String communityName;
    /** 证件号码 */
    private String idNo;
    /** 房屋编号*/
    private String houseId;

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

    public List<Dict> getOwner() {
        return owner;
    }

    public void setOwner(List<Dict> owner) {
        this.owner = owner;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
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

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }
}
