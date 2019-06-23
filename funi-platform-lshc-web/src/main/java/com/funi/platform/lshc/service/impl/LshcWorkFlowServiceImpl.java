package com.funi.platform.lshc.service.impl;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.framework.workflow.eic.po.AuditConclusions;
import com.funi.framework.workflow.eic.service.WorkFlowService;
import com.funi.platform.lshc.dto.JobDto;
import com.funi.platform.lshc.dto.JobLogDto;
import com.funi.platform.lshc.entity.sys.JobAccept;
import com.funi.platform.lshc.enumatation.BusinessType;
import com.funi.platform.lshc.enumatation.Node;
import com.funi.platform.lshc.service.JobAcceptService;
import com.funi.platform.lshc.service.JobLogService;
import com.funi.platform.lshc.service.LshcWorkFlowService;
import com.funi.platform.lshc.support.UserManager;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LshcWorkFlowServiceImpl implements LshcWorkFlowService {

    @Resource
    private UserManager userManager;
    @Resource
    private JobAcceptService jobAcceptService;
    @Resource
    private WorkFlowService workFlowService;
    @Resource
    private JobLogService jobLogService;

    @Override
    public boolean startWorkFlow(BusinessType bizType, String serviceNum, String tableName, Map taskVariables) throws Exception {
        if(StringUtils.isNotBlank(serviceNum) && StringUtils.isNotBlank(tableName)){
            //创建受理信息
            JobAccept jobAccept = new JobAccept();
            jobAccept.setServiceNum(serviceNum);
            jobAccept.setAcceptTime(new Date());
            jobAccept.setTypeName(BusinessType.cnew.getWorkName());
            jobLogService.createJobAccept(jobAccept);

            workFlowService.start(bizType.getKey(),bizType.getName(),bizType.getVersion().toString(),
                    serviceNum,tableName,userManager.findRegionCode(),
                    userManager.findUser().getId(),taskVariables);
            return true;
        }
        return false;
    }

    @Override
    public boolean doWorkFlow(JobLogDto jobLogDto, String tableName) throws Exception {
        if(null != jobLogDto && null != jobLogDto.getJobAccept()){
            JobDto jobDto =jobAcceptService.findByServiceNum(jobLogDto.getJobAccept().getServiceNum());
            //修改当前状态
            jobLogDto.getJobAccept().setCurStatus(jobLogDto.getJobLog().getJobResult());
            jobLogDto.getJobAccept().setId(jobDto.getId());
            jobLogService.modifyCurStatus(jobLogDto.getJobAccept());
            //日志明细
            jobLogDto.getJobLog().setJobAcceptId(jobDto.getId());
            CurrentUser currentUser = userManager.findUser();
            jobLogDto.getJobLog().setAuditId(currentUser.getId());
            jobLogDto.getJobLog().setAuditName(currentUser.getName());
            jobLogDto.getJobLog().setUnitName(currentUser.getOrganization().getMc());
            jobLogService.createJobLog(jobLogDto.getJobLog());
            //流程
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("flag", jobLogDto.getJobResultId());//前端传过来的结论名称，eg:初审通过/初审不通过
            // param.put("flag", jobLogDto.getJobLog().getJobResult());
            BusinessType businessType=BusinessType.findByWorkName(jobDto.getBusinessName());
            workFlowService.complete(jobLogDto.getJobAccept().getServiceNum(),tableName,userManager.findRegionCode(),
                    userManager.findUser().getUserId(),
                    com.funi.framework.utils.StringUtils.hasText(jobLogDto.getJobLog().getNodeName())?jobLogDto.getJobLog().getNodeName(): Node.N001.getName()
                    ,param,
                    businessType.getKey(),jobLogDto.getJobLog().getJobResult());
            return true;
        }
        return false;
    }

    @Override
    public List<AuditConclusions> findWorkFlowConclusions(String serviceNum,BusinessType newBizType) throws Exception{
        List<AuditConclusions> auditList;

        JobDto jobDto = jobAcceptService.findByServiceNum(serviceNum);
        BusinessType businessType;
        String nodeName;
        if(null!=jobDto) {
            businessType=BusinessType.findByWorkName(jobDto.getBusinessName());
            nodeName= org.springframework.util.StringUtils.hasText(jobDto.getNodeName())?jobDto.getNodeName(): Node.N001.getName();
        }else{
            businessType=BusinessType.pnew;
            nodeName= Node.N001.getName();
        }
        auditList = workFlowService.findAuditConclusions(businessType.getKey(),businessType.getName(),businessType.getVersion().toString(),nodeName,userManager.findRegionCode());
        return auditList;
    }

    @Override
    public Map<String, Object> checkWorkFlow(JobLogDto jobLogDto, String tableName) throws Exception {
        Map<String, Object> rtMap = new HashMap<String,Object>();
        String msg = "";



        rtMap.put("CHECK_FLAG",true);
        rtMap.put("CHECK_MSG",msg);
        return rtMap;
    }
}
