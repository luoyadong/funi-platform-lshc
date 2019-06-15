package com.funi.platform.lshc.vo.census;

/**
 * Created by sam on 2019/6/15.9:48 AM
 */
public class ListFileVo {
    /** 文件的主键ID*/
    private String fileId;
    /** 附件名称 */
    private String fileName;
    /** url */
    private String url;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
