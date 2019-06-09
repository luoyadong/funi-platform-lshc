package com.funi.platform.lshc.mapper.charge;

import com.funi.platform.lshc.entity.charge.Deposit;
import com.funi.platform.lshc.query.charge.ChargeQuery;
import com.funi.platform.lshc.query.charge.DepositQuery;
import com.funi.platform.lshc.vo.ChargeDetailVo;
import com.funi.platform.lshc.vo.ChargeHouseVo;
import com.funi.platform.lshc.vo.ChargeVo;
import com.funi.platform.lshc.vo.DepositVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepositMapper {

    /**
     * 分页查询保证金收取列表
     * @param depositQuery
     * @return
     */
    List<DepositVo> selectDepositVoList(DepositQuery depositQuery);

    /**
     * 根据搜索条件分页查询满足条件的收费合同记录
     * @param chargeQuery
     * @return
     */
    List<ChargeVo> selectChargeList(ChargeQuery chargeQuery);

    /**
     * 根据合同ID查询合同房屋明细
     * @param contractId
     * @return
     */
    List<ChargeHouseVo> selectChargeHouseVoList(String contractId);

    /**
     * 根据合同ID查询合同收费详情
     * @param contractIdList
     * @return
     */
    List<ChargeDetailVo> selectChargeDetailVo(@Param("contractIdList") List<String> contractIdList);

    int deleteByPrimaryKey(String id);

    int insert(Deposit record);

    int insertSelective(Deposit record);

    Deposit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Deposit record);

    int updateByPrimaryKey(Deposit record);

    /**
     * 根据合同ID查询保证金收费记录
     * @param contractId
     * @return
     */
    Deposit selectByContractId(String contractId);

    /**
     * 根据合同ID和保证金状态修改合同的保证金收取状态
     * @param contractId
     * @param depositStatus
     */
    void updateContractDepositStatus(@Param("contractId") String contractId, @Param("depositStatus") String depositStatus);
}