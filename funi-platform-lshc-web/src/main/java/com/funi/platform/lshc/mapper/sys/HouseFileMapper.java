package com.funi.platform.lshc.mapper.sys;

import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.entity.sys.HouseFile;
import com.funi.platform.lshc.query.HouseFileQuery;
import com.funi.platform.lshc.vo.HouseFileVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseFileMapper {
    int deleteByPrimaryKey(String id);

    int insert(HouseFile record);

    int insertSelective(HouseFile record);

    HouseFile selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HouseFile record);

    int updateByPrimaryKey(HouseFile record);

    /**
     * 根据档案室ID查询全部档案柜集合
     * @param houseFileQuery
     * @return
     */
    List<HouseFileVo> selectHouseFileListByArchivesId(HouseFileQuery houseFileQuery);

    List<HouseFile> selectHouseFileList(@Param("id") String id, @Param("archivesId") String archivesId, @Param("fileName") String fileName);

    /**
     * 根据档案室查询档案柜
     * @param archivesId
     * @return
     */
    List<ComboboxDto> selectByArchiveId(String archivesId);

    /**
     * 根据档案室ID删除全部档案柜
     * @param archivesId
     * @return
     */
    int deleteByArchivesId(@Param("archivesId") String archivesId, @Param("operateUserId") String operateUserId);
}