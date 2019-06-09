package com.funi.platform.lshc.mapper.repair;

import com.funi.platform.lshc.entity.repair.Repair;
import com.funi.platform.lshc.query.RepairQuery;
import com.funi.platform.lshc.vo.RepairVo;

import java.util.List;

public interface RepairMapper {
    int deleteByPrimaryKey(String id);

    int insert(Repair record);

    int insertSelective(Repair record);

    Repair selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Repair record);

    int updateByPrimaryKey(Repair record);

    List<RepairVo> selectHouseRepairList(RepairQuery repairQuery);

    List<RepairVo> selectHouseUndoRepairList(RepairQuery repairQuery);

    List<RepairVo> selectHouseAlseRepairList(RepairQuery repairQuery);

    List<RepairVo> selectHouseRepairListByHouseId(String houseId);

    /**
     * 获取维修单的业务件号
     * @return
     */
    String generateRepairNum();
}