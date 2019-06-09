package com.funi.platform.lshc.mapper.house;

import com.funi.platform.lshc.entity.house.HouseOperate;

import java.util.List;

public interface HouseOperateMapper {
    int deleteByPrimaryKey(String id);

    int insert(HouseOperate record);

    int insertSelective(HouseOperate record);

    HouseOperate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HouseOperate record);

    int updateByPrimaryKey(HouseOperate record);

    List<HouseOperate> findHouseOperteList(String houseId);
}