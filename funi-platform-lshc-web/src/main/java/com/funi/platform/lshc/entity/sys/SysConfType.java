package com.funi.platform.lshc.entity.sys;

import com.funi.platform.lshc.entity.BaseEntity;

/**
 * GHOUSE_SYS_CONF_TYPE
 * @author 
 */
public class SysConfType extends BaseEntity {
    private String configTypeCode;

    private String configTypeName;

    private String typeDescription;

    private String sysFlag;

    private static final long serialVersionUID = 1L;

    public String getConfigTypeCode() {
        return configTypeCode;
    }

    public void setConfigTypeCode(String configTypeCode) {
        this.configTypeCode = configTypeCode;
    }

    public String getConfigTypeName() {
        return configTypeName;
    }

    public void setConfigTypeName(String configTypeName) {
        this.configTypeName = configTypeName;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(String sysFlag) {
        this.sysFlag = sysFlag;
    }

}