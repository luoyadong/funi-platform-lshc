package com.funi.platform.lshc.service.impl;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.dto.RegiInfoAuditDto;
import com.funi.platform.lshc.entity.census.RegiInfo;
import com.funi.platform.lshc.mapper.census.BuildInfoMapper;
import com.funi.platform.lshc.mapper.census.RegiInfoMapper;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.BuildRegiQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.service.UndoneRegiInfoService;
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
 * Created by sam on 2019/6/17.4:48 PM
 */
public class UndoneRegiInfoServiceImpl implements UndoneRegiInfoService {
    @Resource
    private BuildInfoMapper buildInfoMapper;
    @Resource
    private UserManager userManager;
    @Resource
    private RegiInfoMapper regiInfoMapper;

    @Override
    public List<BuildInfoVo> findBuildInfoList(BuildInfoQuery buildInfoQuery) {
        buildInfoQuery.setOwner(userManager.findUser().getRoles());
        buildInfoQuery.setQueryType(CensusConstants.BUILD_QUERY_TYPE_UNDONE);
        return buildInfoMapper.selectBuildInfoVoList(buildInfoQuery);
    }

    @Override
    public void exportBuildInfoVoList(List<String> ids, HttpServletResponse response) throws Exception {
        BuildRegiQuery buildRegiQuery = new BuildRegiQuery(ids, CensusConstants.BUILD_QUERY_TYPE_UNDONE);
        buildRegiQuery.setOwner(userManager.findUser().getRoles());
        List<ExcelRegiInfoVo> excelRegiInfoVoList = regiInfoMapper.exportBuildInfoVoList(buildRegiQuery);
        if(CollectionUtils.isEmpty(excelRegiInfoVoList)) {
            throw new RuntimeException("没有满足条件的数据");
        }
        ExcelUtil.excelExport("普查数据统计表.xls","普查数据", excelRegiInfoVoList, response);
    }

    @Override
    public List<ListRegiInfoVo> findRegiInfoVoList(RegiInfoQuery regiInfoQuery) {
        regiInfoQuery.setOwner(userManager.findUser().getRoles());
        regiInfoQuery.setQueryType(CensusConstants.BUILD_QUERY_TYPE_UNDONE);
        return regiInfoMapper.selectRegiInfoVoList(regiInfoQuery);
    }

    @Override
    public void batchAuditRegiInfoList(RegiInfoAuditDto regiInfoAuditDto) {
        List<String> ids = regiInfoAuditDto.getIds();
        String result = regiInfoAuditDto.getResult();
        for(String houseId : ids) {
            RegiInfo regiInfo = regiInfoMapper.selectByPrimaryKey(houseId);
            if (regiInfo == null) {
                throw new RuntimeException("普查信息不存在，请确认后再操作");
            }
            String houseStatus = regiInfo.getHouseStatus();
            String nextStatus = null;
            // 如果是待初审状态，将进入初审流程
            if(CensusConstants.HOUSE_STATUS_SUBMIT.equals(houseStatus)) {
                // 如果审批结果是拒绝
                if(CensusConstants.AUDIT_RESULT_REFUSE.equals(result)) {
                    nextStatus = CensusConstants.HOUSE_STATUS_FIRST_APPROVAL_REJECT;
                } else {
                    nextStatus = CensusConstants.HOUSE_STATUS_FIRST_APPROVAL_PASS;
                }
            } else if(CensusConstants.HOUSE_STATUS_FIRST_APPROVAL_PASS.equals(houseStatus)) {
                // 如果是初审通过状态，将进入复审流程
                // 如果审批结果是拒绝
                if(CensusConstants.AUDIT_RESULT_REFUSE.equals(result)) {
                    nextStatus = CensusConstants.HOUSE_STATUS_SECOND_APPROVAL_REJECT;
                } else {
                    nextStatus = CensusConstants.HOUSE_STATUS_SECOND_APPROVAL_PASS;
                }
            } else {
                throw new RuntimeException("普查信息状态异常不能进行审批操作");
            }
            CurrentUser user = userManager.findUser();
            // 修改普查信息的状态
            regiInfoMapper.updateRegiInfoStatus(houseId, nextStatus, user.getUserId());
            // TODO 工作流

            // TODO 记录操作日志
        }
    }
}
