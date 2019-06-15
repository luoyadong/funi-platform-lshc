package com.funi.platform.lshc.mapper.census;

import com.funi.platform.lshc.entity.census.File;

import java.util.List;

public interface FileMapper {
    int deleteByPrimaryKey(String id);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);

    /**
     * 根据房屋编号查询附件列表
     * @param houseId
     * @return
     */
    List<File> selectFileListByHouseId(String houseId);
}