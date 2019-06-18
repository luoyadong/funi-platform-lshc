package com.funi.platform.lshc.query.census;

import com.funi.framework.biz.eic.bo.Dict;
import com.funi.platform.lshc.query.BaseQuery;

import java.util.List;

/**
 * Created by sam on 2019/6/14.9:38 AM
 */
public class RegiInfoQuery extends BaseQuery {
    /** 审批状态*/
    private String houseStatus;
    /** 楼栋ID*/
    private String buildId;
    /** 查询类型*/
    private String queryType;
    /** 用于工作流数据权限控制*/
    private List<Dict> owner;
    /** 工作流数据权限控制类型，默认使用角色*/
    private String ownerType = "R";

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
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
}
