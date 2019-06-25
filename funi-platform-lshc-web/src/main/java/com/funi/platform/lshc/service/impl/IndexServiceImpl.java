package com.funi.platform.lshc.service.impl;

import com.funi.platform.lshc.entity.census.IndexNotice;
import com.funi.platform.lshc.mapper.census.IndexNoticeMapper;
import com.funi.platform.lshc.query.census.NoticeQuery;
import com.funi.platform.lshc.service.IndexService;
import com.funi.platform.lshc.vo.census.IndexNoticeVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sam on 2019/6/25.3:54 PM
 */
public class IndexServiceImpl implements IndexService {
    @Resource
    private IndexNoticeMapper indexNoticeMapper;

    @Override
    public List<IndexNoticeVo> findIndexNoticeList(NoticeQuery noticeQuery) {
        return null;
    }

    @Override
    public IndexNotice findIndexNoticeDetail(String id) {
        return indexNoticeMapper.selectByPrimaryKey(id);
    }
}
