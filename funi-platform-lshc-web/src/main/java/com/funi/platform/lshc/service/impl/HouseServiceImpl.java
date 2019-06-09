package com.funi.platform.lshc.service.impl;

import com.funi.framework.biz.BizException;
import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.framework.utils.StringUtils;
import com.funi.framework.workflow.eic.service.WorkFlowService;
import com.funi.platform.lshc.dto.ContractDto;
import com.funi.platform.lshc.dto.JobDto;
import com.funi.platform.lshc.dto.JobLogDto;
import com.funi.platform.lshc.entity.house.*;
import com.funi.platform.lshc.entity.repair.Repair;
import com.funi.platform.lshc.enumatation.BusinessType;
import com.funi.platform.lshc.enumatation.Node;
import com.funi.platform.lshc.mapper.contr.ContrMapper;
import com.funi.platform.lshc.mapper.house.*;
import com.funi.platform.lshc.mapper.repair.RepairMapper;
import com.funi.platform.lshc.query.HouseListExcel;
import com.funi.platform.lshc.query.HouseListQuery;
import com.funi.platform.lshc.query.RepairQuery;
import com.funi.platform.lshc.service.HouseService;
import com.funi.platform.lshc.service.JobAcceptService;
import com.funi.platform.lshc.service.JobLogService;
import com.funi.platform.lshc.support.UserManager;
import com.funi.platform.lshc.utils.DateUtils;
import com.funi.platform.lshc.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("houseService")
public class HouseServiceImpl implements HouseService {
    @Resource
    private HouseInfoMapper houseInfoMapper;
    @Resource
    private HousePropertyMapper housePropertyMapper;
    @Resource
    private HouseArchivesMapper houseArchivesMapper;
    @Resource
    private HouseLossMapper houseLossMapper;
    @Resource
    private HouseTransferMapper houseTransferMapper;
    @Resource
    private RepairMapper repairMapper;
    @Resource
    private HouseOperateMapper houseOperateMapper;

    @Resource
    private UserManager userManager;

    @Resource
    private JobAcceptService jobAcceptService;

    @Resource
    private WorkFlowService workFlowService;

    @Resource
    private JobLogService jobLogService;

    @Resource
    private AddrChangeMapper addrChangeMapper;
    @Resource
    private ContrMapper contrMapper;
    @Resource
    private RightEntMapper rightEntMapper;

    //
    private void addLogHouse(int i,String houseId){
        //添加操作记录
        HouseOperate houseOperate=new HouseOperate();
        houseOperate.setId(UUID.randomUUID().toString());
        houseOperate.setHouseId(houseId);
        houseOperate.setOperateDate(DateUtils.parseFormatDate(new Date()));
        houseOperate.setOperateUser(userManager.findUser().getName());
        String operteType=null;
        if(i==1){
            operteType="新增";
        }else if(i==2){
            operteType="修改";
        }else if(i==3){
            operteType="注销";
        }else if(i==4){
            operteType="移交";
        }
        houseOperate.setOperateType(operteType);
        houseOperateMapper.insert(houseOperate);
    }

    @Override
    public void addHouseInfo(HouseInfo houseVo) {


        houseVo.setId(UUID.randomUUID().toString());
        addLogHouse(1,houseVo.getId());
        houseInfoMapper.insert(houseVo);
    }

    @Override
    public void addHousePropety(HouseProperty houseProperty) {
        houseProperty.setId(UUID.randomUUID().toString());
        housePropertyMapper.insert(houseProperty);
    }

    @Override
    public void addHouseArchives(HouseArchives houseArchives) {
        houseArchives.setId(UUID.randomUUID().toString());
        houseArchivesMapper.insert(houseArchives);
    }

    @Override
    public List<HouseVo> findHouseList(HouseListQuery query) {
        return houseInfoMapper.selectHouseList(query);
    }

