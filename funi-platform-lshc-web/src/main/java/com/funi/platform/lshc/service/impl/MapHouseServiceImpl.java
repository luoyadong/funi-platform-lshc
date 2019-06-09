package com.funi.platform.lshc.service.impl;

import com.funi.platform.lshc.mapper.house.HouseInfoMapper;
import com.funi.platform.lshc.query.MapHouseQuery;
import com.funi.platform.lshc.service.MapHouseService;
import com.funi.platform.lshc.vo.MapHouseVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("mapHouseService")
public class MapHouseServiceImpl implements MapHouseService {
    @Resource
    private HouseInfoMapper houseInfoMapper;

    @Override
    public List<MapHouseVo> findMapHouseList(MapHouseQuery query) {
        List<MapHouseVo> rtList = houseInfoMapper.selectMapHouseList(query);
        return rtList;
    }
}
