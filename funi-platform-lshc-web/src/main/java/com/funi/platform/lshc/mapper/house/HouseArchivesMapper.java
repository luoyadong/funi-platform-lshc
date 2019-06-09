package com.funi.platform.lshc.mapper.house;

import com.funi.platform.lshc.entity.house.HouseArchives;

public interface HouseArchivesMapper {
    int deleteByPrimaryKey(String id);

    int insert(HouseArchives record);

    int insertSelective(HouseArchives record);

    HouseArchives selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HouseArchives record);

    int updateByPrimaryKey(HouseArchives record);
}