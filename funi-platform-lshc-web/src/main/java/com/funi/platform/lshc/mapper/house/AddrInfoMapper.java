package com.funi.platform.lshc.mapper.house;

import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.entity.house.AddrInfo;
import com.funi.platform.lshc.query.address.AddressQuery;
import com.funi.platform.lshc.vo.AddressVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("addrInfoMapper")
public interface AddrInfoMapper {
    /**
     * 分页查询小区列表
     * @param addressQuery
     * @return
     */
    List<AddressVo> selectAddressVoList(AddressQuery addressQuery);

    /**
     * 逻辑删除小区
     * @param id
     * @return
     */
    int logicalDelete(String id);

    int deleteByPrimaryKey(String id);

    int insert(AddrInfo record);

    int insertSelective(AddrInfo record);

    AddrInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AddrInfo record);

    int updateByPrimaryKey(AddrInfo record);

    /**
     *
     * @param addressQuery
     * @return
     */
    List<ComboboxDto> autoComplete(AddressQuery addressQuery);

    /**
     * 根据小区ID查询有效房屋数量
     * @param addrInfo
     * @return
     */
    int selectAvailableHouseCount(AddrInfo addrInfo);
}