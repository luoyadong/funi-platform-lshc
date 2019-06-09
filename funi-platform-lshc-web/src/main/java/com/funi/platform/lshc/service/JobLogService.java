package com.funi.platform.lshc.service;

import com.funi.platform.lshc.dto.WorkLogDto;
import com.funi.platform.lshc.entity.sys.JobAccept;
import com.funi.platform.lshc.entity.sys.JobLog;

import java.util.List;

/**
 * @author 3
 */
public interface JobLogService {

    /**
     * 创建受理信息
     * @param jobAccept
     * @return
     */
    String createJobAccept(JobAccept jobAccept);

    /**
     * 创建日志明细
     * @param jobLog
     */
    void createJobLog(JobLog jobLog);

    /**
     * 修改当前状态
     * @param jobAccept
     */
    void modifyCurStatus(JobAccept jobAccept);

    List<WorkLogDto> findByServiceNum(String serviceNum);
}
