package com.funi.platform.lshc.vo;

/**
 * Created by sam on 2018/12/11.4:56 PM
 */
public class HouseFileVo {
    /** 档案柜主键ID*/
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreateUnitId() {
        return createUnitId;
    }

    public void setCreateUnitId(String createUnitId) {
        this.createUnitId = createUnitId;
    }
}
