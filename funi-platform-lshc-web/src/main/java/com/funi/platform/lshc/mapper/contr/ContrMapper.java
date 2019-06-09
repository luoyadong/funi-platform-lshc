package com.funi.platform.lshc.mapper.contr;

import com.funi.platform.lshc.dto.ContractDto;
import com.funi.platform.lshc.dto.LinkFileDto;
import com.funi.platform.lshc.entity.contr.Contr;
import com.funi.platform.lshc.query.CheckQuery;
import com.funi.platform.lshc.query.ContractQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContrMapper {

    /**
     * 新增合同
     * @param record
     * @return
     */
    int insert(Contr record);

    /**
     * 分页查询合同列表
     * @param contractQuery
     * @return
     */
    List<ContractDto> selectByQuery(ContractQuery contractQuery);

    /**
     * 年审
     * @param checkQuery
     * @return
     */
    List<ContractDto> selectCheckByQuery(CheckQuery checkQuery);

    /**
     *
     * @param contractQuery
     * @return
     */
    List<ContractDto> selectUndoListByQuery(ContractQuery contractQuery);

    /**
     *
     * @param contractQuery
     * @return
     */
    List<ContractDto> selectDoneListByQuery(ContractQuery contractQuery);

    /**
     * 根据序列提前合同号
     * @return
     */
    String generateContractNo();

    /**
     * 根据序列参数业务件号
     * @return
     */
    String generateServiceNum();

    /**
     * 根据主键ID查询合同对象
     * @param id
     * @return
     */
    Contr selectByPrimaryKey(String id);

    /**
     * 根据合同id查询合同
     * @param contractId
     * @param flag
     * @return
     */
    Contr selectById(@Param("id")String contractId,@Param("flag")int flag);

    /**
     * @param serviceNum
     * @param flag
     * @return
     */
    Contr selectByServiceNum(@Param("serviceNum")String serviceNum,@Param("flag")int flag);

    /**
     * 插入结果数据
     * @param serviceNum
     * @param userId
     */
    void insertFinal(@Param("serviceNum")String serviceNum,@Param("userId")String userId,
                     @Param("id")String id,@Param("lesseeId")String lesseeId,
                     @Param("contractStatus")String status,@Param("isFinal")int isFinal,
                     @Param("isFinalW")int isFinalW,@Param("newServiceNum")String newServiceNum);

    void modifyStatus(@Param("serviceNum")String serviceNum,@Param("isFinal")int isFinal,
                      @Param("contractStatus")String contractStatus);

    String selectContractNo(@Param("serviceNum")String serviceNum,@Param("isFinal")int isFinal);

    void modifyStatusByContractNo(@Param("contractNo")String contractNo,@Param("isFinal")int isFinal,
                      @Param("contractStatus")String contractStatus);

    void invalid(@Param("contractNo")String contractNo,@Param("isFinal")int isFinal,
                 @Param("contractStatus")String contractStatus,@Param("invalidReason")String invalidReason);

    List<ContractDto> selectContractListByHouseId(String houseId);

    void modifyOldContract(@Param("contractStatus")String contractStatus,@Param("serviceNum")String serviceNum,@Param("invalidReason")String invalidReason);

    LinkFileDto selectLinkFileDto(@Param("serviceNum")String serviceNum,@Param("isFinal")int isFinal);

    void remove(@Param("serviceNum")String serviceNum,@Param("isFinal")int isFinal);

    void overdue();
}