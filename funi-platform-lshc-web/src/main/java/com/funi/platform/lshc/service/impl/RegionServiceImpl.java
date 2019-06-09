package com.funi.platform.lshc.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.mapper.house.RegionMapper;
import com.funi.platform.lshc.service.RegionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 2018/11/6.10:33 PM
 */
@Service("regionService")
public class RegionServiceImpl implements RegionService {
    @Resource
    private RegionMapper regionMapper;

    @Override
    public List<ComboboxDto> findRegionVoList(Boolean isForm) {
        List<ComboboxDto> rtList = new ArrayList<>();
        List<ComboboxDto> comboboxDtoList = regionMapper.selectRegionVoList();
        if(CollectionUtils.isNotEmpty(comboboxDtoList)) {
            if(isForm == null || ! isForm) {
                ComboboxDto allDto = new ComboboxDto("全部", "");
                rtList.add(allDto);
            }
            rtList.addAll(comboboxDtoList);
        }
        return rtList;
    }
}
