package com.funi.platform.lshc.service;

import com.funi.framework.workflow.eic.po.AuditConclusions;
import com.funi.platform.lshc.dto.JobLogDto;
import com.funi.platform.lshc.enumatation.BusinessType;

import java.util.List;
import java.util.Map;

/**
 * @Package:[com.funi.platform.lshc.service]
 * @Description: [房屋普查流程管理类]
 * @Author: [yadong.luo@funi.com]
 * @CreateDate: [2019/06/14 10:20]
 * @Version: [v1.0]
 */
public interface LshcWorkFlowService {

    /**
     * 启动房屋普查工作流程
     * @Author yadong.luo@funi365.com
     * @param bizType 流程类型枚举,eg:BusinessType.pnew
     * @param serviceNum 业务ID,一般为业务件编号，也可传业务ID，需要前后统一
     * @param tableName 业务主表名，eg:LSHC_REGI_INFO
     * @param taskVariables 流程需要携带的Map参数，默认传null
     * @return
     * @throws
     */
    boolean startWorkFlow(BusinessType bizType,String serviceNum,String tableName,Map taskVariables)throws Exception;
    /**
     * 处理房屋普查工作流程
     * @Author yadong.luo@funi365.com
     * @param jobLogDto 审批提交对象
     * @param tableName 业务主表名，eg:LSHC_REGI_INFO
     * @return
     * @throws
     */
    boolean doWorkFlow(JobLogDto jobLogDto,String tableName)throws Exception;
    /**
     * 获取房屋普查当前审批结论，eg:初审通过、初审不通过
     * @Author yadong.luo@funi365.com
     * @param  serviceNum 普查ID
     * @param  newBizType 初次查询需要传入业务类型,非初次可以传null，此处直接传null，eg:BusinessType.pnew
     * @return List<AuditConclusions>
     * @throws
     */
    List<AuditConclusions> findWorkFlowConclusions(String serviceNum,BusinessType newBizType)throws Exception;

    /**
     * 房屋普查-批量提交、批量审批前的的检测，
     * 规则：只能对新增状态的普查做提交；只能对相同状态的数据批量审批
     * @Author yadong.luo@funi365.com
     * @param jobLogDto 审批提交对象
     * @param tableName 业务主表名，eg:LSHC_REGI_INFO
     * @return
     * @throws
     */
    Map<String,Object> checkWorkFlow(JobLogDto jobLogDto,String tableName)throws Exception;
}
