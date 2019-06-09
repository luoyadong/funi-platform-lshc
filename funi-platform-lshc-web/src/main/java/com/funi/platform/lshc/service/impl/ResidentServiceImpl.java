package com.funi.platform.lshc.service.impl;

import com.funi.platform.lshc.dto.ResidentDto;
import com.funi.platform.lshc.entity.contr.Resident;
import com.funi.platform.lshc.mapper.contr.ResidentMapper;
import com.funi.platform.lshc.query.ResidentQuery;
import com.funi.platform.lshc.service.ResidentService;
import com.funi.platform.lshc.support.UserManager;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author 3
 */
public class ResidentServiceImpl implements ResidentService {

    @Resource
    private ResidentMapper residentMapper;
    @Resource
    private UserManager userManager;

    @Override
    public void createBatch(List<Resident> residents,String contractId) {
        residentMapper.batchInsert(residents, contractId);
    }

    @Override
    public List<ResidentDto> findByQuery(ResidentQuery residentQuery) {
        return residentMapper.selectByQuery(residentQuery);
    }

    @Override
    public void createFinal(String serviceNum, String contractId,int isFinal,int isFinalW) {
        residentMapper.insertFinal(serviceNum, userManager.findUser().getUserId(), UUID.randomUUID().toString(), contractId,
                isFinal,isFinalW);
    }
}
