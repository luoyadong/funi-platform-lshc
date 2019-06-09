package com.funi.platform.lshc.mapper.sys;

import com.funi.platform.lshc.dto.BizParameterDto;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.entity.sys.SysConfDeta;
import com.funi.platform.lshc.query.BizParameterQuery;

import java.util.List;

public interface SysConfDetaMapper {

    void remove(List<String> id);

    int insert(SysConfDeta record);

    void modify(SysConfDeta record);

    List<BizParameterDto> selectByQuery(BizParameterQuery query);

    List<ComboboxDto> selectAllType();

    SysConfDeta selectById(String id);

    /**
     * 根据系统业务参数类型查询全部业务参数明细
     * @param type
     * @return
     */
    List<ComboboxDto> selectSysConfDetaByType(String type);

    List<ComboboxDto> selectSysConfDetaNameByType(String type);
}