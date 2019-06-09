package com.funi.platform.lshc.entity.sys;

import com.funi.platform.lshc.entity.BaseEntity;

/**
 * 档案室
 * @author 
 */
public class Archives extends BaseEntity {
    /** 档案室名称*/
    private String archivesName;
    /** 档案室创建者机构编码*/
    private String createUnitId;

    private static final long serialVersionUID = 1L;

    public String getCreateUnitId() {
        return createUnitId;
    }

    public void setCreateUnitId(String createUnitId) {
        this.createUnitId = createUnitId;
    }

    public String getArchivesName() {
        return archivesName;
    }

    public void setArchivesName(String archivesName) {
        this.archivesName = archivesName;
    }
}