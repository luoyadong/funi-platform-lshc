package com.funi.platform.lshc.service.impl;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.framework.data.migrate.excel.ExcelImporter;
import com.funi.platform.lshc.dto.RegiInfoDto;
import com.funi.platform.lshc.entity.census.BuildInfo;
import com.funi.platform.lshc.entity.census.EntInfo;
import com.funi.platform.lshc.entity.census.File;
import com.funi.platform.lshc.entity.census.RegiInfo;
import com.funi.platform.lshc.enumatation.BusinessType;
import com.funi.platform.lshc.mapper.census.BuildInfoMapper;
import com.funi.platform.lshc.mapper.census.EntInfoMapper;
import com.funi.platform.lshc.mapper.census.FileMapper;
import com.funi.platform.lshc.mapper.census.RegiInfoMapper;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.BuildRegiQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.service.BasicService;
import com.funi.platform.lshc.service.LshcWorkFlowService;
import com.funi.platform.lshc.service.ManageRegiInfoService;
import com.funi.platform.lshc.support.BasicHelper;
import com.funi.platform.lshc.support.CensusConstants;
import com.funi.platform.lshc.support.UserManager;
import com.funi.platform.lshc.utils.ExcelUtil;
import com.funi.platform.lshc.utils.SuperEntityUtils;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ExcelRegiInfoVo;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;
import com.funi.platform.lshc.vo.census.RegiInfoCheckResultVo;
import com.funi.platform.lshc.vo.census.RegiInfoCheckVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by sam on 2019/6/17.10:40 PM
 */
public class ManageRegiInfoServiceImpl implements ManageRegiInfoService {
    @Resource
    private BuildInfoMapper buildInfoMapper;
    @Resource
    private UserManager userManager;
    @Resource
    private RegiInfoMapper regiInfoMapper;
    @Resource
    private EntInfoMapper entInfoMapper;
    @Resource
    private FileMapper fileMapper;
    @Resource
    private LshcWorkFlowService lshcWorkFlowService;
    @Resource
    private BasicHelper basicHelper;
    @Resource
    private BasicService basicService;

    @Override
    public List<BuildInfoVo> findBuildInfoList(BuildInfoQuery buildInfoQuery) {
        buildInfoQuery.setQueryType(CensusConstants.BUILD_QUERY_TYPE_MANAGE);
        return buildInfoMapper.selectBuildInfoVoList(buildInfoQuery);
    }

    @Override
    public void exportBuildInfoVoList(List<String> ids, HttpServletResponse response) throws Exception {
        BuildRegiQuery buildRegiQuery = new BuildRegiQuery(ids, CensusConstants.BUILD_QUERY_TYPE_MANAGE);
        List<ExcelRegiInfoVo> excelRegiInfoVoList = regiInfoMapper.exportBuildInfoVoList(buildRegiQuery);
        if(CollectionUtils.isEmpty(excelRegiInfoVoList)) {
            throw new RuntimeException("没有满足条件的数据");
        }
        ExcelUtil.excelExport("普查数据统计表.xls","普查数据", excelRegiInfoVoList, response);
    }

    @Override
    public void batchSubmitRegiInfo(List<String> ids) throws Exception {
        CurrentUser userInfo = userManager.findUser();
        for(String id : ids) {
            RegiInfo regiInfo = regiInfoMapper.selectByPrimaryKey(id);
            if (regiInfo == null) {
                throw new RuntimeException("普查信息不存在，请核实普查信息ID");
            }
            if(! CensusConstants.HOUSE_STATUS_INPUT.equals(regiInfo.getHouseStatus())) {
                throw new RuntimeException("普查状态异常，无法进入审批流程");
            }
            // 修改普查信息的状态，
            regiInfoMapper.updateRegiInfoStatus(id, CensusConstants.HOUSE_STATUS_SUBMIT, userInfo.getUserId());
            lshcWorkFlowService.startWorkFlow(BusinessType.pnew,id,"LSHC_REGI_INFO",null);
        }
    }

    @Override
    public List<ListRegiInfoVo> findRegiInfoVoList(RegiInfoQuery regiInfoQuery) {
        regiInfoQuery.setQueryType(CensusConstants.BUILD_QUERY_TYPE_MANAGE);
        return regiInfoMapper.selectRegiInfoVoList(regiInfoQuery);
    }

