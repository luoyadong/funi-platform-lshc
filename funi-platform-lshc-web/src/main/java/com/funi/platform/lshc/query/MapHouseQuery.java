package com.funi.platform.lshc.query;

import com.funi.framework.biz.query.PageableQuery;

/**
 * 公房分布查询对象
 * @author luoyadong
 */
public class MapHouseQuery extends PageableQuery {

    //小区Id
    private String cId;

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }
}
