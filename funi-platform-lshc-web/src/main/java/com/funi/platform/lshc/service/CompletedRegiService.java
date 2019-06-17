package com.funi.platform.lshc.service;

import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.vo.census.BuildInfoVo;

import java.util.List;

/**
 * Created by sam on 2019/6/17.10:39 PM
 */
public interface CompletedRegiService {
    /**
     * 分页查询楼栋列表，只查询新建和初审不通过的数据
     * @param buildInfoQuery
     * @return
     */
    List<BuildInfoVo> findBuildInfoList(BuildInfoQuery buildInfoQuery);
}