    @Override
    public RegiInfoCheckResultVo checkRegiInfo(RegiInfo regiInfo) {
        List<RegiInfo> regiInfoList = regiInfoMapper.selectRegiInfoByUniqueQuery(regiInfo);
        if(CollectionUtils.isEmpty(regiInfoList)) {
            return new RegiInfoCheckResultVo(true);
        } else {
            RegiInfoCheckVo regiInfoCheckVo = new RegiInfoCheckVo(regiInfoList.size(), getRegiInfoDesc(regiInfo));
            return new RegiInfoCheckResultVo(regiInfoCheckVo);
        }
    }

    @Override
    public String createRegiInfo(RegiInfoDto regiInfoDto, boolean isSubmit) throws Exception {
        RegiInfo regiInfo = regiInfoDto.getRegiInfo();
        if (regiInfo == null) {
            throw new RuntimeException("普查信息不能为空");
        }
        String mapCode = regiInfo.getMapCode();
        if(StringUtils.isBlank(mapCode)) {
            throw new RuntimeException("楼栋地图编号不能为空");
        }
        CurrentUser userInfo = userManager.findUser();
        // 保存普查信息和楼栋信息
        saveNewRegiInfo(regiInfo, userInfo, isSubmit);
        // 普查信息主键ID
        String id = regiInfo.getId();
        // 人口信息
        List<EntInfo> entInfoList = regiInfoDto.getEntInfoList();
        if(CollectionUtils.isNotEmpty(entInfoList)) {
            for(EntInfo entInfo: entInfoList) {
                saveNewEntInfo(entInfo, id, userInfo);
            }
        }
        // 附件信息
        List<File> fileList = regiInfoDto.getFileList();
        if(CollectionUtils.isNotEmpty(fileList)) {
            for(File file : fileList) {
                new SuperEntityUtils<>().buildCreateEntity(file, userInfo);
                buildFileInfo(file, regiInfo, userInfo);
                fileMapper.insert(file);
            }
        }
        if(isSubmit) {
            // 修改普查信息的状态
            regiInfoMapper.updateRegiInfoStatus(id, CensusConstants.HOUSE_STATUS_SUBMIT, userInfo.getUserId());
            lshcWorkFlowService.startWorkFlow(BusinessType.pnew,id,"LSHC_REGI_INFO",null);
        }
        return id;
    }

    @Override
    public String saveOrUpdateRegiInfo(RegiInfoDto regiInfoDto, boolean isSubmit) throws Exception {
        RegiInfo regiInfo = regiInfoDto.getRegiInfo();
        if (regiInfo == null) {
            throw new RuntimeException("普查信息不能为空");
        }
        String mapCode = regiInfo.getMapCode();
        if(StringUtils.isBlank(mapCode)) {
            throw new RuntimeException("楼栋地图编号不能为空");
        }
        CurrentUser userInfo = userManager.findUser();
        String id = regiInfo.getId();
        CurrentUser user = userManager.findUser();
        // 如果普查信息ID不为空，则此次操作为更新操作
        if(StringUtils.isNotBlank(id)) {
            RegiInfo existRegiInfo = regiInfoMapper.selectByPrimaryKey(id);
            if (existRegiInfo == null) {
                throw new RuntimeException("普查信息不存在");
            }
            // 编辑普查信息
            updateRegiInfo(regiInfo, existRegiInfo, user);
        } else {
            // 保存普查信息和楼栋信息
            saveNewRegiInfo(regiInfo, userInfo, isSubmit);
        }
        // 编辑普查信息关联的入住人信息
        modifyEntInfoList(regiInfoDto.getEntInfoList(), id, user);
        // 编辑普查信息关联的附件信息
        modifyFileList(regiInfoDto.getFileList(), id, user);
        if(isSubmit) {
            if(! CensusConstants.HOUSE_STATUS_INPUT.equals(regiInfo.getHouseStatus())) {
                throw new RuntimeException("普查状态异常，无法进入审批流程");
            }
            // 修改普查信息的状态
            regiInfoMapper.updateRegiInfoStatus(id, CensusConstants.HOUSE_STATUS_SUBMIT, userInfo.getUserId());
            lshcWorkFlowService.startWorkFlow(BusinessType.pnew,id,"LSHC_REGI_INFO",null);
        }
        return id;
    }

