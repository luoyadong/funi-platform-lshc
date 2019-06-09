package com.funi.platform.lshc.service;

import com.funi.platform.lshc.dto.BizParameterDto;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.entity.sys.SysConfDeta;
import com.funi.platform.lshc.query.BizParameterQuery;

import java.util.List;

/**
 * @author 3
 */
public interface BizParameterService {

    void remove(List<String> id);

    int create(SysConfDeta record);

    void modify(SysConfDeta record);

    List<BizParameterDto> findByQuery(BizParameterQuery query);

    List<ComboboxDto> findAllType(Boolean isForm);

    SysConfDeta findById(String id);
}
