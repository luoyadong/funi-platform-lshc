package com.funi.platform.lshc.service;

import com.funi.platform.lshc.query.MapHouseQuery;
import com.funi.platform.lshc.vo.MapHouseVo;

import java.util.List;

public interface MapHouseService {
    /**
     * 根据小区Id，查询房屋列表
     * @param query
     * @return 房屋列表
     */
    List<MapHouseVo> findMapHouseList(MapHouseQuery query);
}
