package com.funi.platform.lshc.query;

import com.funi.framework.biz.eic.bo.Dict;
import com.funi.framework.biz.query.PageableQuery;

import java.util.List;

/**
 * Created by sam on 2019/6/14.12:09 AM
 */
public class BaseQuery extends PageableQuery {
    /** 权限范围内的机构集合*/
    private List<Dict> authorityList;
    /** 预留字段用于区分管理员和普通账号*/
    private String userId;


    public List<Dict> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<Dict> authorityList) {
        this.authorityList = authorityList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
