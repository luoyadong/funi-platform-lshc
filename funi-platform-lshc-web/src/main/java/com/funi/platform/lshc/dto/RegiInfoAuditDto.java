package com.funi.platform.lshc.dto;

import java.util.List;

/**
 * Created by sam on 2019/6/21.3:43 PM
 */
public class RegiInfoAuditDto {
    /** 普查信息ID集合*/
    private List<String> ids;
    /** 审批结果，0：不通过，1：通过*/
    private String result;
    /** 审核描述*/
    private String desc;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
