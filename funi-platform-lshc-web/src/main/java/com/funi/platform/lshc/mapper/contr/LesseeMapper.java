package com.funi.platform.lshc.mapper.contr;

import com.funi.platform.lshc.entity.contr.Lessee;
import org.apache.ibatis.annotations.Param;

public interface LesseeMapper {

    /**
     * 创建承租人
     * @param record
     * @return
     */
    int insert(Lessee record);

    Lessee selectByContractId(@Param("contractId")String contractId,@Param("flag")int flag);

    /**
     * 插入结果数据
     * @param serviceNum
     * @param userId
     */
    void insertFinal(@Param("serviceNum")String serviceNum,@Param("userId")String userId,
                     @Param("id")String id,@Param("isFinal")int isFinal,@Param("isFinalW")int isFinalW);

}