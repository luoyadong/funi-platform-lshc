package com.funi.platform.lshc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * entity基础类
 * @author
 */
public class BaseEntity implements Serializable {
    /**数据库主键ID*/
    private String id;
    /** 是否逻辑删除，默认0*/
    private Integer isDeleted = 0;
    /** 数据版本号，默认1，每次更新+1*/
    private Integer version = 1;
    /** 数据的创建时间*/
    private Date sysCreateTime;
    /** 数据的最后时间*/
    private Date sysUpdateTime;
    /** 数据的逻辑删除时间*/
    private Date sysDeleteTime;
    /** 数据创建人的ID*/
    private String sysCreateId;
    /** 数据最后更新人的ID*/
    private String sysUpdateId;
    /** 数据逻辑删除人的ID*/
    private String sysDeleteId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getSysCreateTime() {
        return sysCreateTime;
    }

    public void setSysCreateTime(Date sysCreateTime) {
        this.sysCreateTime = sysCreateTime;
    }

    public Date getSysUpdateTime() {
        return sysUpdateTime;
    }

    public void setSysUpdateTime(Date sysUpdateTime) {
        this.sysUpdateTime = sysUpdateTime;
    }

    public Date getSysDeleteTime() {
        return sysDeleteTime;
    }

    public void setSysDeleteTime(Date sysDeleteTime) {
        this.sysDeleteTime = sysDeleteTime;
    }

    public String getSysCreateId() {
        return sysCreateId;
    }

    public void setSysCreateId(String sysCreateId) {
        this.sysCreateId = sysCreateId;
    }

    public String getSysUpdateId() {
        return sysUpdateId;
    }

    public void setSysUpdateId(String sysUpdateId) {
        this.sysUpdateId = sysUpdateId;
    }

    public String getSysDeleteId() {
        return sysDeleteId;
    }

    public void setSysDeleteId(String sysDeleteId) {
        this.sysDeleteId = sysDeleteId;
    }
}
