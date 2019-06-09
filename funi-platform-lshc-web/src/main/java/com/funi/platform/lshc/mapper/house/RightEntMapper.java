package com.funi.platform.lshc.mapper.house;

import com.funi.platform.lshc.entity.house.RightEnt;

import java.util.List;

public interface RightEntMapper {
    int deleteByPrimaryKey(String id);

    int insert(RightEnt record);

    int insertSelective(RightEnt record);

    RightEnt selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RightEnt record);

    int updateByPrimaryKey(RightEnt record);

    List<RightEnt> selectRightEntList(String houseId);
}