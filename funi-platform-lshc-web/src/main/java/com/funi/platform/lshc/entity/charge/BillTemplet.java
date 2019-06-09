package com.funi.platform.lshc.entity.charge;

import com.funi.platform.lshc.entity.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 票据打印模板
 * @author sam
 * @date 2018-11-12 10:11:55
 */
public class BillTemplet extends BaseEntity implements Serializable {
    /** 票据打印模板名称 */
    private String billTempletName;

    /** 票据类型 0：空白机打票据，1：带格式机打票据，2：手写票据*/
    private String billType;

    /** 票据属性 */
    private String billProperty;

    /** 最大行数 */
    private BigDecimal maxRows;

    /** 备注 */
    private String templetRemarks;

    /** 内容 */
    private String billContent;

    /** 使用标志,1：启用，0：未启用 */
    private String userFlag;

    /** 系统预制Flag */
    private String sysFlag;

    /** 模板名称，用于调用打印服务 */
    private String tmpName;

    public String getTmpName() {
        return tmpName;
    }

    public void setTmpName(String tmpName) {
        this.tmpName = tmpName;
    }

    private static final long serialVersionUID = 1L;

    public String getBillTempletName() {
        return billTempletName;
    }

    public void setBillTempletName(String billTempletName) {
        this.billTempletName = billTempletName;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillProperty() {
        return billProperty;
    }

    public void setBillProperty(String billProperty) {
        this.billProperty = billProperty;
    }

    public BigDecimal getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(BigDecimal maxRows) {
        this.maxRows = maxRows;
    }

    public String getTempletRemarks() {
        return templetRemarks;
    }

    public void setTempletRemarks(String templetRemarks) {
        this.templetRemarks = templetRemarks;
    }

    public String getBillContent() {
        return billContent;
    }

    public void setBillContent(String billContent) {
        this.billContent = billContent;
    }

    public String getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(String userFlag) {
        this.userFlag = userFlag;
    }

    public String getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(String sysFlag) {
        this.sysFlag = sysFlag;
    }

}