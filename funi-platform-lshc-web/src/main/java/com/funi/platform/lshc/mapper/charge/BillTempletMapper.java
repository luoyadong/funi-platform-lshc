package com.funi.platform.lshc.mapper.charge;

import com.funi.platform.lshc.entity.charge.BillTemplet;
import com.funi.platform.lshc.query.BillTempQuery;

import java.util.List;

public interface BillTempletMapper {
    int deleteByPrimaryKey(String id);

    int insert(BillTemplet record);

    int insertSelective(BillTemplet record);

    BillTemplet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BillTemplet record);

    int updateByPrimaryKey(BillTemplet record);

    List<BillTemplet> selectBillTempList(BillTempQuery billTempQuery);

    /**
     * 查询当前启用的票据模板
     * @return
     */
    List<BillTemplet> selectEnabledBillTempletList();

    /**
     * 更新所有的启用标志
     * @param record
     * @return
     */
    int updateAllUseFlag(BillTemplet record);

}