    @Override
    public void updateHouseInfo(HouseInfo houseVo) {
        houseVo.setHouseNo(null);
        houseVo.setHourseNoPrefix(null);
        houseVo.setHouseNoSys(null);
        addLogHouse(2,houseVo.getId());
        houseInfoMapper.updateByPrimaryKeySelective(houseVo);
    }

    @Override
    public void updateHousePropety(HouseProperty houseProperty) {
        housePropertyMapper.updateByPrimaryKeySelective(houseProperty);
    }

    @Override
    public void updateHouseArchives(HouseArchives houseArchives) {
        houseArchivesMapper.updateByPrimaryKeySelective(houseArchives);
    }

    @Override
    public HouseInfo selectHouseInfoById(String id) {
        return houseInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public HouseProperty selectHousePropetyById(String id) {
        return housePropertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public HouseArchives selectHouseArchivesById(String id) {
        return houseArchivesMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AddressVo> findAddressVoList(String id, String keywords) {
        return houseInfoMapper.selectAdddressList(id,keywords);
    }

    @Override
    public List<ExcelHouseVo> selectHouseExcelList(HouseListExcel query) {
        return houseInfoMapper.selectHouseExcelList(query);
    }

    @Override
    public void updateHouseStatus(String id, String status) {
        houseInfoMapper.updateHouseStatus(id,status);
    }

    @Override
    public void addHouseLoss(HouseLoss houseLoss) {

        houseLoss.setId(UUID.randomUUID().toString());
        addLogHouse(3,houseLoss.getHouseId());
        houseLossMapper.insert(houseLoss);
    }

    @Override
    public HouseLoss findHouseLoss(String houseId) {
        return houseLossMapper.selectByPrimaryKey(houseId);
    }

    @Override
    public void addHouseTransfer(HouseTransfer houseTransfer) {
        houseTransfer.setId(UUID.randomUUID().toString());
        addLogHouse(4,houseTransfer.getHouseId());
        houseTransferMapper.insert(houseTransfer);
    }

    @Override
    public HouseTransfer findHouseTransfer(String houseId) {
        return houseTransferMapper.selectByPrimaryKey(houseId);
    }

    @Override
    public void addHouseRepair(Repair repair) {
        repair.setId(UUID.randomUUID().toString());
        repairMapper.insert(repair);
    }

    @Override
    public Repair findRepair(String id) {
        return repairMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<HouseVo> getHouseSysNo(String id, String keyword) {
        return houseInfoMapper.selectHouseSysNo(id,keyword);
    }

    @Override
    public List<RepairVo> findHouseRepairList(RepairQuery repairQuery) {

        return repairMapper.selectHouseRepairList(repairQuery);
    }

    @Override
    public List<RepairVo> findHouseUndoRepairList(RepairQuery repairQuery) {
        return repairMapper.selectHouseUndoRepairList(repairQuery);
    }

    @Override
    public List<RepairVo> findHouseAlseRepairList(RepairQuery repairQuery) {
        return repairMapper.selectHouseAlseRepairList(repairQuery);
    }

    @Override
    public void commit(JobLogDto jobLogDto) {
        JobDto jobDto =jobAcceptService.findByServiceNum(jobLogDto.getJobAccept().getServiceNum());
        //修改当前状态
        jobLogDto.getJobAccept().setCurStatus(jobLogDto.getJobLog().getJobResult());
        jobLogDto.getJobAccept().setId(jobDto.getId());
        jobLogService.modifyCurStatus(jobLogDto.getJobAccept());
        //日志明细
        jobLogDto.getJobLog().setJobAcceptId(jobDto.getId());
        CurrentUser currentUser = userManager.findUser();
        jobLogDto.getJobLog().setAuditId(currentUser.getId());
        jobLogDto.getJobLog().setAuditName(currentUser.getName());
        jobLogService.createJobLog(jobLogDto.getJobLog());
        //流程
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("flag", jobLogDto.getJobResultId());
        // param.put("flag", jobLogDto.getJobLog().getJobResult());
        BusinessType businessType=BusinessType.findByWorkName(jobDto.getBusinessName());
        workFlowService.complete(jobLogDto.getJobAccept().getServiceNum(), "GHOUSE_REPAIR", userManager.findRegionCode(),
                userManager.findUser().getUserId(),
                StringUtils.hasText(jobLogDto.getJobLog().getNodeName()) ? jobLogDto.getJobLog().getNodeName() : Node.N001.getName()
                , param,
                businessType.getKey(), jobLogDto.getJobLog().getJobResult());
    }

    @Override
    public void updaateHouseRepair(Repair repair) {
        repairMapper.updateByPrimaryKeySelective(repair);
    }

    @Override
    public void updateHouseRepair(Repair repair) {
        repair.setHouseId(null);
        repairMapper.updateByPrimaryKeySelective(repair);
    }

    @Override
    public List<HouseOperate> findHouseOperteList(String houseId) {
        return houseOperateMapper.findHouseOperteList(houseId);
    }

    @Override
    public List<AddrChangeVo> findAddressChangeList(String houseId) {

        return addrChangeMapper.selectAddressChangeListByHouseId(houseId);
    }

    @Override
    public List<ContractDto> findContractList(String houseId) {
        return contrMapper.selectContractListByHouseId(houseId);
    }

    @Override
    public List<RepairVo> getHouseRepairListByHouseId(String houseId) {
        return repairMapper.selectHouseRepairListByHouseId(houseId);
    }

    @Override
    public void createRightEnt(RightEnt rightEnt) {
        rightEnt.setId(UUID.randomUUID().toString());
        rightEntMapper.insert(rightEnt);
    }

    @Override
    public List<RightEnt> findRightEntList(String houseId) {
        return rightEntMapper.selectRightEntList(houseId);
    }

    @Override
    public void deleteRightEnt(String id) {
        rightEntMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateHouseHasProperty(String houseId) {
        houseInfoMapper.updateHouseHasProperty(houseId);
    }

    @Override
    public void addHouseExcelDate(List<ExcelHouseInsetVo> queryList,CurrentUser user) throws Exception{
        for(ExcelHouseInsetVo obj:queryList){
            HouseInfo info=new HouseInfo();
            info.setId(UUID.randomUUID().toString());
            info.setSysCreateTime(new Date());
            info.setCreateUnitId( user.getOrganization().getDm());



            info.setHouseNo(obj.getHouseNo());
            List<AddressVo> addressVos=  findAddressVoList(null,obj.getLivingAreaName());
            if(addressVos.isEmpty()){
                continue;
            }
            info.setAddressId(addressVos.get(0).getId());
            info.setAddressBuilding(obj.getAddressBuilding());
            info.setAddressUnit(obj.getAddressUnit());
            info.setAddressFloor(obj.getAddressFloor());
            info.setAddressRoomNo(obj.getAddressRoomNo());
            info.setBedroomQuantity(obj.getBedroomQuantity());
            info.setBathroomQuantity(obj.getBathroomQuantity());
            info.setLivingroomQuantity(obj.getLivingroomQuantity());
            info.setHourseUseType(obj.getHourseUseType());
            info.setManageType(obj.getManageType());
            info.setHouseStatus("有效");
            info.setBulidArea(obj.getBulidArea());
            info.setRentArea(obj.getRentArea());
            info.setEvaluatePrice(obj.getEvaluatePrice());
            info.setBuildStructure(obj.getBuildStructure());
            info.setSafetyLevel(obj.getSafetyLevel());
            addHouseInfo(info);
        }
    }

    @Override
    public String findZlReportTitle(String contractId) {
        List<String> manageTypeList= houseInfoMapper.selectManageTypeByContractId(contractId);
        if(manageTypeList.isEmpty()){
            throw new BizException("无房屋类型!");
        }
        if(manageTypeList.contains("同屋异产房屋")) {
            return "Ghouse_TongWuYiChan_ZuLin";
        }else
            return "Ghouse_GongChan_ZuLin";
    }

    @Override
    public String findHouseNoInfo(String houseNo) {
        return houseInfoMapper.selectHouseNoInfo(houseNo);
    }
}
