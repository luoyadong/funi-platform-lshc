package com.funi.platform.lshc.dto;

/**
 * 合同档案信息
 * @author 3
 */
public class DocumentDto {

    //档案编号
    private String documentNo;
    //文件数量
    private Integer documentCnt;
    //档案存放点
    private String archiveRoomNo;
    //
    private String archiveCabinetNo;
    //
    private String archiveFileNo;
    //档案存放时间
    private String documentDate;
    //备注
    private String documentAddInfo;

    public String getArchiveCabinetNo() {
        return archiveCabinetNo;
    }

    public void setArchiveCabinetNo(String archiveCabinetNo) {
        this.archiveCabinetNo = archiveCabinetNo;
    }

    public String getArchiveFileNo() {
        return archiveFileNo;
    }

    public void setArchiveFileNo(String archiveFileNo) {
        this.archiveFileNo = archiveFileNo;
    }

    public String getArchiveRoomNo() {
        return archiveRoomNo;
    }

    public void setArchiveRoomNo(String archiveRoomNo) {
        this.archiveRoomNo = archiveRoomNo;
    }

    public String getDocumentAddInfo() {
        return documentAddInfo;
    }

    public void setDocumentAddInfo(String documentAddInfo) {
        this.documentAddInfo = documentAddInfo;
    }

    public Integer getDocumentCnt() {
        return documentCnt;
    }

    public void setDocumentCnt(Integer documentCnt) {
        this.documentCnt = documentCnt;
    }

    public String getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }
}
