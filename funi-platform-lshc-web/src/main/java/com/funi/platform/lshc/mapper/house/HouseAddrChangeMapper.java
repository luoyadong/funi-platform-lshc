package com.funi.platform.lshc.mapper.house;

import com.funi.platform.lshc.entity.house.HouseAddrChange;

public interface HouseAddrChangeMapper {
    int deleteByPrimaryKey(String id);

    int insert(HouseAddrChange record);

    int insertSelective(HouseAddrChange record);

    HouseAddrChange selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HouseAddrChange record);

    int updateByPrimaryKey(HouseAddrChange record);
}