package com.funi.platform.lshc.mapper.charge;

import com.funi.platform.lshc.entity.charge.ArrearageHis;

public interface ArrearageHisMapper {
    int deleteByPrimaryKey(String id);

    int insert(ArrearageHis record);

    int insertSelective(ArrearageHis record);

    ArrearageHis selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArrearageHis record);

    int updateByPrimaryKey(ArrearageHis record);

    /**
     * 根据合同ID查询欠费记录
     * @param contractId
     * @return
     */
    ArrearageHis selectArrearageHisByContractId(String contractId);
}