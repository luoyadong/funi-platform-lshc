package com.funi.platform.lshc.query;

import com.funi.framework.biz.query.PageableQuery;

/**
 * @author yadong
 */
public class ContrTempQuery extends PageableQuery {

    //关键字
    private String keyword;

    /**
     * 是否启用
     */
    private String validFlg;

    public String getValidFlg() {
        return validFlg;
    }

    public void setValidFlg(String validFlg) {
        this.validFlg = validFlg;
    }

    @Override
    public String getKeyword() {
        return keyword;
    }

    @Override
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
