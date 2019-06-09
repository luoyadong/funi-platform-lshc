package com.funi.platform.lshc.service.impl;

import com.funi.platform.lshc.dto.BizParameterDto;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.entity.sys.SysConfDeta;
import com.funi.platform.lshc.mapper.sys.SysConfDetaMapper;
import com.funi.platform.lshc.query.BizParameterQuery;
import com.funi.platform.lshc.service.BizParameterService;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 3
 */
public class BizParameterServiceImpl implements BizParameterService {

    @Resource
    private SysConfDetaMapper sysConfDetaMapper;
    @Override
    public int create(SysConfDeta record) {
        return sysConfDetaMapper.insert(record);
    }

    @Override
    public void remove(List<String> id) {
        sysConfDetaMapper.remove(id);
    }

    @Override
    public void modify(SysConfDeta record) {
        sysConfDetaMapper.modify(record);
    }

    @Override
    public List<BizParameterDto> findByQuery(BizParameterQuery query) {
        return sysConfDetaMapper.selectByQuery(query);
    }

    @Override
    public List<ComboboxDto> findAllType(Boolean isForm) {
        List<ComboboxDto> rtList = new ArrayList<>();
        List<ComboboxDto> comboboxDtoList = sysConfDetaMapper.selectAllType();
        if(CollectionUtils.isNotEmpty(comboboxDtoList)) {
            if(isForm == null || ! isForm) {
                ComboboxDto allDto = new ComboboxDto("全部", "");
                rtList.add(allDto);
            }
            rtList.addAll(comboboxDtoList);
        }
        return rtList;
    }

    @Override
    public SysConfDeta findById(String id) {
        return sysConfDetaMapper.selectById(id);
    }
}
