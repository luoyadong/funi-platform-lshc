package com.funi.platform.lshc.dto;

import com.funi.platform.lshc.entity.sys.JobAccept;
import com.funi.platform.lshc.entity.sys.JobLog;
import com.funi.platform.lshc.enumatation.BusinessType;

/**
 * @author 3
 */
public class JobLogDto {

    //受理信息
    private JobAccept jobAccept;
    //日志明细
    private JobLog jobLog;
    //业务类型
    private BusinessType businessType;
    //结论id
    private String jobResultId;

    public JobAccept getJobAccept() {
        return jobAccept;
    }

    public void setJobAccept(JobAccept jobAccept) {
        this.jobAccept = jobAccept;
    }

    public JobLog getJobLog() {
        return jobLog;
    }

    public void setJobLog(JobLog jobLog) {
        this.jobLog = jobLog;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public String getJobResultId() {
        return jobResultId;
    }

    public void setJobResultId(String jobResultId) {
        this.jobResultId = jobResultId;
    }
}
