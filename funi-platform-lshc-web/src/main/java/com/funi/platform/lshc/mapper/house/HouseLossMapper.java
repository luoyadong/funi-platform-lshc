package com.funi.platform.lshc.mapper.house;

import com.funi.platform.lshc.entity.house.HouseLoss;

public interface HouseLossMapper {
    int deleteByPrimaryKey(String id);

    int insert(HouseLoss record);

    int insertSelective(HouseLoss record);

    HouseLoss selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HouseLoss record);

    int updateByPrimaryKey(HouseLoss record);
}