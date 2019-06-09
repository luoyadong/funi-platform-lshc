package com.funi.platform.lshc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.funi.framework.biz.BizException;
import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.framework.biz.eic.bo.Dict;
import com.funi.framework.data.migrate.excel.ExcelImporter;
import com.funi.framework.mvc.eic.controller.BaseController;
import com.funi.framework.mvc.eic.vo.ResultVo;
import com.funi.framework.workflow.eic.service.WorkFlowService;
import com.funi.platform.lshc.dto.ContractDto;
import com.funi.platform.lshc.dto.GHouseOrgDto;
import com.funi.platform.lshc.dto.GHouseUserInfoDto;
import com.funi.platform.lshc.dto.JobLogDto;
import com.funi.platform.lshc.entity.house.HouseArchives;
import com.funi.platform.lshc.entity.house.HouseInfo;
import com.funi.platform.lshc.entity.house.HouseLoss;
import com.funi.platform.lshc.entity.house.HouseOperate;
import com.funi.platform.lshc.entity.house.HouseProperty;
import com.funi.platform.lshc.entity.house.HouseTransfer;
import com.funi.platform.lshc.entity.house.RightEnt;
import com.funi.platform.lshc.entity.repair.Repair;
import com.funi.platform.lshc.enumatation.BusinessType;
import com.funi.platform.lshc.mapper.house.HouseInfoMapper;
import com.funi.platform.lshc.mapper.house.RightEntMapper;
import com.funi.platform.lshc.mapper.repair.RepairMapper;
import com.funi.platform.lshc.query.HouseListExcel;
import com.funi.platform.lshc.query.HouseListQuery;
import com.funi.platform.lshc.query.RepairQuery;
import com.funi.platform.lshc.service.CommonService;
import com.funi.platform.lshc.service.HouseService;
import com.funi.platform.lshc.service.JobLogService;
import com.funi.platform.lshc.support.GhouseConstants;
import com.funi.platform.lshc.support.UserManager;
import com.funi.platform.lshc.utils.ContractUtils;
import com.funi.platform.lshc.utils.DateUtils;
import com.funi.platform.lshc.utils.ExcelUtil;
import com.funi.platform.lshc.vo.AddrChangeVo;
import com.funi.platform.lshc.vo.AddressVo;
import com.funi.platform.lshc.vo.ExcelHouseInsetVo;
import com.funi.platform.lshc.vo.ExcelHouseVo;
import com.funi.platform.lshc.vo.HouseAndEntVo;
import com.funi.platform.lshc.vo.HouseVo;
import com.funi.platform.lshc.vo.RepairVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {
    @Resource
    private HouseService houseService;
    @Resource
    private WorkFlowService workFlowService;
    @Resource
    private UserManager userManager;
    @Resource
    private RepairMapper repairMapper;
    @Resource
    private HouseInfoMapper houseInfoMapper;

    @Resource
    private JobLogService jobLogService;

    @Resource
    private RightEntMapper rightEntMapper;
    @Resource
    private CommonService commonService;
    /**
     * 创建产权人信息
     * @param
     */
    @ResponseBody
    @RequestMapping("/createRightEnt")
    public String createRightEnt(RightEnt rightEnt){
        houseService.updateHouseHasProperty(rightEnt.getHouseId());
        houseService.createRightEnt(rightEnt);
        return rightEnt.getId();
    }
    /**
     * 删除产权人信息
     * @param
     */
    @ResponseBody
    @RequestMapping("/deleteRightEnt")
    public String deleteRightEnt(String id){
        houseService.deleteRightEnt(id);
        return  "删除成功";
    }
    /**
     * 查询产权人列表
     * @param
     */
    @ResponseBody
    @RequestMapping("/findRightEntList")
    public List<RightEnt> findRightEntList(String houseId){
        return houseService.findRightEntList(houseId);
    }
    /**
     * 查询操作记录
     * @param
     */
    @ResponseBody
    @RequestMapping("/findHouseOperteList")
    public List<HouseOperate> findHouseOperteList(String houseId){
        return houseService.findHouseOperteList(houseId);
    }
    /**
     * 查询维修信息
     * @param
     * @return
     */
    @RequestMapping("getHouseRepairListByHouseId")
    @ResponseBody
    public List<RepairVo> getHouseRepairListByHouseId(String houseId) {
        return houseService.getHouseRepairListByHouseId(houseId);
    }
    /**
     * 分页查询地址变更列表
     * @param
     * @return
     */
    @RequestMapping("getAddressChangeList")
    @ResponseBody
    public List<AddrChangeVo> getAddressChangeList(String houseId) {
        return houseService.findAddressChangeList(houseId);
    }
    /**
     * 合同集合
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/findContractList")
    public List<ContractDto> findContractList(String houseId){
        return houseService.findContractList(houseId);
    }
    /**
     * 工作流提交
     * @param jobLogDto
     */
    @ResponseBody
    @RequestMapping("/commit")
    public void Commit(@RequestBody JobLogDto jobLogDto){
        Repair repair=new Repair();
        if(jobLogDto.getJobLog().getJobResult().equals("同意")){
            repair.setRepairStatus("审核通过");
            jobLogDto.getJobLog().setJobResult("审核通过");
        }else if(jobLogDto.getJobLog().getJobResult().equals("不同意")){
            repair.setRepairStatus("审核未通过");
            jobLogDto.getJobLog().setJobResult("审核未通过");
        }else {
            repair.setRepairStatus("审核退回");
            jobLogDto.getJobLog().setJobResult("审核退回");
        }

        repair.setRepairNo(jobLogDto.getJobAccept().getServiceNum());
        houseService.updaateHouseRepair(repair);

        houseService.commit(jobLogDto);
    }
    /**
     * 查询维修号
     * @param
     * @return
     */
    @RequestMapping("getHouseRepairList")
    @ResponseBody
    public List<RepairVo> findHouseRepairList(RepairQuery repairQuery) {
        return houseService.findHouseRepairList(repairQuery);
    }
    /**
     * 查询维修号代办件
     * @param
     * @return
     */
    @RequestMapping("getHouseUndoRepairList")
    @ResponseBody
    public List<RepairVo> findHouseUndoRepairList(RepairQuery repairQuery) {
        repairQuery.setOwner(this.getUserInfo().getRoles());
        return houseService.findHouseUndoRepairList(repairQuery);
    }

    /**
     * 查询维修号已办件
     * @param
     * @return
     */
    @RequestMapping("getHouseAlseRepairList")
    @ResponseBody
    public List<RepairVo> findHouseAlseRepairList(RepairQuery repairQuery) {
        return houseService.findHouseAlseRepairList(repairQuery);
    }

    /**
     * 查询房屋户号
     * @param
     * @return
     */
    @RequestMapping("getHouseSysNo")
    @ResponseBody
    public List<HouseVo> getHouseSysNo(String id,String keyword) {
        return houseService.getHouseSysNo(id,keyword);
    }

    /**
     * 维修单填写Transfer
     * @param
     * @return
     */
    @RequestMapping("createRepair")
    @ResponseBody
    public String createRepair(Repair repair){
        repair.setSysCreateTime(new Date());
        repair.setRepairNo( ContractUtils.generateCode(GhouseConstants.BIZ_PREFIX_REPAIR, repairMapper.generateRepairNum()));
        repair.setSysCreateId( this.getUserInfo().getId());
        if(repair.getRepairAddFlag().equals("2")){
            repair.setRepairStatus("已完成");
        }else {
            repair.setRepairStatus("待审核");
        }
        repair.setCreateUnitId(this.getUserInfo().getOrganization().getDm());
        houseService.addHouseRepair(repair);

        if(!repair.getRepairAddFlag().equals("2")){
            //开启工作流
            jobLogService.createJobAccept(
                    ContractUtils.createJobAccept(repair.getRepairNo(),BusinessType.repairnew)
            );
            workFlowService.start(BusinessType.repairnew.getKey(),BusinessType.repairnew.getName(),BusinessType.repairnew.getVersion().toString(),
                    repair.getRepairNo(),"GHOUSE_REPAIR",userManager.findRegionCode(),
                    userManager.findUser().getId(),null);
        }

        return repair.getId();
    }
    /**
     * 维修单修改Transfer
     * @param
     * @return
     */
    @RequestMapping("updateRepair")
    @ResponseBody
    public String updateRepair(Repair repair){
        repair.setSysUpdateTime(new Date());
        repair.setSysUpdateId( this.getUserInfo().getOrganization().getDm());
        if(repair.getRepairAddFlag().equals("2")){
            repair.setRepairStatus("已完成");
            //表示维修完成

        }
        if(repair.getRepairStatus().equals("审核通过")){
            repair.setRepairStatus("已完成");
            //表示回单录入
            repair.setRepairAddFlag("1");
        }

        if(repair.getRepairStatus().equals("审核退回")){

            if(!repair.getRepairAddFlag().equals("2")){
                repair.setRepairStatus("待审核");
                //开启工作流
//                jobLogService.createJobAccept(
//                        ContractUtils.createJobAccept(repair.getRepairNo(),BusinessType.repairnew)
//                );
//                workFlowService.start(BusinessType.repairnew.getKey(),BusinessType.repairnew.getName(),BusinessType.repairnew.getVersion().toString(),
//                        repair.getRepairNo(),"GHOUSE_REPAIR",userManager.findRegionCode(),
//                        userManager.findUser().getId(),null);
            }
        }
        houseService.updateHouseRepair(repair);
        return repair.getId();
    }
    /**
     * 维修单查看Transfer
     * @param
     * @return
     */
    @RequestMapping("findRepair")
    @ResponseBody
    public Repair findRepair(String id){
        Repair repair=houseService.findRepair(id);

        return repair==null?new Repair():repair;
    }

    /**
     * 房屋移交Transfer
     * @param
     * @return
     */
    @RequestMapping("createHouseTransfer")
    @ResponseBody
    public String createHouseTransfer(HouseTransfer houseTransfer){
        houseTransfer.setSysCreateTime(new Date());
        houseTransfer.setSysCreateId( this.getUserInfo().getOrganization().getDm());
        houseTransfer.setTransferUserId(this.getUserInfo().getName());
        houseTransfer.setTransferDate(DateUtils.parseFormatDate(new Date()));
        houseService.addHouseTransfer(houseTransfer);
        return houseTransfer.getId();
    }
    /**
     * 房屋移交Transfer
     * @param
     * @return
     */
    @RequestMapping("findHouseTransfer")
    @ResponseBody
    public HouseTransfer findHouseTransfer(String houseId){
      HouseTransfer result=houseService.findHouseTransfer(houseId);
        return result==null?new HouseTransfer():result;
    }
    /**
     * 房屋注销添加
     * @param
     * @return
     */
    @RequestMapping("createHouseLoss")
    @ResponseBody
    public String createHouseLoss(HouseLoss houseLoss){
        houseLoss.setSysCreateTime(new Date());
        houseLoss.setSysCreateId( this.getUserInfo().getOrganization().getDm());
        houseLoss.setCancelUserId(this.getUserInfo().getName());
        houseLoss.setSysDeleteId(this.getUserInfo().getOrganization().getDm());
        houseLoss.setSysDeleteTime(new Date());
        houseService.addHouseLoss(houseLoss);
        return houseLoss.getId();
    }

    /**
     * 房屋注销查看
     * @param
     * @return
     */
    @RequestMapping("findHouseLoss")
    @ResponseBody
    public HouseLoss findHouseLoss(String houseId){
        HouseLoss result= houseService.findHouseLoss(houseId);
        return  result==null?new HouseLoss():result;
    }

    /**
     * 添加房屋
     * @param houseVo
     * @return
     */
    @RequestMapping("createHouseInfo")
    @ResponseBody
    public String createHouseInfo(HouseInfo houseVo){
        // 获得房屋序列号
//        String houseNum = houseInfoMapper.generateHouseNo();
        houseVo.setIsVacant(new BigDecimal(0));
        houseVo.setHasProperty("0");
        houseVo.setSysCreateTime(new Date());
//        houseVo.setHouseNoSys(houseNum);
        houseVo.setHourseNoPrefix(GhouseConstants.BIZ_PREFIX_HOUSE);

        String houseKeeper = houseVo.getHouseKeeper();//this.getUserInfo().getId()
        GHouseOrgDto ghouse = commonService.getOrgByUserId(houseKeeper);
        String unitCode = this.getUserInfo().getOrganization().getDm();
        if(null != ghouse){
            unitCode = ghouse.getCode();
        }

        houseVo.setSysCreateId(houseKeeper);
//        houseVo.setHouseNo(ContractUtils.generateCode(GhouseConstants.BIZ_PREFIX_HOUSE, houseNum));
        houseVo.setHouseStatus("有效");
       houseVo.setCreateUnitId(unitCode);
        houseService.addHouseInfo(houseVo);
        return houseVo.getId();
    }
    /**
     * 修改房屋
     * @param houseVo
     * @return
     */
    @RequestMapping("updateHouseInfo")
    @ResponseBody
    public String updateHouseInfo(HouseInfo houseVo){

        String houseKeeper = houseVo.getHouseKeeper();
        HouseInfo houseInfo = houseInfoMapper.selectByPrimaryKey(houseVo.getId());
        if(null != houseInfo && !houseInfo.getSysCreateId().equals(houseKeeper)){
            //更新保存前，需要检查房屋是否存在其他业务，如有则不允许变更房管员
            Map<String,String> pMap = new HashMap<String,String>();
            pMap.put("houseId",houseVo.getId());
            List<Map<String,String>>  rtList = houseInfoMapper.existHouseBiz(pMap);
            if(null != rtList && rtList.size() > 0){
                boolean flag = false;
                String msg = "";
                for(Map<String,String> map:rtList){
                    if("REPAIR".equals(map.get("BIZ_TYPE"))){
                        flag = true;
                        msg += "房屋：["+houseVo.getHouseNo()+"],存在办理中的维修业务,编号["+map.get("BIZ_NO")+"]，禁止更改房管员！\n\n";
                    }
                    if("CONTRACT".equals(map.get("BIZ_TYPE"))){
                        flag = true;
                        msg += "房屋：["+houseVo.getHouseNo()+"],存在办理中的租赁业务,编号["+map.get("BIZ_NO")+"]，禁止更改房管员！\n\n\n";
                    }
                }
                if(flag){
                    throw new BizException(msg);
                }
            }

            //将房管员设置成新增者
            GHouseOrgDto ghouse = commonService.getOrgByUserId(houseKeeper);
            String unitCode = this.getUserInfo().getOrganization().getDm();
            if(null != ghouse){
                unitCode = ghouse.getCode();
            }
            houseVo.setCreateUnitId(unitCode);
            houseVo.setSysCreateId(houseKeeper);
        }

        houseVo.setHouseStatus(null);
        houseVo.setSysUpdateTime(new Date());
        houseVo.setSysUpdateId( this.getUserInfo().getOrganization().getDm());

        houseService.updateHouseInfo(houseVo);
        return houseVo.getId();
    }
    @RequestMapping("findHouseNoInfo")
    @ResponseBody
    public String findHouseNoInfo(String houseNo){
        String houseNos=houseService.findHouseNoInfo(houseNo);
        if(houseNos!=null){
            return "户号重复";
        }else {
            return "通过";
        }
    }
    /**
     * 查看房屋
     * @param
     * @return
     */
    @RequestMapping("findHouseInfo")
    @ResponseBody
    public HouseInfo findHouseInfo(String id){
        return houseService.selectHouseInfoById(id);
    }


    /**
     * 添加产权
     * @param houseProperty
     * @return
     */
    @RequestMapping("createHousePropety")
    @ResponseBody
    public String createHousePropety(HouseProperty houseProperty){
       //验证是否有产权
      List<RightEnt>  ret= rightEntMapper.selectRightEntList(houseProperty.getHouseId());
        if(!ret.isEmpty()){
            houseService.updateHouseHasProperty(houseProperty.getHouseId());
        }
        houseProperty.setSysCreateTime(new Date());
        houseService.addHousePropety(houseProperty);
        return houseProperty.getId();
    }

    /**
     * 修改产权
     * @param houseProperty
     * @return
     */
    @RequestMapping("updateHousePropety")
    @ResponseBody
    public String updateHousePropety(HouseProperty houseProperty){
        //验证是否有产权
        List<RightEnt>  ret= rightEntMapper.selectRightEntList(houseProperty.getHouseId());
        if(!ret.isEmpty()){
            houseService.updateHouseHasProperty(houseProperty.getHouseId());
        }
        houseProperty.setSysUpdateTime(new Date());
        houseService.updateHousePropety(houseProperty);
        return  houseProperty.getId();
    }
    /**
     * 添加产权
     * @param houseEnt
     * @return
     */
    @RequestMapping("createHousePropetyAndEnt")
    @ResponseBody
    public String createHousePropetyAndEnt(HouseAndEntVo houseEnt){
        //先插入产权人
        insertRightEnt(houseEnt);

        HouseProperty houseProperty = JSONObject.parseObject(houseEnt.getHouseProperty(),HouseProperty.class);
        //验证是否有产权
        List<RightEnt>  ret= rightEntMapper.selectRightEntList(houseProperty.getHouseId());
        if(!ret.isEmpty()){
            houseService.updateHouseHasProperty(houseProperty.getHouseId());
        }
        houseProperty.setSysCreateTime(new Date());
        houseService.addHousePropety(houseProperty);
        return houseProperty.getId();
    }

    /**
     * 修改产权
     * @param houseEnt
     * @return
     */
    @RequestMapping("updateHousePropetyAndEnt")
    @ResponseBody
    public String updateHousePropetyAndEnt(HouseAndEntVo houseEnt){
        //先插入产权人
        insertRightEnt(houseEnt);
        //验证是否有产权
        HouseProperty houseProperty =  JSONObject.parseObject(houseEnt.getHouseProperty(),HouseProperty.class);
        List<RightEnt>  ret= rightEntMapper.selectRightEntList(houseProperty.getHouseId());
        if(!ret.isEmpty()){
            houseService.updateHouseHasProperty(houseProperty.getHouseId());
        }
        houseProperty.setSysUpdateTime(new Date());
        houseService.updateHousePropety(houseProperty);
        return  houseProperty.getId();
    }
    /**
     * 查看产权
     * @param
     * @return
     */
    @RequestMapping("findHousePropety")
    @ResponseBody
    public HouseProperty findHousePropety(String id){
        HouseProperty result=houseService.selectHousePropetyById(id);
        if(result==null)
            result=new HouseProperty();
        return result;
    }
    /**
     * 添加档案
     * @param houseArchives
     * @return
     */
    @RequestMapping("createHouseArchives")
    @ResponseBody
    public String createHouseArchives(HouseArchives houseArchives){
        houseArchives.setSysCreateTime(new Date());
        houseService.addHouseArchives(houseArchives);
        return houseArchives.getId();
    }
    /**
     * 修改档案
     * @param houseArchives
     * @return
     */
    @RequestMapping("updateHouseArchives")
    @ResponseBody
    public String updateHouseArchives(HouseArchives houseArchives){
        houseArchives.setSysUpdateTime(new Date());
        houseService.updateHouseArchives(houseArchives);
        return houseArchives.getId();
    }
    /**
     * 查看档案
     * @param
     * @return
     */
    @RequestMapping("findHouseArchives")
    @ResponseBody
    public HouseArchives findHouseArchives(String id){
        return houseService.selectHouseArchivesById(id);
    }
    @RequestMapping("findHouseList")
    @ResponseBody
    public List<HouseVo> findHouseList(HouseListQuery query){
        return houseService.findHouseList(query);

    }

    /**
     * 查询小区列表
     * @param
     * @return
     */
    @RequestMapping("getAddressList")
    @ResponseBody
    public List<AddressVo> getAddressList(String id,String keyword) {
        return houseService.findAddressVoList(id,keyword);
    }
    @RequestMapping("updateHouseStatus")
    @ResponseBody
    public String updateHouseStatus(String  id,String status){
        houseService.updateHouseStatus(id,status);
        return "成功";
    }

    /**
     * 导出查询数据
     * @throws Exception
     */
    @RequestMapping("/excelExport")
    public void excelExport(HouseListExcel query, HttpServletResponse response)throws Exception{
        List<ExcelHouseVo> queryList = new ArrayList<ExcelHouseVo>();
        com.funi.framework.biz.eic.bo.CurrentUser user = userManager.findUser();
        String currentUserOrgCode = user.getOrganization().getDm();
        // 如果当前登录用户所属机构是房管局，不做权限控制，允许查询全部数据
        if(currentUserOrgCode.indexOf("ghouse_zf")==0){
            return;
        } else if(currentUserOrgCode.indexOf("ghouse_dg")==0){
            // 如果当前登录用户所属机构属于代管公司管理员，只返回所属机构
            if(isDgAdmin(user)) {
                List<Dict> authorityList = new ArrayList<>();
                authorityList.add(user.getOrganization());
                query.setAuthorityList(authorityList);
            } else {
                // 代管公司普通用户只能查看自己创建的业务件
                query.setUserId(userManager.findUser().getUserId());
            }
        } else {
            throw new RuntimeException("非房管局和代管公司用户的无效用户");
        }
        queryList= houseService.selectHouseExcelList(query);
        ExcelUtil.excelExport("房屋管理数据.xls","房屋数据",queryList,response);
    }
    /**
     * 判断当前登录用户是否是代管公司管理员
     * @param user
     * @return
     */
    private boolean isDgAdmin(com.funi.framework.biz.eic.bo.CurrentUser user) {
        for(Dict role : user.getRoles()){
            if(role.getDm().indexOf("ROLE_GHOUSE_DG_ADMIN")!=-1){
                return true;
            }
        }
        return false;
    }

    /**
     * 查询用户获取用户权限范围内的全部房管员列表
     * @return
     */
    @RequestMapping("/findUserList")
    @ResponseBody
    public List<GHouseUserInfoDto> findUserList(String id){
        CurrentUser user=this.userManager.findUser();
        if(StringUtils.isNotBlank(id)){
            user.setUserId(id);
        }
        return commonService.getUserList(user);
    }

    /**
     * 导出魔板
     * @throws Exception
     */
    @RequestMapping("/excelModelExport")
    public void excelModelExport(HouseListQuery query, HttpServletResponse response)throws Exception{
        List<ExcelHouseInsetVo> queryList = new ArrayList<ExcelHouseInsetVo>();
        queryList.add(new ExcelHouseInsetVo());
        ExcelUtil.excelExport("房屋管理数据.xls","房屋数据",queryList,response);
    }
    /**
     * 导入数据
     * @throws Exception
     */
    @RequestMapping("/importExport")
    @ResponseBody
    public Map importExport(MultipartFile uploadFile) throws Exception{
        //返回报文头
        Map<String,Object> responseMap = new HashMap<String,Object>();
        List<ExcelHouseInsetVo> queryList = null;
        ExcelImporter<ExcelHouseInsetVo> importer = new ExcelImporter<ExcelHouseInsetVo>();
        try{
            queryList = importer.setStartRows(2)
                    .setHeadRows(1)
                    .setItemClass(ExcelHouseInsetVo.class)
                    .execute(uploadFile.getInputStream());
            //校验房屋号，是否存在重复
            String checkResult = checkImportData(queryList);
            if(!"true".equals(checkResult)){//如果不是为"true",则返回错误提示
                responseMap.put("status",500);
                responseMap.put("check_msg",checkResult);
                return responseMap;
            }
            houseService.addHouseExcelDate(queryList,this.getUserInfo());

            //TODO
            responseMap.put("status",200);
        }catch(Exception e){
            //System.out.println(e.getMessage());
            responseMap.put("status",500);
            responseMap.put("check_msg","导入失败，数据库可能存在重复数据，请联系管理员！");
        }
        return responseMap;
    }
    //校验导入数据是否存在重复
    //返回参数："true":数据校验通过，可以进行后续操作；其余情况为错误信息返回
    private String checkImportData(List<ExcelHouseInsetVo> importList){
        String rtFlag = "true";
        if(null == importList || importList.size() <= 0){//结果集为空
            return "导入结果不能为空";
        }
        List<String> checkList = new ArrayList<String>();
        int leng = importList.size();
        for(int i=0;i<leng;i++){
            ExcelHouseInsetVo obj = importList.get(i);
            if(null != obj && StringUtils.isNotBlank(obj.getHouseNo())){
                if(!checkList.contains(obj.getHouseNo())){
                    checkList.add(obj.getHouseNo());
                }else{
                    return "第"+(i+1)+"行，房屋号：["+obj.getHouseNo()+"]存在重复数据，请修改后重试。";
                }
            }else{
                return "第"+(i+1)+"行，[房屋户号*]为空，请修改后重试。";
            }
        }

        return rtFlag;
    }
    private void insertRightEnt(HouseAndEntVo houseEnt){

        if(null != houseEnt && StringUtils.isNotBlank(houseEnt.getRightEnt())){
            List<RightEnt> rtList= JSONArray.parseArray(houseEnt.getRightEnt(),RightEnt.class);
            if(null != rtList && rtList.size() >0){

                int leng = rtList.size();
                HouseProperty houseProperty =  JSONObject.parseObject(houseEnt.getHouseProperty(),HouseProperty.class);
                for(int i=0;i<leng;i++){
                    RightEnt rEnt = rtList.get(i);
                    rEnt.setHouseId(houseProperty.getHouseId());
                    rEnt.setId(UUID.randomUUID().toString());
                    houseService.createRightEnt(rEnt);
                }
            }

        }

    }

    @ResponseBody
    @RequestMapping("/zl/{contractId}")
    public ResultVo findZlReportTitle(@PathVariable String contractId){
        return ResultVo.newResult(houseService.findZlReportTitle(contractId));
    }
}
