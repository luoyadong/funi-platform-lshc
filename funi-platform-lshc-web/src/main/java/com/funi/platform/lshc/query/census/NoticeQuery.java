package com.funi.platform.lshc.query.census;

import com.funi.platform.lshc.query.BaseQuery;

/**
 * Created by sam on 2019/6/25.3:43 PM
 */
public class NoticeQuery extends BaseQuery {
    /** 公告类型 */
    private String noticeType;

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }
}
