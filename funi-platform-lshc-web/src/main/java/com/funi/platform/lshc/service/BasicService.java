package com.funi.platform.lshc.service;

import com.funi.platform.lshc.dto.ComboboxDto;

import java.util.List;

/**
 * Created by sam on 2019/6/17.3:56 PM
 */
public interface BasicService {

    /**
     * 返回以name为key，以name为value
     * @param type
     * @return
     */
    List<ComboboxDto> findDictionaryListName(String type);

    /**
     * 查询西藏全部市信息
     * @return
     */
    List<ComboboxDto> findRegionList();

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

    /**
     * 获取当前用户所属的区域code
     * @param userId
     * @return
     */
    String findCurrentUserRegionCode(String userId);

    /**
     * 获取当前用户所属的区域名称
     * @param userId
     * @return
     */
    String findCurrentUserRegionName(String userId);

    /**
     * 获取当前用户权限范围内的区域code集合（包括自身的）
     * @param userId
     * @return
     */
    List<String> findCurrentUserRegionCodeList(String userId);

    /**
     * 获取当前登录用户权限范围内全部区域编码的字符串表示，多个社区编码间使用逗号分隔
     * @return
     */
    String findCurrentUserRegionCodeString();
}

