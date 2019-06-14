package com.funi.platform.lshc.query.census;

import com.funi.platform.lshc.query.BaseQuery;

/**
 * Created by sam on 2019/6/14.12:10 AM
 */
public class BuildInfoQuery extends BaseQuery {
    /** 区县 */
    private String region;

    /** 街道 */
    private String street;

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
}
