package com.funi.platform.lshc.service;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.dto.ContractDto;
import com.funi.platform.lshc.dto.JobLogDto;
import com.funi.platform.lshc.entity.house.*;
import com.funi.platform.lshc.entity.repair.Repair;
import com.funi.platform.lshc.query.HouseListExcel;
import com.funi.platform.lshc.query.HouseListQuery;
import com.funi.platform.lshc.query.RepairQuery;
import com.funi.platform.lshc.vo.*;

import java.util.List;

public interface HouseService {
    void addHouseInfo(HouseInfo houseVo);

    void addHousePropety(HouseProperty houseProperty);

    void addHouseArchives(HouseArchives houseArchives);

    List<HouseVo> findHouseList(HouseListQuery query);

    void updateHouseInfo(HouseInfo houseVo);

    void updateHousePropety(HouseProperty houseProperty);

    void updateHouseArchives(HouseArchives houseArchives);

    HouseInfo selectHouseInfoById(String id);

    HouseProperty selectHousePropetyById(String id);

    HouseArchives selectHouseArchivesById(String id);

    List<AddressVo> findAddressVoList(String id, String keywords);

    List<ExcelHouseVo> selectHouseExcelList(HouseListExcel query);

    void updateHouseStatus(String id, String status);

    void addHouseLoss(HouseLoss houseLoss);

    HouseLoss findHouseLoss(String houseId);

    void addHouseTransfer(HouseTransfer houseTransfer);

    HouseTransfer findHouseTransfer(String houseId);

    void addHouseRepair(Repair repair);

    Repair findRepair(String id);

    List<HouseVo> getHouseSysNo(String id, String keyword);

    List<RepairVo> findHouseRepairList(RepairQuery repairQuery);

    List<RepairVo> findHouseUndoRepairList(RepairQuery repairQuery);

    List<RepairVo> findHouseAlseRepairList(RepairQuery repairQuery);

    void commit(JobLogDto jobLogDto);

    void updaateHouseRepair(Repair repair);

    void updateHouseRepair(Repair repair);

    List<HouseOperate> findHouseOperteList(String houseId);

    List<AddrChangeVo> findAddressChangeList(String houseId);

    List<ContractDto> findContractList(String houseId);

    List<RepairVo> getHouseRepairListByHouseId(String houseId);

    void createRightEnt(RightEnt rightEnt);

    List<RightEnt> findRightEntList(String houseId);

    void deleteRightEnt(String id);

    void updateHouseHasProperty(String houseId);

    void addHouseExcelDate(List<ExcelHouseInsetVo> queryList, CurrentUser user)  throws Exception;

    String findZlReportTitle(String contractId);

    String findHouseNoInfo(String houseNo);
}
