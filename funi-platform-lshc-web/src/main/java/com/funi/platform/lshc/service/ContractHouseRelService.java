package com.funi.platform.lshc.service;

import com.funi.platform.lshc.entity.contr.ContrHouse;

import java.util.List;

/**
 * 房屋合同关系业务层
 * @author 3
 */
public interface ContractHouseRelService {

    /**
     * 批量创建房屋合同关系
     * @param contractHouseList
     * @param contractId
     */
    void createBatch(List<ContrHouse> contractHouseList,String contractId);

    /**
     * 插入结果数据
     * @param serviceNum
     */
    void createFinal(String serviceNum,String contractId,int isFinal,int isFinalW);
}
