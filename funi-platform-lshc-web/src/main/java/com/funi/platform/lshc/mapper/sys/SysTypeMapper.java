package com.funi.platform.lshc.mapper.sys;

import com.funi.platform.lshc.entity.sys.SysType;

public interface SysTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysType record);

    int insertSelective(SysType record);

    SysType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysType record);

    int updateByPrimaryKey(SysType record);
}