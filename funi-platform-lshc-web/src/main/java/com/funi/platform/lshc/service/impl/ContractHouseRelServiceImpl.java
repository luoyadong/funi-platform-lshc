package com.funi.platform.lshc.service.impl;

import com.funi.platform.lshc.entity.contr.ContrHouse;
import com.funi.platform.lshc.mapper.contr.ContrHouseMapper;
import com.funi.platform.lshc.service.ContractHouseRelService;
import com.funi.platform.lshc.support.UserManager;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author 3
 */
public class ContractHouseRelServiceImpl implements ContractHouseRelService {

    @Resource
    private ContrHouseMapper contrHouseMapper;
    @Resource
    private UserManager userManager;

    @Override
    public void createBatch(List<ContrHouse> contractHouseList, String contractId) {
        contrHouseMapper.batchInsert(contractHouseList,contractId);
    }

    @Override
    public void createFinal(String serviceNum, String contractId,int isFinal,int isFinalW) {
        contrHouseMapper.insertFinal(serviceNum,userManager.findUser().getUserId(), UUID.randomUUID().toString(),contractId,
                isFinal,isFinalW);
    }
}
