package com.funi.platform.lshc.service;

import com.funi.platform.lshc.dto.ComboboxDto;

import java.util.List;

/**
 * Created by sam on 2019/6/17.3:56 PM
 */
public interface BasicService {
    /**
     * 查询西藏全部市信息
     * @return
     */
    List<ComboboxDto> findAllRegionList();

    /**
     * 根据市ID查询全部区县信息
     * @param cityId
     * @return
     */
    List<ComboboxDto> findAllRegionListByCityId(String cityId);

    /**
     * 根据区县ID查询全部街道信息
     * @param regionId
     * @return
     */
    List<ComboboxDto> findAllStreetListByRegionId(String regionId);
}

