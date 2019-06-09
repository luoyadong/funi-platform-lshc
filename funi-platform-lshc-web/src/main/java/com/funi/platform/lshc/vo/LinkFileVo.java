package com.funi.platform.lshc.vo;

/**
 * 上传要件
 * Created by sam on 2018/11/7.10:23 PM
 */
public class LinkFileVo {
    /** 文件ID*/
    private String fileId;
    /** 关联文件排序 */
    private Integer linkOrder;
    /** 文件名 */
    private String linkFileName;

    /** 文件类型 */
    private String linkFileType;

    /** 文件地址 */
    private String linkFileUrl;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getLinkOrder() {
        return linkOrder;
    }

    public void setLinkOrder(Integer linkOrder) {
        this.linkOrder = linkOrder;
    }

    public String getLinkFileName() {
        return linkFileName;
    }

    public void setLinkFileName(String linkFileName) {
        this.linkFileName = linkFileName;
    }

    public String getLinkFileType() {
        return linkFileType;
    }

    public void setLinkFileType(String linkFileType) {
        this.linkFileType = linkFileType;
    }

    public String getLinkFileUrl() {
        return linkFileUrl;
    }

    public void setLinkFileUrl(String linkFileUrl) {
        this.linkFileUrl = linkFileUrl;
    }
}
