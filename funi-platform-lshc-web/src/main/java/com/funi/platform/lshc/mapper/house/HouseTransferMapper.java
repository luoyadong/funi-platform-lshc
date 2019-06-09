package com.funi.platform.lshc.mapper.house;

import com.funi.platform.lshc.entity.house.HouseTransfer;

public interface HouseTransferMapper {
    int deleteByPrimaryKey(String id);

    int insert(HouseTransfer record);

    int insertSelective(HouseTransfer record);

    HouseTransfer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HouseTransfer record);

    int updateByPrimaryKey(HouseTransfer record);
}