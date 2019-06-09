package com.funi.platform.lshc.mapper.sys;

import com.funi.platform.lshc.entity.sys.SysConfType;

public interface SysConfTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysConfType record);

    int insertSelective(SysConfType record);

    SysConfType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysConfType record);

    int updateByPrimaryKey(SysConfType record);
}