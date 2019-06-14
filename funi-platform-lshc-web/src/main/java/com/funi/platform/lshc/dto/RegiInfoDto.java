package com.funi.platform.lshc.dto;

import com.funi.platform.lshc.entity.census.EntInfo;
import com.funi.platform.lshc.entity.census.File;
import com.funi.platform.lshc.entity.census.RegiInfo;

import java.util.List;

/**
 * Created by sam on 2019/6/14.11:53 PM
 */
public class RegiInfoDto {
    private RegiInfo regiInfo;
    /** */
    private List<EntInfo> entInfoList;
    /** 附件列表*/
    private List<File> fileList;

    public RegiInfo getRegiInfo() {
        return regiInfo;
    }

    public void setRegiInfo(RegiInfo regiInfo) {
        this.regiInfo = regiInfo;
    }

    public List<EntInfo> getEntInfoList() {
        return entInfoList;
    }

    public void setEntInfoList(List<EntInfo> entInfoList) {
        this.entInfoList = entInfoList;
    }

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }
}
