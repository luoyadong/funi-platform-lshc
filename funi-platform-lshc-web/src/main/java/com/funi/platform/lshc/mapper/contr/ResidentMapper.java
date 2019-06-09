package com.funi.platform.lshc.mapper.contr;

import com.funi.platform.lshc.dto.ResidentDto;
import com.funi.platform.lshc.entity.contr.Resident;
import com.funi.platform.lshc.query.ResidentQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResidentMapper {

    /**
     * 批量创建住户
     * @param residentList
     * @param contractId 合同id
     * @return
     */
    int batchInsert(@Param("list")List<Resident> residentList,@Param("contractId")String contractId);

    List<Resident> selectByContractId(@Param("contractId")String contractId,@Param("flag")int flag);

    /**
     * 插入结果数据
     * @param serviceNum
     * @param userId
     */
    void insertFinal(@Param("serviceNum")String serviceNum,@Param("userId")String userId,
                     @Param("id")String id,@Param("contractId")String contractId,@Param("isFinal")int isFinal,@Param("isFinalW")int isFinalW);

    List<ResidentDto> selectByQuery(ResidentQuery residentQuery);

}