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
    /** 用于工作流数据权限控制*/
    private List<Dict> owner;
    /** 工作流数据权限控制类型，默认使用角色*/
    private String ownerType = "R";

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
