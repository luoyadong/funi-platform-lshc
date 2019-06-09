package com.funi.platform.lshc.query;

import com.funi.framework.biz.query.PageableQuery;

/**
 * @author yadong
 */
public class BillTempQuery extends PageableQuery {

    //关键字
    private String keyword;
    //票据类型
    private String billType;
    //票据属性
    private String billProperty;

    @Override
    public String getKeyword() {
        return keyword;
    }

    @Override
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillProperty() {
        return billProperty;
    }

    public void setBillProperty(String billProperty) {
        this.billProperty = billProperty;
    }
}
