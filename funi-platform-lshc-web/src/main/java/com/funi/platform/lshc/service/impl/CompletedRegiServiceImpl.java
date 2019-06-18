package com.funi.platform.lshc.service.impl;

import com.funi.platform.lshc.mapper.census.BuildInfoMapper;
import com.funi.platform.lshc.mapper.census.RegiInfoMapper;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.BuildRegiQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.service.CompletedRegiService;
import com.funi.platform.lshc.support.CensusConstants;
import com.funi.platform.lshc.support.UserManager;
import com.funi.platform.lshc.utils.ExcelUtil;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ExcelRegiInfoVo;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sam on 2019/6/17.10:39 PM
 */
public class CompletedRegiServiceImpl implements CompletedRegiService {
    @Resource
    private BuildInfoMapper buildInfoMapper;
    @Resource
    private UserManager userManager;
    @Resource
    private RegiInfoMapper regiInfoMapper;

    @Override
    public List<BuildInfoVo> findBuildInfoList(BuildInfoQuery buildInfoQuery) {
        buildInfoQuery.setUserId(userManager.findUser().getUserId());
        buildInfoQuery.setQueryType(CensusConstants.BUILD_QUERY_TYPE_COMPLETED);
        return buildInfoMapper.selectBuildInfoVoList(buildInfoQuery);
    }

    @Override
    public void exportBuildInfoVoList(List<String> ids, HttpServletResponse response) throws Exception {
        BuildRegiQuery buildRegiQuery = new BuildRegiQuery(ids, CensusConstants.BUILD_QUERY_TYPE_COMPLETED);
        buildRegiQuery.setUserId(userManager.findUser().getUserId());
        List<ExcelRegiInfoVo> excelRegiInfoVoList = regiInfoMapper.exportBuildInfoVoList(buildRegiQuery);
        if(CollectionUtils.isEmpty(excelRegiInfoVoList)) {
            throw new RuntimeException("没有满足条件的数据");
        }
        ExcelUtil.excelExport("普查数据统计表.xls","普查数据", excelRegiInfoVoList, response);
    }

    @Override
    public List<ListRegiInfoVo> findRegiInfoVoList(RegiInfoQuery regiInfoQuery) {
        regiInfoQuery.setUserId(userManager.findUser().getUserId());
        regiInfoQuery.setQueryType(CensusConstants.BUILD_QUERY_TYPE_COMPLETED);
        return regiInfoMapper.selectRegiInfoVoList(regiInfoQuery);
    }
}
