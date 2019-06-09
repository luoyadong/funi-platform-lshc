package com.funi.platform.lshc.query.charge;

import com.funi.platform.lshc.query.GhouseBaseQuery;
import com.funi.platform.lshc.utils.DateUtils;

/**
 * 欠费催缴的查询对象
 * Created by sam on 2018/11/17.6:13 PM
 */
public class ArrearageQuery extends GhouseBaseQuery {
    /**
     * 创建者机构编码
     */
    private String createUnitId;

    /** 户号、地址、承租人*/
    private String keywords;
    /** 统计欠租的起始日期 */
    private String arrearageStartDate = DateUtils.getNow();

    public String getArrearageStartDate() {
        return arrearageStartDate;
    }

    public void setArrearageStartDate(String arrearageStartDate) {
        this.arrearageStartDate = arrearageStartDate;
    }

    public String getCreateUnitId() {
        return createUnitId;
    }

    public void setCreateUnitId(String createUnitId) {
        this.createUnitId = createUnitId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
