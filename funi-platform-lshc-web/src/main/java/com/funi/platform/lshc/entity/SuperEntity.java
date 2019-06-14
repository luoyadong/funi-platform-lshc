package com.funi.platform.lshc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * entity基础类
 * @author
 */
public class SuperEntity implements Serializable {
    /**数据库主键ID*/
    private String id;
    /** 数据版本号，默认0，每次更新+1*/
    private Integer version;
    /** 数据的创建时间*/
    private Date createTime;
    /** 数据创建人的ID*/
    private String createId;
    /** 数据的最后时间*/
    private Date updateTime;
    /** 数据最后更新人的ID*/
    private String updateId;
    /** 是否逻辑删除，1：逻辑删除，0：没有逻辑删除（默认值）*/
    private Short deleted;
    /** 数据是否有效，1：有效（默认值），0：无效*/
    private String isvalide;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Short getDeleted() {
        return deleted;
    }

    public void setDeleted(Short deleted) {
        this.deleted = deleted;
    }

    public String getIsvalide() {
        return isvalide;
    }

    public void setIsvalide(String isvalide) {
        this.isvalide = isvalide;
    }
}
