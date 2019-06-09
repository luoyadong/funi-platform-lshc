package com.funi.platform.lshc.mapper.house;

import com.funi.platform.lshc.entity.house.HouseProperty;

public interface HousePropertyMapper {
    int deleteByPrimaryKey(String id);

    int insert(HouseProperty record);

    int insertSelective(HouseProperty record);

    HouseProperty selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HouseProperty record);

    int updateByPrimaryKey(HouseProperty record);
}