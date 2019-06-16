package com.funi.platform.lshc.query.census;

import com.funi.platform.lshc.query.BaseQuery;

/**
 * Created by sam on 2019/6/14.9:38 AM
 */
public class RegiInfoQuery extends BaseQuery {
    /** 审批状态*/
    private String status;
    /** */
    private String mapCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }
}
