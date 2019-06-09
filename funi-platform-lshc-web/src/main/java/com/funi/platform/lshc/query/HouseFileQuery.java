package com.funi.platform.lshc.query;

import com.funi.platform.lshc.entity.sys.HouseFile;

/**
 * Created by sam on 2018/11/29.12:05 AM
 */
public class HouseFileQuery extends GhouseBaseQuery {
    /** 档案室ID*/
    private String archivesId;
    /** 档案柜名称*/
    private String fileName;

    public HouseFileQuery() {
    }

    public HouseFileQuery(HouseFile houseFile) {
        this.archivesId = houseFile.getArchivesId();
        this.fileName = houseFile.getFileName();
    }

    public String getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(String archivesId) {
        this.archivesId = archivesId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
