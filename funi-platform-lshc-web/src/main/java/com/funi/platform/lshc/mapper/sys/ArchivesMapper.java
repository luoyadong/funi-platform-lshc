package com.funi.platform.lshc.mapper.sys;

import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.entity.sys.Archives;
import com.funi.platform.lshc.query.ArchivesQuery;
import com.funi.platform.lshc.vo.ArchivesVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArchivesMapper {
    int deleteByPrimaryKey(String id);

    int insert(Archives record);

    int insertSelective(Archives record);

    Archives selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Archives record);

    int updateByPrimaryKey(Archives record);

    /**
     * 查询所有档案室
     * @return
     */
    List<ComboboxDto> selectAllArchive();

    /**
     * 分页查询档案室列表
     * @param archivesQuery
     * @return
     */
    List<ArchivesVo> selectArchivesVoList(ArchivesQuery archivesQuery);

    List<ArchivesVo> selectArchivesVoListById(@Param("id") String id, @Param("archivesName") String archivesName);

}