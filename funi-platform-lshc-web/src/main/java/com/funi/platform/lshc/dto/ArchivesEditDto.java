package com.funi.platform.lshc.dto;

import com.funi.platform.lshc.entity.sys.HouseFile;

import java.util.List;

/**
 * Created by sam on 2018/11/11.9:52 PM
 */
public class ArchivesEditDto {
    /** 档案室ID*/
    private String archivesId;
    /** 档案柜集合*/
    List<HouseFile> houseFileList;

    public String getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(String archivesId) {
        this.archivesId = archivesId;
    }

    public List<HouseFile> getHouseFileList() {
        return houseFileList;
    }

    public void setHouseFileList(List<HouseFile> houseFileList) {
        this.houseFileList = houseFileList;
    }
}
