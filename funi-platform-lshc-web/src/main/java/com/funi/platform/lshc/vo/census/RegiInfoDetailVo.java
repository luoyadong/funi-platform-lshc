package com.funi.platform.lshc.vo.census;

import com.funi.platform.lshc.entity.census.EntInfo;
import com.funi.platform.lshc.entity.census.File;
import com.funi.platform.lshc.entity.census.RegiInfo;

import java.util.List;

/**
 * Created by sam on 2019/6/14.9:36 AM
 */
public class RegiInfoDetailVo {
    /** 房屋信息*/
    private RegiInfo regiInfo;
    /** 人口信息*/
    private List<EntInfo> entInfoList;
    /** 文件信息*/
    private List<File> fileList;
    /** TODO 处理过程信息*/

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