    /**
     * 保存新增的房屋信息，更新或保存楼栋信息
     * @param regiInfo
     * @param userInfo
     */
    private void saveNewRegiInfo(RegiInfo regiInfo, CurrentUser userInfo, boolean isSubmit) {
        new SuperEntityUtils<>().buildCreateEntity(regiInfo, userInfo);
        String addressCity = regiInfo.getAddressCity();
        // 批量上传时没有城市名称，默认为拉萨市
        if(StringUtils.isBlank(addressCity)) {
            regiInfo.setAddressCity(CensusConstants.DEFAULT_CITY_NAME);
        }
        regiInfo.setHouseId(regiInfoMapper.generateHouseId());
        // 默认状态是录入
        String houseStatus = CensusConstants.HOUSE_STATUS_INPUT;
        regiInfo.setHouseStatus(houseStatus);
        regiInfo.setOrgCode(userInfo.getOrganization().getDm());
        regiInfo.setOrgName(userInfo.getOrganization().getMc());
        // 社区名称由当前登录人权限社区获取
        regiInfo.setCommunityName(basicService.findCurrentUserRegionName(userInfo.getUserId()));
        // 填报单位、填报时间、填报人由前端来传
//        regiInfo.setUnitName(userInfo.getOrganization().getMc());
//        regiInfo.setApplyUser(userInfo.getName());
//        regiInfo.setReportDate(DateUtils.getCurrentDate());
        // 设置普查信息的提交人区域编码
        regiInfo.setCommon(basicService.findCurrentUserRegionCode(userInfo.getUserId()));
        // 保存房屋数据
        regiInfoMapper.insert(regiInfo);
        // 保存或更新楼栋信息
        saveOrUpdateBuildInfo(regiInfo, userInfo);
        regiInfoMapper.updateSameBuildRegiInfo(regiInfo);
    }

    /**
     * 保存房屋的入住人信息
     * @param entInfo
     * @param hcId
     * @param userInfo
     */
    private void saveNewEntInfo(EntInfo entInfo, String hcId, CurrentUser userInfo) {
        new SuperEntityUtils<>().buildCreateEntity(entInfo, userInfo);
        entInfo.setHcId(hcId);
        entInfoMapper.insert(entInfo);
    }

    /**
     * 保存或更新楼栋信息
     * @param regiInfo
     */
    private void saveOrUpdateBuildInfo(RegiInfo regiInfo, CurrentUser userInfo) {
        // 获得楼栋地图编号
        String mapCode = regiInfo.getMapCode();
        if(StringUtils.isBlank(mapCode)) {
            throw new RuntimeException("楼栋地图编号不能为空");
        }
        BuildInfo buildInfo = buildInfoMapper.selectBuildInfoByMapCode(mapCode);
        if (buildInfo == null) {
            buildInfo = new BuildInfo();
            new SuperEntityUtils<>().buildCreateEntity(buildInfo, userInfo);
            buildBuildInfoFromRegiInfo(buildInfo, regiInfo);
            // 保存楼栋数据
            buildInfoMapper.insert(buildInfo);
        } else {
            buildBuildInfoFromRegiInfo(buildInfo, regiInfo);
            buildInfo.setUpdateTime(new Date());
            buildInfo.setUpdateId(userInfo.getUserId());
            buildInfoMapper.updateByPrimaryKey(buildInfo);
        }
    }

    /**
     * 拷贝普查信息中的楼栋信息到楼栋对象
     * @param buildInfo
     * @param regiInfo
     */
    private void buildBuildInfoFromRegiInfo(BuildInfo buildInfo, RegiInfo regiInfo) {
        buildInfo.setRegion(regiInfo.getRegion());
        buildInfo.setStreet(regiInfo.getStreet());
        buildInfo.setProjectName(regiInfo.getProjectName());
        buildInfo.setMapCode(regiInfo.getMapCode());
        buildInfo.setAddress(getActualAddress(regiInfo));
        buildInfo.setBuildName(regiInfo.getBuildName());
        buildInfo.setCommunityName(regiInfo.getCommunityName());
    }


