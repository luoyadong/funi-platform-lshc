package com.funi.platform.lshc.mapper.house;

import com.funi.platform.lshc.entity.house.AddrChange;
import com.funi.platform.lshc.query.address.AddressChangeQuery;
import com.funi.platform.lshc.vo.AddrChangeVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("addrChangeMapper")
public interface AddrChangeMapper {

    /**
     * 分页查询小区地址修改历史记录
     * @param addressChangeQuery
     * @return
     */
    List<AddrChangeVo> selectAddressChangeList(AddressChangeQuery addressChangeQuery);

    int deleteByPrimaryKey(String id);

    int insert(AddrChange record);

    int insertSelective(AddrChange record);

    AddrChange selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AddrChange record);

    int updateByPrimaryKey(AddrChange record);

    List<AddrChangeVo> selectAddressChangeListByHouseId(String houseId);
}