package com.funi.platform.lshc.mapper.charge;

import com.funi.platform.lshc.dto.ChargeDto;
import com.funi.platform.lshc.entity.charge.ChargeHis;
import com.funi.platform.lshc.query.charge.ArrearageQuery;
import com.funi.platform.lshc.query.charge.ChargeHistoryQuery;
import com.funi.platform.lshc.vo.ArrearageVo;
import com.funi.platform.lshc.vo.ChargeHistoryVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ChargeHisMapper {
    /**
     * 分页查询催缴合同列表
     * @param arrearageQuery
     * @return
     */
    List<ArrearageVo> selectArrearageVoList(ArrearageQuery arrearageQuery);

    /**
     * 查询收费记录列表
     * @param chargeHistoryQuery
     * @return
     */
    List<ChargeHistoryVo> selectChargeHistoryVoList(ChargeHistoryQuery chargeHistoryQuery);

    /**
     * 根据合同ID查询有效的收费记录
     * @param contractId
     * @return
     */
    BigDecimal selectPrepaidAmount(String contractId);

    /**
     * 根据合同ID，缴费起止时间判断是否存在缴费记录
     * @param chargeDto
     * @return
     */
    ChargeHis selectChargeHisByContractId(ChargeDto chargeDto);

    /**
     * 修改合同的最后缴费日期
     * @param contractId
     * @param lastRentDate
     */
    void updateLastRentDate(@Param("contractId") String contractId, @Param("lastRentDate") String lastRentDate);

    int deleteByPrimaryKey(String id);

    int insert(ChargeHis record);

    int insertSelective(ChargeHis record);

    ChargeHis selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ChargeHis record);

    int updateByPrimaryKey(ChargeHis record);
}