package com.funi.platform.lshc.service.impl;

import com.funi.platform.lshc.mapper.census.BuildInfoMapper;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.service.CompletedRegiService;
import com.funi.platform.lshc.support.UserManager;
import com.funi.platform.lshc.vo.census.BuildInfoVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sam on 2019/6/17.10:39 PM
 */
public class CompletedRegiServiceImpl implements CompletedRegiService {
    @Resource
    private BuildInfoMapper buildInfoMapper;
    @Resource
    private UserManager userManager;

    @Override
    public List<BuildInfoVo> findBuildInfoList(BuildInfoQuery buildInfoQuery) {
        buildInfoQuery.setUserId(userManager.findUser().getUserId());
        return buildInfoMapper.selectCompletedBuildInfoVoList(buildInfoQuery);
    }
}
