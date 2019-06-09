package com.funi.platform.lshc.service;

import com.funi.platform.lshc.dto.*;
import com.funi.platform.lshc.entity.contr.Contr;
import com.funi.platform.lshc.enumatation.ContractStatus;
import com.funi.platform.lshc.query.CheckQuery;
import com.funi.platform.lshc.query.ContractQuery;
import com.funi.platform.lshc.query.HouseQuery;

import java.util.List;

/**
 * @author 3
 */
public interface ContractService {

    /**
     * 创建合同
     * @param contr
     * @return 合同id
     */
    String create(Contr contr);

    /**
     * 查询房屋列表
     * @param houseQuery
     * @return
     */
    List<HouseDto> findHouseList(HouseQuery houseQuery);

    /**
     * 初始化合同信息
     * @param contractFormDto
     */
    void init(ContractFormDto contractFormDto);

    /**
     * 变更
     * @param contractFormDto
     */
    void change(ContractFormDto contractFormDto);

    /**
     * 续签
     * @param contractFormDto
     */
    void renew(ContractFormDto contractFormDto);

    /**
     * 分页查询合同列表
     * @param contractQuery
     * @return
     */
    List<ContractDto> findByQuery(ContractQuery contractQuery);

    /**
     * 待办件
     * @param contractQuery
     * @return
     */
    List<ContractDto> findUndoListByQuery(ContractQuery contractQuery);

    /**
     * 已办件
     * @param contractQuery
     * @return
     */
    List<ContractDto> findDoneListByQuery(ContractQuery contractQuery);

    /**
     * 生成合同号
     * @return
     */
    String findContractNo();

    /**
     * 生成业务件号
     * @return
     */
    String generateServiceNum();

    /**
     * 提交合同
     * @param jobLogDto
     */
    void commit(JobLogDto jobLogDto);

    /**
     * 根据id 查询合同详情
     * @param contractId 合同id
     * @param flag 结果数据标示
     * @return
     */
    ContractViewDto findById(String contractId,int flag);

    /**
     * 创建结果数据
     * @return
     */
    String createFinal(String serviceNum,String contractId,
                       ContractStatus contractStatus,int isFinal,int isFinalW,String newServiceNum);

    /**
     * 注销合同
     * @param serviceNum 原合同业务件号
     * @return
     */
    void cancel(String serviceNum);

    /**
     * 年审
     * @param checkQuery
     * @return
     */
    List<ContractDto> findCheckByQuery(CheckQuery checkQuery);

    /**
     * 验证是否为有效合同
     * @param serviceNum
     * @return
     */
    boolean isValidContract(String serviceNum);

    void modifyOldContract(ContractStatus contractStatus,String serviceNum,String invalidReason);

    /**
     * 移除合同
     * @param serviceNum
     * @param isFinal
     */
    void remove(String serviceNum,int isFinal);

    void overdue();

}
