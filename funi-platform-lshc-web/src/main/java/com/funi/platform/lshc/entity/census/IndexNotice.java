package com.funi.platform.lshc.entity.census;

import com.funi.platform.lshc.entity.SuperEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * 首页公告信息
 * @author sam
 * @date 2019-06-22 14:57:12
 */
public class IndexNotice extends SuperEntity implements Serializable {
    /** 公告类型 */
    private String noticeType;

    /** 公告标题 */
    private String noticeTitle;

    /** 公告日期 */
    private Date noticeDate;

    /** url */
    private String url;

    /** 备用 */
    private String common;

    /** 公告内容 */
    private String content;

    private static final long serialVersionUID = 1L;

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}