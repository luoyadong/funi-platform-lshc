package com.funi.platform.lshc.mapper.census;

import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.entity.census.SysConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysConfigMapper {
    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    List<ComboboxDto> selectSysConfDetaNameByType(@Param("tableName") String tableName, @Param("type") String type);
}