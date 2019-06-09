package com.funi.platform.lshc.query.address;

import com.funi.platform.lshc.query.GhouseBaseQuery;

/**
 * 小区查询对象
 * Created by sam on 2018/11/6.11:08 PM
 */
public class AddressQuery extends GhouseBaseQuery {
    private String regionCode;
    /** 小区名称或者小区地址*/
    private String keywords;
    /** 是否有变更*/
    private Integer isChange;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getIsChange() {
        return isChange;
    }

    public void setIsChange(Integer isChange) {
        this.isChange = isChange;
    }
}
