package com.funi.platform.lshc.enumatation;

/**
 * Created by sam on 2018/11/7.10:48 PM
 */
public enum  IsChange {
    /** 已变更*/
    CHANGED(1),
    /** 未变更*/
    NOT_CHANGED(0);

    private Integer changeStatus;

    IsChange(Integer changeStatus) {
        this.changeStatus = changeStatus;
    }

    public Integer getChangeStatus() {
        return changeStatus;
    }

}