    @Override
    public void modifyRegiInfo(RegiInfoDto regiInfoDto) {
        // 获取普查信息
        RegiInfo regiInfo = regiInfoDto.getRegiInfo();
        String houseId = regiInfo.getId();
        if(StringUtils.isBlank(houseId)) {
            throw new RuntimeException("普查信息ID不能为空");
        }
        CurrentUser user = userManager.findUser();
        String userId = user.getUserId();
        regiInfo.setUpdateId(userId);
        RegiInfo existRegiInfo = regiInfoMapper.selectByPrimaryKey(houseId);
        // 编辑普查信息
        updateRegiInfo(regiInfo, existRegiInfo, user);
        // 编辑普查信息关联的入住人信息
        modifyEntInfoList(regiInfoDto.getEntInfoList(), houseId, user);
        // 编辑普查信息关联的附件信息
        modifyFileList(regiInfoDto.getFileList(), houseId, user);
    }

    /**
     * 更新普查信息
     * @param paramRegiInfo
     * @param existRegiInfo
     * @param currentUser
     */
    private void updateRegiInfo(RegiInfo paramRegiInfo, RegiInfo existRegiInfo, CurrentUser currentUser) {
        new SuperEntityUtils().copyEntity(existRegiInfo, paramRegiInfo, currentUser);
        paramRegiInfo.setHouseId(existRegiInfo.getHouseId());
        paramRegiInfo.setCommon(existRegiInfo.getCommon());
        paramRegiInfo.setHouseStatus(existRegiInfo.getHouseStatus());
        paramRegiInfo.setOrgCode(existRegiInfo.getOrgCode());
        paramRegiInfo.setOrgName(existRegiInfo.getOrgName());
//        paramRegiInfo.setUnitName(existRegiInfo.getUnitName());
//        paramRegiInfo.setApplyUser(existRegiInfo.getApplyUser());
//        paramRegiInfo.setReportDate(existRegiInfo.getReportDate());
        regiInfoMapper.updateByPrimaryKey(paramRegiInfo);
        // 保存或更新楼栋信息
        saveOrUpdateBuildInfo(paramRegiInfo, currentUser);
        regiInfoMapper.updateSameBuildRegiInfo(paramRegiInfo);
    }

    /**
     * 编辑普查信息关联的入住人信息
     * @param entInfoList
     * @param houseId
     */
    private void modifyEntInfoList(List<EntInfo> entInfoList, String houseId, CurrentUser user) {
        String userId = user.getUserId();
        if(CollectionUtils.isEmpty(entInfoList)) {
            // 逻辑删除全部入住人信息
            entInfoMapper.deleteAllEntInfoByHouseId(houseId, userId);
        } else {
            // 用于保存需要修改的入住人信息
            List<EntInfo> modifyEntInfoList = new ArrayList<>();
            // 用于保存需要新增的入住人信息
            List<EntInfo> createEntInfoList = new ArrayList<>();
            // 用于保存需要修改的入住人ID集合
            List<String> modifyEntInfoIdList = new ArrayList<>();
            for(EntInfo entInfo : entInfoList) {
                String id = entInfo.getId();
                if(StringUtils.isNotBlank(id)) {
                    modifyEntInfoList.add(entInfo);
                    modifyEntInfoIdList.add(id);
                } else {
                    createEntInfoList.add(entInfo);
                }
            }
            if(CollectionUtils.isEmpty(modifyEntInfoIdList)) {
                // 逻辑删除全部入住人信息
                entInfoMapper.deleteAllEntInfoByHouseId(houseId, userId);
            } else {
                // 逻辑删除除了需要修改的入住人信息
                entInfoMapper.deleteEntInfoByExceptIds(houseId, modifyEntInfoIdList, userId);
            }
            if(CollectionUtils.isNotEmpty(modifyEntInfoList)) {
                for(EntInfo entInfo : modifyEntInfoList) {
                    entInfo.setUpdateId(userId);
                    // 编辑入住人信息
                    entInfoMapper.updateByPrimaryKeySelective(entInfo);
                }
            }
            if(CollectionUtils.isNotEmpty(createEntInfoList)) {
                // 新增入住人信息
                for(EntInfo entInfo : createEntInfoList) {
                    saveNewEntInfo(entInfo, houseId, user);
                }
            }
        }
    }

