package com.funi.platform.lshc.mapper.contr;

import com.funi.platform.lshc.entity.contr.ContrTemplet;
import com.funi.platform.lshc.query.ContrTempQuery;

import java.util.List;

public interface ContrTempletMapper {

    /**
     * 新增合同模板
     * @param record
     * @return
     */
    int insert(ContrTemplet record);

    /**
     * 分页查询合同模板列表
     * @param query
     * @return
     */
    List<ContrTemplet> selectByQuery(ContrTempQuery query);

    /**
     * 更新模板状态
     * @param record
     * @return
     */
    int updateStatus(ContrTemplet record);

    int deleteByPrimaryKey(String id);
}