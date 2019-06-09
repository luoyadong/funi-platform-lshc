package com.funi.platform.lshc.query.address;

import com.funi.framework.biz.query.PageableQuery;

/**
 * Created by sam on 2018/11/7.9:30 PM
 */
public class AddressChangeQuery extends PageableQuery {
    /** 地址的主键ID*/
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
