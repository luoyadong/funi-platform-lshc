package com.funi.platform.lshc.service;

import com.funi.platform.lshc.dto.JobDto;

/**
 * @author 3
 */
public interface JobAcceptService {

    /**
     * 根据业务件号 查询受理信息
     * @param serviceNum
     * @return
     */
    JobDto findByServiceNum(String serviceNum);
}