    /**
     * 编辑普查信息关联的附件信息
     * @param fileList
     * @param houseId
     */
    private void modifyFileList(List<File> fileList, String houseId, CurrentUser user) {
        String userId = user.getUserId();
        if(CollectionUtils.isEmpty(fileList)) {
            // 逻辑删除全部附件信息
            fileMapper.deleteAllFileByHouseId(houseId, userId);
        } else {
            // 用于保存需要修改的附件信息
            List<File> modifyFileList = new ArrayList<>();
            // 用于保存需要新增的附件信息
            List<File> createFileList = new ArrayList<>();
            // 用于保存需要修改的附件ID集合
            List<String> modifyFileIdList = new ArrayList<>();

            for(File file : fileList) {
                String id = file.getId();
                if(StringUtils.isNotBlank(id)) {
                    modifyFileList.add(file);
                    modifyFileIdList.add(id);
                } else {
                    createFileList.add(file);
                }
            }
            if(CollectionUtils.isEmpty(modifyFileList)) {
                // 逻辑删除全部入住人信息
                fileMapper.deleteAllFileByHouseId(houseId, userId);
            } else {
                // 逻辑删除除了需要修改的入住人信息
                fileMapper.deleteFileByExceptIds(houseId, modifyFileIdList, userId);
            }
            if(CollectionUtils.isNotEmpty(modifyFileList)) {
                for(File file : modifyFileList) {
                    file.setUpdateId(userId);
                    // 编辑入住人信息
                    fileMapper.updateByPrimaryKeySelective(file);
                }
            }
            if(CollectionUtils.isNotEmpty(createFileList)) {
                RegiInfo regiInfoExist = regiInfoMapper.selectByPrimaryKey(houseId);
                // 新增入住人信息
                for(File file : createFileList) {
                    new SuperEntityUtils<>().buildCreateEntity(file, user);
                    buildFileInfo(file, regiInfoExist, user);
                    fileMapper.insert(file);
                }
            }
        }
    }

    @Override
    public String checkRegiInfoList(MultipartFile uploadFile) throws IOException {
        List<ExcelRegiInfoVo> excelRegiInfoVoList = getRegiInfoByExcle(uploadFile);
        // 校验Excel数据有效性
        if(CollectionUtils.isEmpty(excelRegiInfoVoList)) {
            throw new RuntimeException("Excel中没有数据，请检查Excel格式是否正确或是否存在有效数据");
        }
        StringBuilder checkResultBuilder = new StringBuilder();
        for(int i = 0; i < excelRegiInfoVoList.size(); i ++) {
            // 获取当前行号
            int rowNo = i + CensusConstants.EXCEL_CONTENT_START_ROW_NO + 1;
            List<Integer> repeatRowNoList = new ArrayList<>();
            ExcelRegiInfoVo currentExcelRegiInfoVo = excelRegiInfoVoList.get(i);
            for(int j = 0; j < excelRegiInfoVoList.size(); j ++) {
                if(i  == j) {
                    continue;
                }
                int loopRowNo = j + CensusConstants.EXCEL_CONTENT_START_ROW_NO + 1;
                ExcelRegiInfoVo currentLoopExcelRegiInfoVo = excelRegiInfoVoList.get(i);
                if(currentExcelRegiInfoVo.equals(currentLoopExcelRegiInfoVo)) {
                    repeatRowNoList.add(loopRowNo);
                }
            }
            if(CollectionUtils.isNotEmpty(repeatRowNoList)) {
                checkResultBuilder.append("第" + rowNo + "行与第" + StringUtils.join(repeatRowNoList, ",") + "行数据重复；");
            }
        }
        return checkResultBuilder.toString();
    }

    /**
     * 根据批量上传普查信息的Excel，获取普查信息对象和入住人信息对象集合
     * @param uploadFile
     * @return
     */
    private List<ExcelRegiInfoVo> getRegiInfoByExcle(MultipartFile uploadFile) throws IOException {
        ExcelImporter<ExcelRegiInfoVo> importer = new ExcelImporter<>();
        return importer.setStartRows(CensusConstants.EXCEL_CONTENT_START_ROW_NO).setHeadRows(CensusConstants.EXCEL_CONTENT_HEAD_ROWS_NO).setItemClass(ExcelRegiInfoVo.class).execute(uploadFile.getInputStream());
    }

