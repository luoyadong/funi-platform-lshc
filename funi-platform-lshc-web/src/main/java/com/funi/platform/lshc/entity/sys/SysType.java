package com.funi.platform.lshc.entity.sys;

import com.funi.platform.lshc.entity.BaseEntity;

import java.io.Serializable;

/**
 * 系统类型管理表
 * @author sam
 * @date 2018-11-07 22:06:42
 */
public class SysType extends BaseEntity implements Serializable {
    /**
     * 上传要件类型编号，字符串长度不能大于3
     * 添加文件类型，需要完善注释，并添加相应的initDDL（sys_type_init.sql）
     * 001 ： 小区地址变更要件
     */
    private String typeCode;

    /** 类型明细编号 */
    private String typeDetailCode;

    /** 类型明细名称 */
    private String typeDetailName;

    /** 类型明细排序 */
    private Integer typeDetailOrder;

    /** 备注 */
    private String typeRemark;

    private static final long serialVersionUID = 1L;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeDetailCode() {
        return typeDetailCode;
    }

    public void setTypeDetailCode(String typeDetailCode) {
        this.typeDetailCode = typeDetailCode;
    }

    public String getTypeDetailName() {
        return typeDetailName;
    }

    public void setTypeDetailName(String typeDetailName) {
        this.typeDetailName = typeDetailName;
    }

    public Integer getTypeDetailOrder() {
        return typeDetailOrder;
    }

    public void setTypeDetailOrder(Integer typeDetailOrder) {
        this.typeDetailOrder = typeDetailOrder;
    }

    public String getTypeRemark() {
        return typeRemark;
    }

    public void setTypeRemark(String typeRemark) {
        this.typeRemark = typeRemark;
    }
}