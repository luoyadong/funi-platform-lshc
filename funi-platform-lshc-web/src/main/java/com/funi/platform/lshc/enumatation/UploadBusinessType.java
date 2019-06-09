package com.funi.platform.lshc.enumatation;

/**
 * 上传要件文件类型枚举
 * Created by sam on 2018/11/7.10:28 PM
 */
public enum UploadBusinessType {
    /** 小区地址变更要件*/
    ADDRESS_CHANGE("001", "小区地址变更要件"),
    /** 小区地址变更要件*/
    HOUSE_CONTRACT("010", "购房合同要件"),
    LESSEE_ATTACH("C001","承租人要件"),
    DOCUMENT_ATTACH("C002","档案要件");

    private String typeCode;
    private String description;

    UploadBusinessType(String typeCode, String description) {
        this.typeCode = typeCode;
        this.description = description;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public String getDescription() {
        return description;
    }

}
