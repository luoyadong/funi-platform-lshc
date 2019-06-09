package com.funi.platform.lshc.mapper.contr;

import com.funi.platform.lshc.entity.contr.ContrUpd;

public interface ContrUpdMapper {

    /**
     * 创建合同变更记录
     * @param record
     * @return
     */
    int insert(ContrUpd record);
}