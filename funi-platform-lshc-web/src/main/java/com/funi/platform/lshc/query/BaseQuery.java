package com.funi.platform.lshc.query;

import com.funi.framework.biz.eic.bo.Dict;
import com.funi.framework.biz.query.PageableQuery;

import java.util.List;

/**
 * Created by sam on 2019/6/14.12:09 AM
 */
public class BaseQuery extends PageableQuery {
    private List<Dict> authorityList;
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
