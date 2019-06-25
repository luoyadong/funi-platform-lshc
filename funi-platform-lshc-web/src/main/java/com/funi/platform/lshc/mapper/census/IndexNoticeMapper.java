package com.funi.platform.lshc.mapper.census;

import com.funi.platform.lshc.entity.census.IndexNotice;
import com.funi.platform.lshc.query.census.NoticeQuery;
import com.funi.platform.lshc.vo.census.IndexNoticeVo;

public interface IndexNoticeMapper {
    int deleteByPrimaryKey(String id);

    int insert(IndexNotice record);

    int insertSelective(IndexNotice record);

    IndexNotice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(IndexNotice record);

    int updateByPrimaryKeyWithBLOBs(IndexNotice record);

    int updateByPrimaryKey(IndexNotice record);

    /**
     * 分页查询文章列表
     * @param noticeQuery
     * @return
     */
    IndexNoticeVo selectIndexNoticeVoList(NoticeQuery noticeQuery);
}