    @Override
    public void batchRemoveBuildInfo(List<String> ids) {
        // 楼栋ID关联的审核中普查信息
        List<RegiInfo> inAuditRegiInfoList = regiInfoMapper.selectRegiInfoListInAudit(ids);
        if(CollectionUtils.isNotEmpty(inAuditRegiInfoList)) {
            throw new RuntimeException("该楼栋还有处理审核流程中的普查信息，请先处理完审核流程中的普查信息再执行删除操作");
        }
        List<String> regiInfoIdList = regiInfoMapper.selectRegiInfoIdListByBuildIds(ids);
        String userId = userManager.findUser().getUserId();
        // 逻辑删除楼栋信息
        buildInfoMapper.batchDeleteBuildInfo(ids, userId);
        // 逻辑删除普查信息以及关联数据
        if(CollectionUtils.isNotEmpty(regiInfoIdList)) {
            batchRemoveRegiInfoByIds(regiInfoIdList, userId);
        }
    }

    @Override
    public void batchRemoveRegiInfo(List<String> ids) {
        batchRemoveRegiInfoByIds(ids, userManager.findUser().getUserId());
    }

    @Override
    public Map<String,Object> checkReturnRegiInfoList(MultipartFile uploadFile) throws IOException {
        Map<String,Object> rtMap = new HashMap<String,Object>();
        List<ExcelRegiInfoVo> excelRegiInfoVoList = getRegiInfoByExcle(uploadFile);
        // 校验Excel数据有效性
        if(CollectionUtils.isEmpty(excelRegiInfoVoList)) {
            throw new RuntimeException("Excel中没有数据，请检查Excel格式是否正确或是否存在有效数据");
        }
        StringBuilder checkResultBuilder = new StringBuilder();
        for(int i = 0; i < excelRegiInfoVoList.size(); i ++) {
            // 获取当前行号
            int rowNo = i + CensusConstants.EXCEL_CONTENT_HEAD_ROWS_NO + 1;
            List<Integer> repeatRowNoList = new ArrayList<>();
            ExcelRegiInfoVo currentExcelRegiInfoVo = excelRegiInfoVoList.get(i);
            // 获取房屋编号，并校验有效性
            String houseId = currentExcelRegiInfoVo.getHouseId();
            RegiInfo regiInfo = regiInfoMapper.selectByHouseId(houseId);
            if (regiInfo == null) {
                throw new RuntimeException("房屋编号为" + houseId + ",的普查信息在系统中不存在，请核实");
            }
            for(int j = i+1; j < excelRegiInfoVoList.size(); j ++) {
                int loopRowNo = j + CensusConstants.EXCEL_CONTENT_HEAD_ROWS_NO + 1;
                ExcelRegiInfoVo currentLoopExcelRegiInfoVo = excelRegiInfoVoList.get(j);
                if(currentExcelRegiInfoVo.equals(currentLoopExcelRegiInfoVo)) {
                    repeatRowNoList.add(loopRowNo);
                }
            }
            if(CollectionUtils.isNotEmpty(repeatRowNoList)) {
                checkResultBuilder.append("第" + rowNo + "行与第" + StringUtils.join(repeatRowNoList, ",") + "行数据重复；");
            }
        }
        rtMap.put("checkMsg",checkResultBuilder.toString());
        rtMap.put("regiList",excelRegiInfoVoList);
        return rtMap;
    }

    @Override
    public void importRegiInfoList(List<ExcelRegiInfoVo> excelRegiInfoVoList) throws IOException {
        // 校验Excel数据有效性
        if(CollectionUtils.isEmpty(excelRegiInfoVoList)) {
            throw new RuntimeException("Excel中没有数据，请检查Excel格式是否正确或是否存在有效数据");
        }
        for(ExcelRegiInfoVo excelRegiInfoVo : excelRegiInfoVoList) {
            RegiInfo regiInfo = new RegiInfo();
            EntInfo entInfo = new EntInfo();
            // 拷贝房屋对象属性
            BeanUtils.copyProperties(excelRegiInfoVo, regiInfo);
            // 拷贝入住人员属性
            BeanUtils.copyProperties(excelRegiInfoVo, entInfo);
            // 使用普查信息编号是否为空来判断是新增还是编辑操作
            String houseId = regiInfo.getHouseId();
            CurrentUser userInfo = userManager.findUser();
            if(StringUtils.isBlank(houseId)) {
                // 保存普查信息和楼栋信息
                saveNewRegiInfo(regiInfo, userInfo, false);
                // 普查信息主键ID
                String id = regiInfo.getId();
                if(entInfo != null) {
                    // 保存人口信息
                    saveNewEntInfo(entInfo, id, userInfo);
                }
            } else {
                RegiInfo existRegiInfo = regiInfoMapper.selectByHouseId(houseId);
                // 编辑普查信息
                updateRegiInfo(regiInfo, existRegiInfo, userInfo);
                if(entInfo != null) {
                    List<EntInfo> entInfoList = new ArrayList<>();
                    entInfoList.add(entInfo);
                    // 编辑普查信息关联的入住人信息
                    modifyEntInfoList(entInfoList, houseId, userInfo);
                }
            }
        }
    }

