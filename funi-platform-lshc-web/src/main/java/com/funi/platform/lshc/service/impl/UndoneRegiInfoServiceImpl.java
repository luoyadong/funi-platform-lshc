package com.funi.platform.lshc.service.impl;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.dto.JobLogDto;
import com.funi.platform.lshc.dto.RegiInfoAuditDto;
import com.funi.platform.lshc.entity.sys.JobAccept;
import com.funi.platform.lshc.entity.sys.JobLog;
import com.funi.platform.lshc.mapper.census.BuildInfoMapper;
import com.funi.platform.lshc.mapper.census.RegiInfoMapper;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.BuildRegiQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.service.LshcWorkFlowService;
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
    @Resource
    private LshcWorkFlowService lshcWorkFlowService;

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
        buildRegiQuery.setPage(1);
        buildRegiQuery.setLimit(50000);//目前最多支持单次导出50000条记录
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
    public void batchAuditRegiInfoList(RegiInfoAuditDto regiInfoAuditDto)throws Exception {
        List<String> ids = regiInfoAuditDto.getIds();
        String result = regiInfoAuditDto.getResult();
        for(String houseId : ids) {
//            RegiInfo regiInfo = regiInfoMapper.selectByPrimaryKey(houseId);
//            if (regiInfo == null) {
//                throw new RuntimeException("普查信息不存在，请确认后再操作");
//            }
//            String houseStatus = regiInfo.getHouseStatus();
//            String nextStatus = null;
//            // 如果是待初审状态，将进入初审流程
//            if(CensusConstants.HOUSE_STATUS_SUBMIT.equals(houseStatus)) {
//                // 如果审批结果是拒绝
//                if(CensusConstants.AUDIT_RESULT_REFUSE.equals(result)) {
//                    nextStatus = CensusConstants.HOUSE_STATUS_FIRST_APPROVAL_REJECT;
//                } else {
//                    nextStatus = CensusConstants.HOUSE_STATUS_FIRST_APPROVAL_PASS;
//                }
//            } else if(CensusConstants.HOUSE_STATUS_FIRST_APPROVAL_PASS.equals(houseStatus)) {
//                // 如果是初审通过状态，将进入复审流程
//                // 如果审批结果是拒绝
//                if(CensusConstants.AUDIT_RESULT_REFUSE.equals(result)) {
//                    nextStatus = CensusConstants.HOUSE_STATUS_SECOND_APPROVAL_REJECT;
//                } else {
//                    nextStatus = CensusConstants.HOUSE_STATUS_SECOND_APPROVAL_PASS;
//                }
//            } else {
//                throw new RuntimeException("普查信息状态异常不能进行审批操作");
//            }
            CurrentUser user = userManager.findUser();
            // 修改普查信息的状态,状态暂时和审批保持一致，便于查询（状态为中文）
            String nextStatus = regiInfoAuditDto.getResult();
            regiInfoMapper.updateRegiInfoStatus(houseId, nextStatus, user.getUserId());
            // TODO 工作流
            try {
                lshcWorkFlowService.doWorkFlow(buildAuditDto(regiInfoAuditDto,houseId),"LSHC_REGI_INFO");
            } catch (Exception e) {
                throw e;
            }
        }
    }

    private JobLogDto buildAuditDto(RegiInfoAuditDto regiInfoAuditDto,String id){
        if(null == regiInfoAuditDto){
            return null;
        }
        JobLogDto jobLogDto = new JobLogDto();
        JobAccept jobAccept = new JobAccept();
        JobLog jobLog = new JobLog();

        jobLogDto.setJobResultId(regiInfoAuditDto.getJobResultId());
        jobAccept.setServiceNum(id);
        jobAccept.setCurStatus(regiInfoAuditDto.getResult());

        jobLog.setJobResult(regiInfoAuditDto.getResult());
        jobLog.setNodeName(regiInfoAuditDto.getNodeName());
        jobLog.setJobOpinion(regiInfoAuditDto.getDesc());

        jobLogDto.setJobAccept(jobAccept);
        jobLogDto.setJobLog(jobLog);
        return jobLogDto;
    }
}
