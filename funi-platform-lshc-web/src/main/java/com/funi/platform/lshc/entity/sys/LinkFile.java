package com.funi.platform.lshc.entity.sys;

import com.funi.platform.lshc.entity.BaseEntity;
import com.funi.platform.lshc.enumatation.UploadBusinessType;
import com.funi.platform.lshc.vo.LinkFileVo;

import java.io.Serializable;

/**
 * 关联文件管理表
 * @author sam
 * @date 2018-11-07 22:05:27
 */
public class LinkFile extends BaseEntity implements Serializable {
    /**
     * 关联类型，数据来源于SysType
     */
    private String linkType;

    /** 关联ID */
    private String linkId;

    /** 关联显示文件名 */
    private String linkShowName;

    /** 关联文件排序 */
    private Integer linkOrder;

    /** 文件名 */
    private String linkFileName;

    /** 文件类型 */
    private String linkFileType;

    /** 文件地址 */
    private String linkFileUrl;

    public LinkFile() {
    }

    public LinkFile(String linkId, LinkFileVo linkFileVo, UploadBusinessType uploadBusinessType) {
        this.linkType = uploadBusinessType.getTypeCode();
        this.linkId = linkId;
        this.linkShowName = linkFileVo.getLinkFileName();
        this.linkOrder = linkFileVo.getLinkOrder();
        this.linkFileName = linkFileVo.getLinkFileName();
        this.linkFileUrl = linkFileVo.getLinkFileUrl();
    }

    private static final long serialVersionUID = 1L;

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getLinkShowName() {
        return linkShowName;
    }

    public void setLinkShowName(String linkShowName) {
        this.linkShowName = linkShowName;
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