package com.funi.platform.lshc.query.census;

import com.funi.platform.lshc.query.BaseQuery;

/**
 * Created by sam on 2019/6/14.10:03 AM
 */
public class ApproveLogQuery extends BaseQuery {
    /** 房屋编号*/
    private String houseId;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }
}
