package com.funi.platform.lshc.entity.contr;

import com.funi.platform.lshc.entity.BaseEntity;


/**
 * 合同模板
 * @author 3
 */
public class ContrTemplet extends BaseEntity {

    /**
     * 合同模板名称
     */
    private String contractTempletName;

    /**
     * 单位ID
     */
    private String unitId;

    /**
     * 内容
     */
    private String templetContent;

    /**
     * 是否启用
     */
    private String validFlg;

    /**
     * 模板url
     */
    private String tempUrl;

    public String getTempUrl() {
        return tempUrl;
    }

    public void setTempUrl(String tempUrl) {
        this.tempUrl = tempUrl;
    }

    public String getContractTempletName() {
        return contractTempletName;
    }

    public void setContractTempletName(String contractTempletName) {
        this.contractTempletName = contractTempletName;
    }

    public String getTempletContent() {
        return templetContent;
    }

    public void setTempletContent(String templetContent) {
        this.templetContent = templetContent;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getValidFlg() {
        return validFlg;
    }

    public void setValidFlg(String validFlg) {
        this.validFlg = validFlg;
    }
}