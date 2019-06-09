package com.funi.platform.lshc.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.entity.house.AddrChange;
import com.funi.platform.lshc.entity.house.AddrInfo;
import com.funi.platform.lshc.entity.sys.LinkFile;
import com.funi.platform.lshc.enumatation.IsChange;
import com.funi.platform.lshc.enumatation.UploadBusinessType;
import com.funi.platform.lshc.mapper.house.AddrChangeMapper;
import com.funi.platform.lshc.mapper.house.AddrInfoMapper;
import com.funi.platform.lshc.mapper.house.RegionMapper;
import com.funi.platform.lshc.mapper.sys.LinkFileMapper;
import com.funi.platform.lshc.query.address.AddressChangeQuery;
import com.funi.platform.lshc.query.address.AddressQuery;
import com.funi.platform.lshc.service.AddressService;
import com.funi.platform.lshc.utils.BaseEntityUtils;
import com.funi.platform.lshc.vo.AddrChangeVo;
import com.funi.platform.lshc.vo.AddressEditVo;
import com.funi.platform.lshc.vo.AddressVo;
import com.funi.platform.lshc.vo.LinkFileVo;
import com.funi.platform.lshc.vo.RegionVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sam on 2018/11/6.11:12 PM
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);
    @Resource
    private AddrInfoMapper addrInfoMapper;
    @Resource
    private AddrChangeMapper addrChangeMapper;
    @Resource
    private LinkFileMapper linkFileMapper;
    @Resource
    private RegionMapper regionMapper;

    @Override
    public void createAddress(AddrInfo addrInfo, CurrentUser userInfo) {
        // 设置当前登录用户的机构编码
        addrInfo.setBizCreateOrgCode(userInfo.getOrganization().getDm());
        addrInfoMapper.insert(addrInfo);
    }

    @Override
    public List<AddressVo> findAddressVoList(AddressQuery addressQuery, CurrentUser userInfo) {
        List<AddressVo> addressVoList = addrInfoMapper.selectAddressVoList(addressQuery);
        // 进行数据权限处理
        if(CollectionUtils.isNotEmpty(addressVoList)) {
            // 当前登录用户的机构
            for (AddressVo addressVo : addressVoList) {
                Map<String, Boolean> authorityMap = new HashMap<>();
                authorityMap.put("AUTH_GHOUSE_ADDRESS_HISTORY", false);
                // 设置查看链接
                Integer isChange = addressVo.getIsChange();
                if (isChange != null) {
                    if(IsChange.CHANGED.getChangeStatus().equals(isChange)) {
                        authorityMap.put("AUTH_GHOUSE_ADDRESS_HISTORY", true);
                    }
                }
                addressVo.setAuthorityMap(authorityMap);
            }
        }
        return addressVoList;
    }

    @Override
    public AddrInfo findAddrInfo(String addressId) {
        return addrInfoMapper.selectByPrimaryKey(addressId);
    }

    @Override
    public void updateAddress(AddressEditVo addressEditVo, CurrentUser currentUser) {
        LOGGER.info("开始修改小区，传入参数：" + addressEditVo);
        String id = addressEditVo.getId();
        if(StringUtils.isBlank(id)) {
            LOGGER.error("小区ID不能为空");
            throw new RuntimeException("小区ID不能为空");
        }
        AddrInfo oldAddrInfo = addrInfoMapper.selectByPrimaryKey(id);
        if (oldAddrInfo == null) {
            LOGGER.error("小区地址不存在或者ID错误");
            throw new RuntimeException("小区地址不存在或者ID错误");
        }
        // 如果小区区域ID、街道、号、经度、纬度均未发生变化，不予更新
        AddrInfo editAddrInfo = new AddrInfo(addressEditVo);
        editAddrInfo.setVersion(oldAddrInfo.getVersion());
        if(oldAddrInfo.equals(editAddrInfo)) {
            LOGGER.error("小区地址没有变化，修改失败");
            throw new RuntimeException("小区地址没有变化，修改失败");
        }
        editAddrInfo.setIsChange(IsChange.CHANGED.getChangeStatus());
        new BaseEntityUtils<AddrInfo>().buildModifyEntity(editAddrInfo, currentUser);
        // 修改小区状态为已变更并保存变更后的小区信息
        addrInfoMapper.updateByPrimaryKeySelective(editAddrInfo);
        String oldRegionName = "";
        String newRegionName = "";
        RegionVo oldRegionVo = regionMapper.selectRegionVoById(Integer.parseInt(oldAddrInfo.getZoneId()));
        if (oldRegionVo != null) {
            oldRegionName = oldRegionVo.getName();
        }
        String zoneId = addressEditVo.getZoneId();
        RegionVo newRegionVo = regionMapper.selectRegionVoById(Integer.parseInt(zoneId));
        if (newRegionVo != null) {
            newRegionName = newRegionVo.getName();
        }

        // 添加小区地址变更记录
        AddrChange addrChange = new AddrChange(oldAddrInfo, editAddrInfo, oldRegionName, newRegionName);
        addrChange.setChangeUser(currentUser.getUsername());
        new BaseEntityUtils<AddrChange>().buildCreateEntity(addrChange, currentUser);
        addrChangeMapper.insert(addrChange);

        // 记录要件信息（如果有要件）
        List<LinkFileVo> linkFileVoList = addressEditVo.getLinkFileVoList();
        if(CollectionUtils.isNotEmpty(linkFileVoList)) {
            for(LinkFileVo linkFileVo : linkFileVoList) {
                LinkFile linkFile = new LinkFile(addrChange.getId(), linkFileVo, UploadBusinessType.ADDRESS_CHANGE);
                new BaseEntityUtils<LinkFile>().buildCreateEntity(linkFile, currentUser);
                linkFileMapper.insert(linkFile);
            }
        }
        LOGGER.info("成功修改小区信息");
    }

    @Override
    public List<AddrChangeVo> findAddressChangeList(AddressChangeQuery addressChangeQuery) {
        return addrChangeMapper.selectAddressChangeList(addressChangeQuery);
    }

    @Override
    public void removeAddress(AddrInfo addrInfo) {
        // 注销小区前需要判断是否还关联房屋
        int houseCount = addrInfoMapper.selectAvailableHouseCount(addrInfo);
        if(houseCount > 0) {
            throw new RuntimeException("小区关联未注销房屋数量：" + houseCount + "，请处理后再注销");
        }
        addrInfoMapper.updateByPrimaryKeySelective(addrInfo);
    }

    @Override
    public List<ComboboxDto> findByQueryForComplete(AddressQuery addressQuery) {
        return addrInfoMapper.autoComplete(addressQuery);
    }
}
