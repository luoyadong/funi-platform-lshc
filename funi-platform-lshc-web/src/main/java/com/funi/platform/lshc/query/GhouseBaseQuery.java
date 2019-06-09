package com.funi.platform.lshc.query;

import com.funi.framework.biz.eic.bo.Dict;
import com.funi.framework.biz.query.PageableQuery;

import java.util.List;

/**
 * @author 3
 */
public class GhouseBaseQuery extends PageableQuery {

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
