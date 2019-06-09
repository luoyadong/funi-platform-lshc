package com.funi.platform.lshc.vo;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by sam on 2018/11/8.9:40 PM
 */
public class AddrChangeVo {

    private String beforeAddress;

    private String afterAddress;

    private String changeUser;

    private String changeDate;

    private String changeRemark;

    private List<LinkFileVo> linkFileVoList;

    private String linkFileName;

    private String linkFileUrl;

    /** 下载链接描述字段*/
    private String downloadDesc;

    public String getDownloadDesc() {
        List<LinkFileVo> linkFileVoList = getLinkFileVoList();
        if(CollectionUtils.isNotEmpty(linkFileVoList)) {
            StringBuilder downloadUrl = new StringBuilder();
            for(LinkFileVo linkFileVo : linkFileVoList) {
                // 文件名
                String linkFileName = linkFileVo.getLinkFileName();
                // 文件地址
                String linkFileUrl =linkFileVo.getLinkFileUrl();
                if(StringUtils.isNotBlank(linkFileName) && StringUtils.isNotBlank(linkFileUrl)) {
                    downloadUrl.append("<a href=\"");
                    downloadUrl.append(linkFileUrl);
                    downloadUrl.append("\"");
                    downloadUrl.append(" target=\"_blank\"");
                    downloadUrl.append(">");
                    downloadUrl.append(linkFileName);
                    downloadUrl.append("</a>&nbsp;&nbsp;&nbsp;&nbsp;");
                }
            }
            return downloadUrl.toString();
        }
        return downloadDesc;
    }

    public void setDownloadDesc(String downloadDesc) {
        this.downloadDesc = downloadDesc;
    }

    public String getLinkFileName() {
        if(CollectionUtils.isNotEmpty(linkFileVoList)) {
            return linkFileVoList.get(0).getLinkFileName();
        }
        return linkFileName;
    }

    public void setLinkFileName(String linkFileName) {
        this.linkFileName = linkFileName;
    }

    public String getLinkFileUrl() {
        if(CollectionUtils.isNotEmpty(linkFileVoList)) {
            return linkFileVoList.get(0).getLinkFileUrl();
        }
        return linkFileUrl;
    }

    public void setLinkFileUrl(String linkFileUrl) {
        this.linkFileUrl = linkFileUrl;
    }

    public String getBeforeAddress() {
        return beforeAddress;
    }

    public void setBeforeAddress(String beforeAddress) {
        this.beforeAddress = beforeAddress;
    }

    public String getAfterAddress() {
        return afterAddress;
    }

    public void setAfterAddress(String afterAddress) {
        this.afterAddress = afterAddress;
    }

    public String getChangeUser() {
        return changeUser;
    }

    public void setChangeUser(String changeUser) {
        this.changeUser = changeUser;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangeRemark() {
        return changeRemark;
    }

    public void setChangeRemark(String changeRemark) {
        this.changeRemark = changeRemark;
    }

    public List<LinkFileVo> getLinkFileVoList() {
        return linkFileVoList;
    }

    public void setLinkFileVoList(List<LinkFileVo> linkFileVoList) {
        this.linkFileVoList = linkFileVoList;
    }
}
