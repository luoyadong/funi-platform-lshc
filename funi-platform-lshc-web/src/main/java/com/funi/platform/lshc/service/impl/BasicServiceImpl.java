package com.funi.platform.lshc.service.impl;

import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.service.BasicService;

import java.util.List;

/**
 * Created by sam on 2019/6/17.3:56 PM
 */
public class BasicServiceImpl implements BasicService {
    @Override
    public List<ComboboxDto> findAllCityList() {
        return null;
    }

    @Override
    public List<ComboboxDto> findAllRegionListByCityId(String cityId) {
        return null;
    }

    @Override
    public List<ComboboxDto> findAllBlockListByRegionId(String regionId) {
        return null;
    }
}
