package com.funi.platform.lshc.service.impl;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.dto.WorkLogDto;
import com.funi.platform.lshc.entity.sys.JobAccept;
import com.funi.platform.lshc.entity.sys.JobLog;
import com.funi.platform.lshc.mapper.sys.JobAcceptMapper;
import com.funi.platform.lshc.mapper.sys.JobLogMapper;
import com.funi.platform.lshc.service.JobLogService;
import com.funi.platform.lshc.support.UserManager;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author 3
 */
public class JobLogServiceImpl implements JobLogService {

    @Resource(name="ghouseJobLogMapper")
    private JobLogMapper jobLogMapper;
    @Resource
    private JobAcceptMapper jobAcceptMapper;
    @Resource
    private UserManager userManager;

    @Override
    public String createJobAccept(JobAccept jobAccept) {
        jobAcceptMapper.insert(jobAccept);
        CurrentUser user =userManager.findUser();
        JobLog jobLog = new JobLog();
        jobLog.setId(UUID.randomUUID().toString());
        jobLog.setAuditName(user.getName());
        jobLog.setAuditId(user.getUserId());
        jobLog.setJobAcceptId(jobAccept.getId());
        jobLog.setNodeName("录入");
       // createJobLog(jobLog);
        return jobAccept.getId();
    }

    @Override
    public void createJobLog(JobLog jobLog) {
        jobLogMapper.insert(jobLog);
    }

    @Override
    public void modifyCurStatus(JobAccept jobAccept) {
        jobAcceptMapper.modifyCurStatus(jobAccept);
    }

    @Override
    public List<WorkLogDto> findByServiceNum(String serviceNum) {
        return jobLogMapper.selectByServiceNum(serviceNum);
    }
}
