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
     * 根据普查信息ID逻辑删除全部关联的附件信息
     * @param houseId
     * @return
     */
    int deleteAllFileByHouseId(@Param("houseId")String houseId, @Param("userId") String userId);

    /**
     * 根据普查信息ID和例外的附件ID，逻辑删除符合条件的附件信息
     * @param houseId
     * @param ids
     * @return
     */
    int deleteFileByExceptIds(@Param("houseId") String houseId, @Param("ids") List<String> ids, @Param("userId") String userId);

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