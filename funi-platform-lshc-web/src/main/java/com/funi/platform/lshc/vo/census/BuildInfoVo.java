package com.funi.platform.lshc.vo.census;

import com.funi.platform.lshc.entity.census.BuildInfo;

/**
 * Created by sam on 2019/6/14.12:13 AM
 */
public class BuildInfoVo extends BuildInfo {
    /** 房屋套数*/
    private Integer houseCount;

    public Integer getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(Integer houseCount) {
        this.houseCount = houseCount;
    }
}
