package com.funi.platform.lshc.entity.house;

import com.funi.platform.lshc.entity.BaseEntity;

import java.io.Serializable;

/**
 * 区域表
 * @author sam
 * @date 2018-11-06 22:20:00
 */
public class Region extends BaseEntity implements Serializable {
    /** 行政编码 */
    private String regionCode;

    /** 区域名称 */
    private String regionName;

    private static final long serialVersionUID = 1L;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}