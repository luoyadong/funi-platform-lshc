package com.funi.platform.lshc.mapper.census;

import com.funi.platform.lshc.entity.census.EntInfo;

import java.util.List;

public interface EntInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(EntInfo record);

    int insertSelective(EntInfo record);

    EntInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EntInfo record);

    int updateByPrimaryKey(EntInfo record);

    /**
     * 根据房屋编号查询入住人员信息
     * @param houseId
     * @return
     */
    List<EntInfo> selectEntInfoListByHouseId(String houseId);
}