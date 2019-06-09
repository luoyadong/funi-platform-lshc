package com.funi.platform.lshc.mapper.contr;

import com.funi.platform.lshc.dto.HouseDto;
import com.funi.platform.lshc.entity.contr.ContrHouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContrHouseMapper {

    /**
     * 创建房屋合同关系
     * @param contrHouseList
     * @param contractId 合同id
     * @return
     */
    int batchInsert(@Param("list")List<ContrHouse> contrHouseList,@Param("contractId")String contractId);

    List<HouseDto> selectByContractId(@Param("contractId")String contractId,@Param("flag")int flag);

    /**
     * 插入结果数据
     * @param serviceNum
     * @param userId
     */
    void insertFinal(@Param("serviceNum")String serviceNum,@Param("userId")String userId,
                     @Param("id")String id,@Param("contractId")String contractId,@Param("isFinal")int isFinal,@Param("isFinalW")int isFinalW);

    List<ContrHouse> selectBoByContractId(@Param("contractId")String contractId,@Param("flag")int flag);

    List<ContrHouse> selectBoByServiceNum(@Param("serviceNum")String serviceNum,@Param("flag")int flag);
}