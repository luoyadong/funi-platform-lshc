package com.funi.platform.lshc.service;

import com.funi.platform.lshc.dto.ResidentDto;
import com.funi.platform.lshc.entity.contr.Resident;
import com.funi.platform.lshc.query.ResidentQuery;

import java.util.List;

/**
 * 住户业务层
 * @author 3
 */
public interface ResidentService {

    /**
     * 批量创建住户
     * @param residents
     * @param contractId 合同id
     */
    void createBatch(List<Resident> residents,String contractId);

    /**
     * 查询住户列表
     * @param residentQuery
     * @return
     */
    List<ResidentDto> findByQuery(ResidentQuery residentQuery);

    void createFinal(String serviceNum,String contractId,int isFinal,int isFinalW);
}