    /**
     * 根据普查信息ID集合逻辑删除普查信息，以及关联的入住人员信息和附件信息
     * @param ids 普查信息ID集合
     * @param userId
     */
    private void batchRemoveRegiInfoByIds(List<String> ids, String userId) {
        // 逻辑删除房屋信息
        regiInfoMapper.batchDeleteRegiInfo(ids, userId);
        // 逻辑删除房屋关联的入住人员信息
        entInfoMapper.batchDeleteEntInfo(ids, userId);
        // 逻辑删除房屋关联的附件信息
        fileMapper.batchDeleteFile(ids, userId);
    }

    /**
     * 市+区+县+门牌号+楼栋地图编号+栋+单元+楼层+号
     * @param regiInfo
     * @return
     */
    private String getRegiInfoDesc(RegiInfo regiInfo) {
        StringBuilder regiInfoBuilder = new StringBuilder();
        String mapCode = regiInfo.getMapCode();
        if(StringUtils.isNotBlank(mapCode)) {
            regiInfoBuilder.append("楼栋地图编号为：" + mapCode + ",");
        }
        regiInfoBuilder.append("地址为：");
        String addressCity = regiInfo.getAddressCity();
        if(StringUtils.isNotBlank(addressCity)) {
            regiInfoBuilder.append(addressCity);
        }
        String addressRegion = regiInfo.getAddressRegion();
        if(StringUtils.isNotBlank(addressRegion)) {
            regiInfoBuilder.append(addressRegion);
        }
        String addressCounty = regiInfo.getAddressCounty();
        if(StringUtils.isNotBlank(addressCounty)) {
            regiInfoBuilder.append(addressCounty);
        }
        String apt = regiInfo.getApt();
        if(StringUtils.isNotBlank(apt)) {
            regiInfoBuilder.append(apt + "号");
        }
        String buildNo = regiInfo.getBuildNo();
        if(StringUtils.isNotBlank(buildNo)) {
            regiInfoBuilder.append(buildNo + "栋");
        }
        String unitNo = regiInfo.getUnitNo();
        if(StringUtils.isNotBlank(unitNo)) {
            regiInfoBuilder.append(unitNo + "单元");
        }
        String layer = regiInfo.getLayer();
        if(StringUtils.isNotBlank(layer)) {
            regiInfoBuilder.append(layer + "楼");
        }
        String roomNo = regiInfo.getRoomNo();
        if(StringUtils.isNotBlank(roomNo)) {
            regiInfoBuilder.append(roomNo + "号");
        }
        return regiInfoBuilder.toString();
    }

    private String getActualAddress(RegiInfo regiInfo) {
        StringBuilder addressBuilder = new StringBuilder();
        String region = regiInfo.getRegion();

        if(StringUtils.isNotBlank(region)) {
            addressBuilder.append(region);
        }
        String street = regiInfo.getStreet();
        if(StringUtils.isNotBlank(street)) {
            addressBuilder.append(street);
        }
        String apt = regiInfo.getApt();
        if(StringUtils.isNotBlank(apt)) {
            addressBuilder.append(apt + "号");
        }
        String buildNo = regiInfo.getBuildNo();
        if(StringUtils.isNotBlank(buildNo)) {
            addressBuilder.append(buildNo + "栋");
        }
        return addressBuilder.toString();
    }

    private void buildFileInfo(File file, RegiInfo regiInfo, CurrentUser userInfo) {
        file.setHcId(regiInfo.getId());
        file.setRightNo(regiInfo.getRightNo());
        file.setSubmitDate(new Date());
        file.setUserName(userInfo.getName());
        file.setUnitName(userInfo.getOrganization().getMc());
    }

}
