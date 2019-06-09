package com.funi.platform.lshc.service;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.entity.house.AddrInfo;
import com.funi.platform.lshc.query.address.AddressChangeQuery;
import com.funi.platform.lshc.query.address.AddressQuery;
import com.funi.platform.lshc.vo.AddrChangeVo;
import com.funi.platform.lshc.vo.AddressEditVo;
import com.funi.platform.lshc.vo.AddressVo;

import java.util.List;

/**
 * Created by sam on 2018/11/6.11:12 PM
 */
public interface AddressService {
    /**
     * 添加小区
     * @param addrInfo
     */
    void createAddress(AddrInfo addrInfo, CurrentUser userInfo);

    /**
     * 分页查询小区列表
     * @param addressQuery
     * @return
     */
    List<AddressVo> findAddressVoList(AddressQuery addressQuery, CurrentUser userInfo);

    /**
     * 根据小区ID查询小区详情
     * @param addressId
     * @return
     */
    AddrInfo findAddrInfo(String addressId);

    /**
     * 修改小区地址
     * @param addressEditVo
     */
    void updateAddress(AddressEditVo addressEditVo, CurrentUser currentUser);

    /**
     * 分页查询小区地址修改历史记录
     * @param addressChangeQuery
     * @return
     */
    List<AddrChangeVo> findAddressChangeList(AddressChangeQuery addressChangeQuery);

    /**
     * 注销小区（逻辑删除小区）
     * @param addrInfo
     */
    void removeAddress(AddrInfo addrInfo);

    /**
     * 自动补全小区
     * creator 3
     * @param addressQuery
     * @return
     */
    List<ComboboxDto> findByQueryForComplete(AddressQuery addressQuery);

}
