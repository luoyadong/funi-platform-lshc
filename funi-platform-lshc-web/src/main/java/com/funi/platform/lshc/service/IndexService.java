package com.funi.platform.lshc.service;

import com.funi.platform.lshc.entity.census.IndexNotice;
import com.funi.platform.lshc.query.census.NoticeQuery;
import com.funi.platform.lshc.vo.census.IndexNoticeVo;

import java.util.List;

/**
 * Created by sam on 2019/6/25.3:34 PM
 */
public interface IndexService {
    /**
     * 分页查询文章列表
     * @param noticeQuery
     * @return
     */
    List<IndexNoticeVo> findIndexNoticeList(NoticeQuery noticeQuery);

    /**
     * 根据文章ID查询文章详情
     * @param id
     * @return
     */
    IndexNotice findIndexNoticeDetail(String id);
}
