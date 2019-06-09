package com.funi.platform.lshc.entity.sys;

import com.funi.platform.lshc.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * GHOUSE_SYS_CONF_DETA
 * @author 
 */
public class SysConfDeta extends BaseEntity {
    private String configTypeCode;

    private String configDetailName;

    private BigDecimal configDetailOrder;

    private String sysFlag;

    private String detailDescription;

    private static final long serialVersionUID = 1L;

    public String getConfigTypeCode() {
        return configTypeCode;
    }

    public void setConfigTypeCode(String configTypeCode) {
        this.configTypeCode = configTypeCode;
    }

    public String getConfigDetailName() {
        return configDetailName;
    }

    public void setConfigDetailName(String configDetailName) {
        this.configDetailName = configDetailName;
    }

    public BigDecimal getConfigDetailOrder() {
        return configDetailOrder;
    }

    public void setConfigDetailOrder(BigDecimal configDetailOrder) {
        this.configDetailOrder = configDetailOrder;
    }

    public String getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(String sysFlag) {
        this.sysFlag = sysFlag;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

}