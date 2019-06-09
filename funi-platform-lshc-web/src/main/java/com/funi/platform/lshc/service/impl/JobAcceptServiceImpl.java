package com.funi.platform.lshc.service.impl;

import com.funi.platform.lshc.dto.JobDto;
import com.funi.platform.lshc.mapper.sys.JobAcceptMapper;
import com.funi.platform.lshc.service.JobAcceptService;

import javax.annotation.Resource;

/**
 * @author 3
 */
public class JobAcceptServiceImpl implements JobAcceptService {

    @Resource
    private JobAcceptMapper jobAcceptMapper;

    @Override
    public JobDto findByServiceNum(String serviceNum) {
        return jobAcceptMapper.selectByServiceNum(serviceNum);
    }
}
