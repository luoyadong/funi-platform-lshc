package com.funi.platform.lshc.enumatation;

/**
 * Created by yadong on 2018/11/7.10:48 PM
 */
public enum CommonBizType {
    /** 全域查询*/
    ALL_ORG(1),
    /** 未变更*/
    USER_ORG(0),
    /** 业务启用*/
    BIZ_OPEN(1),
    /** 业务关闭*/
    BIZ_CLOSE(0);

    private Integer bizType;

    CommonBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public Integer getBizType() {
        return bizType;
    }

}
