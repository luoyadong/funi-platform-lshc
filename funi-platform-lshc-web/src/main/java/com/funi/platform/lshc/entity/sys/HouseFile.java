package com.funi.platform.lshc.entity.sys;

import com.funi.platform.lshc.entity.BaseEntity;

/**
 * 档案室
 * @author 
 */
public class HouseFile extends BaseEntity {
    /** 档案室ID*/
    private String archivesId;
    /** 档案柜编号*/
    private String fileNo;
    /** 档案柜名称*/
    private String fileName;
    /** 序号*/
    private Integer fileOrder;
    /** 档案柜创建者机构编码*/
    private String createUnitId;

    public HouseFile() {
    }

    public HouseFile(String archivesId, String fileNo, String fileName, Integer fileOrder) {
        this.archivesId = archivesId;
        this.fileNo = fileNo;
        this.fileName = fileName;
        this.fileOrder = fileOrder;
    }

    private static final long serialVersionUID = 1L;

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

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileOrder() {
        return fileOrder;
    }

    public void setFileOrder(Integer fileOrder) {
        this.fileOrder = fileOrder;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        HouseFile other = (HouseFile) that;
        return (this.getArchivesId() == null ? other.getArchivesId() == null : this.getArchivesId().equals(other.getArchivesId()))
                && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
                && (this.getFileNo() == null ? other.getFileNo() == null : this.getFileNo().equals(other.getFileNo()))
                && (this.getFileOrder() == null ? other.getFileOrder() == null : this.getFileOrder().equals(other.getFileOrder()));
    }

}