package com.funi.platform.lshc.service.impl;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.entity.census.EntInfo;
import com.funi.platform.lshc.entity.census.File;
import com.funi.platform.lshc.entity.census.RegiInfo;
import com.funi.platform.lshc.mapper.census.BuildInfoMapper;
import com.funi.platform.lshc.mapper.census.EntInfoMapper;
import com.funi.platform.lshc.mapper.census.FileMapper;
import com.funi.platform.lshc.mapper.census.RegiInfoMapper;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.BuildRegiQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.service.RegiInfoService;
import com.funi.platform.lshc.support.BasicHelper;
import com.funi.platform.lshc.support.CensusConstants;
import com.funi.platform.lshc.support.UserManager;
import com.funi.platform.lshc.utils.ExcelUtil;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ExcelRegiInfoVo;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;
import com.funi.platform.lshc.vo.census.RegiInfoDetailVo;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sam on 2019/6/17.3:47 PM
 */
public class RegiInfoServiceImpl implements RegiInfoService {
    @Resource
    private BasicHelper basicHelper;
    @Resource
    private BuildInfoMapper buildInfoMapper;
    @Resource
    private RegiInfoMapper regiInfoMapper;
    @Resource
    private EntInfoMapper entInfoMapper;
    @Resource
    private FileMapper fileMapper;
    @Resource
    private UserManager userManager;

    @Override
    public List<BuildInfoVo> findBuildInfoList(BuildInfoQuery buildInfoQuery) {
        buildInfoQuery.setQueryType(CensusConstants.BUILD_QUERY_TYPE_COLLECT);
        return buildInfoMapper.selectBuildInfoVoList(buildInfoQuery);
    }

    @Override
    public void exportBuildInfoVoList(List<String> ids, HttpServletResponse response) throws Exception {
        BuildRegiQuery buildRegiQuery = new BuildRegiQuery(ids, CensusConstants.BUILD_QUERY_TYPE_COLLECT);
        List<ExcelRegiInfoVo> excelRegiInfoVoList = regiInfoMapper.exportBuildInfoVoList(buildRegiQuery);
        if(CollectionUtils.isEmpty(excelRegiInfoVoList)) {
            throw new RuntimeException("没有满足条件的数据");
        }
        ExcelUtil.excelExport("普查数据统计表.xls","普查数据", excelRegiInfoVoList, response);
    }

    @Override
    public List<ListRegiInfoVo> findRegiInfoVoList(RegiInfoQuery regiInfoQuery) {
        regiInfoQuery.setQueryType(CensusConstants.BUILD_QUERY_TYPE_COLLECT);
        return regiInfoMapper.selectRegiInfoVoList(regiInfoQuery);
    }

    @Override
    public RegiInfoDetailVo findRegiInfoDetail(String hcId) {
        RegiInfoDetailVo regiInfoDetailVo = new RegiInfoDetailVo();
        RegiInfo regiInfo = regiInfoMapper.selectByPrimaryKey(hcId);
        regiInfoDetailVo.setRegiInfo(regiInfo);
        List<EntInfo> entInfoList = entInfoMapper.selectEntInfoListByHouseId(hcId);
        regiInfoDetailVo.setEntInfoList(entInfoList);
        List<File> fileList = fileMapper.selectFileListByHouseId(hcId);
        regiInfoDetailVo.setFileList(fileList);
        // TODO 查询处理过程信息
        // TODO 当前所处的环节
        return regiInfoDetailVo;
    }

    @Override
    public void exportRegiInfoVoList(List<String> ids, HttpServletResponse response) throws Exception {
        List<ExcelRegiInfoVo> excelRegiInfoVoList = regiInfoMapper.selectExcelRegiInfoVoList(ids);
        if(CollectionUtils.isEmpty(excelRegiInfoVoList)) {
            throw new RuntimeException("没有满足条件的数据");
        }
        ExcelUtil.excelExport("楼栋普查数据统计表.xls","普查数据", excelRegiInfoVoList, response);
    }

    @Override
    public void sendBack(String houseId, String desc) {
        RegiInfo regiInfo = regiInfoMapper.selectByPrimaryKey(houseId);
        if (regiInfo == null) {
            throw new RuntimeException("普查信息不存在");
        }
        String houseStatus = regiInfo.getHouseStatus();
        // 只有复审审核通过的业务件才可以被退回
        if(! CensusConstants.HOUSE_STATUS_SECOND_APPROVAL_PASS.equals(houseStatus)) {
            throw new RuntimeException("普查信息状态异常不能进行退回操作");
        }
        CurrentUser user = userManager.findUser();
        // 修改普查信息的状态
        regiInfoMapper.updateRegiInfoStatus(houseId, CensusConstants.HOUSE_STATUS_BACK, user.getUserId());
        // TODO 工作流

        // TODO 记录操作日志

    }
}
