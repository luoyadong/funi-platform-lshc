package com.funi.platform.lshc.mapper.census;

import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.entity.census.SysConfig;

import java.util.List;

public interface SysConfigMapper {
    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    List<ComboboxDto> selectSysConfDetaNameByType(String type);
}