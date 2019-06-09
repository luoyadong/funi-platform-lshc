package com.funi.platform.lshc.service;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.dto.ChargeDepositDto;
import com.funi.platform.lshc.dto.ChargeDto;
import com.funi.platform.lshc.query.charge.ArrearageQuery;
import com.funi.platform.lshc.query.charge.ChargeHistoryQuery;
import com.funi.platform.lshc.query.charge.ChargeQuery;
import com.funi.platform.lshc.query.charge.DepositQuery;
import com.funi.platform.lshc.vo.ArrearageVo;
import com.funi.platform.lshc.vo.BillVo;
import com.funi.platform.lshc.vo.ChargeDetailVo;
import com.funi.platform.lshc.vo.ChargeHistoryVo;
import com.funi.platform.lshc.vo.ChargeHouseVo;
import com.funi.platform.lshc.vo.ChargeVo;
import com.funi.platform.lshc.vo.DepositVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 收费业务逻辑处理类
 * Created by sam on 2018/11/4.11:16 PM
 */
public interface ChargeService {
    /**
     * 分页查询收费列表
     * @param chargeQuery
     * @return
     */
    List<ChargeVo> findChargeList(ChargeQuery chargeQuery, CurrentUser userInfo);

    /**
     * 根据合同ID查询合同房屋明细
     * @param contractId
     * @return
     */
    List<ChargeHouseVo> findChargeHouseVoList(String contractId);

    /**
     * 根据合同ID查询合同收费详情
     * @param contractId
     * @return
     */
    ChargeDetailVo findChargeDetailVo(String contractId, String chargeEndDate);

    /**
     * 根据合同ID批量查询合欠费单明细列表
     * @param contractIds
     * @return
     */
    List<ChargeDetailVo> findArrearsDetailList(CurrentUser userInfo, String [] contractIds);

    /**
     * 查询票据明细
     * @param chargeDto
     * @param userInfo
     * @return
     */
    BillVo createBill(ChargeDto chargeDto, CurrentUser userInfo);

    /**
     * 查询收费记录列表
     * @param chargeHistoryQuery
     * @return
     */
    List<ChargeHistoryVo> findChargeHistoryVoList(ChargeHistoryQuery chargeHistoryQuery, CurrentUser userInfo);

    /**
     * 退费操作
     * @param userInfo
     * @param historyId
     * @param backReason
     */
    void cancelCharge(CurrentUser userInfo, String historyId, String backReason);

    /**
     * 分页查询催缴合同列表
     * @param arrearageQuery
     * @return
     */
    List<ArrearageVo> findArrearageVoList(ArrearageQuery arrearageQuery, CurrentUser userInfo);

    /**
     * 根据查询条件导出欠费列表（不分页）
     * @param arrearageQuery
     * @param response
     */
    void exportArrearageVoList(ArrearageQuery arrearageQuery, HttpServletResponse response) throws Exception;

    /**
     * 分页查询保证金收取列表
     * @param depositQuery
     * @return
     */
    List<DepositVo> findDepositVoList(DepositQuery depositQuery, CurrentUser userInfo);

    /**
     * 根据查询条件导出保证金列表（不分页）
     * @param depositQuery
     * @param response
     */
    void exportDepositVoList(DepositQuery depositQuery, HttpServletResponse response) throws Exception;

    /**
     * 收取保证金
     * @param chargeDepositDto
     */
    void chargeDeposit(ChargeDepositDto chargeDepositDto);

    /**
     * 退回保证金
     * @param userInfo
     * @param contractId
     * @param refundReason
     */
    void refundDeposit(CurrentUser userInfo, String contractId, String refundReason);

    /**
     * 同步欠租记录
     */
    void syncArrearageDetail();

    /**
     * 根据合同ID集合，生成或更新欠费记录
     * @param contractIds
     */
    void generateArrearageHistory(String contractIds);

    /**
     * 获取当前启用的票据打印模板
     * @return
     */
    String findBillTemplateName();
}
