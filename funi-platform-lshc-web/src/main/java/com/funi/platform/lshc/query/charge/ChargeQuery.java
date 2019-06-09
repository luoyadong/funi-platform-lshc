package com.funi.platform.lshc.query.charge;

import com.funi.platform.lshc.query.GhouseBaseQuery;

/**
 * 分页查询收费列表
 * Created by sam on 2018/11/12.9:28 AM
 */
public class ChargeQuery extends GhouseBaseQuery {
    /**
     * 承租人
     */
    private String lesseeName;
    /**
     * 身份证号码
     */
    private String uniqueId;
    /**
     * 房屋户号
     */
    private String houseNo;
    /**
     * 单位名称
     */
    private String organizationName;

    public String getLesseeName() {
        return lesseeName;
    }

    public void setLesseeName(String lesseeName) {
        this.lesseeName = lesseeName;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
