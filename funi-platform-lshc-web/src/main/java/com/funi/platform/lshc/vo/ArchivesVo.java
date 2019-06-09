package com.funi.platform.lshc.vo;

/**
 * Created by sam on 2018/11/28.9:47 PM
 */
public class ArchivesVo {
    /**数据库主键ID*/
    private String archivesId;
    /** 档案室名称*/
    private String archivesName;
    /** 数据的创建时间*/
    private String sysCreateTime;
    /** 档案室的创建者机构编码*/
    private String createUnitId;

    public String getCreateUnitId() {
        return createUnitId;
    }

    public void setCreateUnitId(String createUnitId) {
        this.createUnitId = createUnitId;
    }

    public String getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(String archivesId) {
        this.archivesId = archivesId;
    }

    public String getArchivesName() {
        return archivesName;
    }

    public void setArchivesName(String archivesName) {
        this.archivesName = archivesName;
    }

    public String getSysCreateTime() {
        return sysCreateTime;
    }

    public void setSysCreateTime(String sysCreateTime) {
        this.sysCreateTime = sysCreateTime;
    }

}
