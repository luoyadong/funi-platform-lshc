package com.funi.platform.lshc.mapper.house;

import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.entity.house.Region;
import com.funi.platform.lshc.vo.RegionVo;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("regionMapper")
public interface RegionMapper {

    /**
     * 查询全部区域
     * @return
     */
    List<ComboboxDto> selectRegionVoList();

    /**
     * 根据regionCode查询区域
     * @param regionCode
     * @return
     */
    RegionVo selectRegionVoById(Integer regionCode);

    int deleteByPrimaryKey(String id);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);
}