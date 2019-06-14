package com.funi.platform.lshc.mapper.census;

import com.funi.platform.lshc.entity.census.EntInfo;

public interface EntInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(EntInfo record);

    int insertSelective(EntInfo record);

    EntInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EntInfo record);

    int updateByPrimaryKey(EntInfo record);
}