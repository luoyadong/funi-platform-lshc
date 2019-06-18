package com.funi.platform.lshc.query.census;

import com.funi.framework.biz.eic.bo.Dict;
import com.funi.platform.lshc.query.BaseQuery;

import java.util.List;

/**
 * Created by sam on 2019/6/18.11:28 AM
 */
public class BuildRegiQuery extends BaseQuery {
    List<String> ids;
    /** 用于工作流数据权限控制*/
    private List<Dict> owner;
    /** 工作流数据权限控制类型，默认使用角色*/
    private String ownerType = "R";
    /** 查询类型*/
    private String queryType;

    public BuildRegiQuery() {
    }

    public BuildRegiQuery(List<String> ids, String queryType) {
        this.ids = ids;
        this.queryType = queryType;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
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
}
