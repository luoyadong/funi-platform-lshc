package com.funi.platform.lshc.query;

import com.funi.framework.biz.eic.bo.Dict;

import java.util.List;

public class RepairQuery extends GhouseBaseQuery {

    //
    private List<Dict> owner;
    //
    private String ownerType="R";

    private String manaageOrg;

    private String requestTimeStart;

    private String requestTimeEnd;

    private String searchContent;

    private String  hourseUseType;

    public String getHourseUseType() {
        return hourseUseType;
    }

    public void setHourseUseType(String hourseUseType) {
        this.hourseUseType = hourseUseType;
    }

    public String getManaageOrg() {
        return manaageOrg;
    }

    public void setManaageOrg(String manaageOrg) {
        this.manaageOrg = manaageOrg;
    }

    public String getRequestTimeStart() {
        return requestTimeStart;
    }

    public void setRequestTimeStart(String requestTimeStart) {
        this.requestTimeStart = requestTimeStart;
    }

    public String getRequestTimeEnd() {
        return requestTimeEnd;
    }

    public void setRequestTimeEnd(String requestTimeEnd) {
        this.requestTimeEnd = requestTimeEnd;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public List<Dict> getOwner() {
        return owner;
    }

    public void setOwner(List<Dict> owner) {
        this.owner = owner;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }
}
