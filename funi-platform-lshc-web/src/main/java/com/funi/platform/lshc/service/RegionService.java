package com.funi.platform.lshc.service;

import com.funi.platform.lshc.dto.ComboboxDto;

import java.util.List;

/**
 * Created by sam on 2018/11/6.10:32 PM
 */
public interface RegionService {
    /**
     * 查询全部区域
     * @return
     */
    List<ComboboxDto> findRegionVoList(Boolean isForm);
}
