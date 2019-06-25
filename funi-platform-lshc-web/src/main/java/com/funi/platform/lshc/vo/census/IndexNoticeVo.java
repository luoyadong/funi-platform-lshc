package com.funi.platform.lshc.vo.census;

/**
 * Created by sam on 2019/6/25.3:49 PM
 */
public class IndexNoticeVo {
    /**数据库主键ID*/
    private String id;

    /** 公告标题 */
    private String noticeTitle;

    /** 公告日期 */
    private String noticeDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }
}
