package com.funi.platform.lshc.mapper.census;

import com.funi.platform.lshc.entity.census.File;
import org.apache.ibatis.annotations.Param;

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
     * @param hcId
     * @return
     */
    List<File> selectFileListByHouseId(String hcId);

    /**
     * 根据房屋编号集合逻辑删除房屋关联的附件信息
     * @param ids
     */
    void batchDeleteFile(@Param("ids") List<String> ids, @Param("userId") String userId);
}