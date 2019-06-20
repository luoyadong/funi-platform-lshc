package com.funi.platform.lshc.service.impl;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.framework.data.migrate.excel.ExcelImporter;
import com.funi.platform.lshc.dto.RegiInfoDto;
import com.funi.platform.lshc.entity.census.BuildInfo;
import com.funi.platform.lshc.entity.census.EntInfo;
import com.funi.platform.lshc.entity.census.File;
import com.funi.platform.lshc.entity.census.RegiInfo;
import com.funi.platform.lshc.mapper.census.BuildInfoMapper;
import com.funi.platform.lshc.mapper.census.EntInfoMapper;
import com.funi.platform.lshc.mapper.census.FileMapper;
import com.funi.platform.lshc.mapper.census.RegiInfoMapper;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.BuildRegiQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.service.ManageRegiInfoService;
import com.funi.platform.lshc.support.CensusConstants;
import com.funi.platform.lshc.support.UserManager;
import com.funi.platform.lshc.utils.ExcelUtil;
import com.funi.platform.lshc.utils.SuperEntityUtils;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ExcelRegiInfoVo;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public List<ListRegiInfoVo> findRegiInfoVoList(RegiInfoQuery regiInfoQuery) {
        regiInfoQuery.setQueryType(CensusConstants.BUILD_QUERY_TYPE_MANAGE);
        return regiInfoMapper.selectRegiInfoVoList(regiInfoQuery);
    }

    @Override
    public void createRegiInfo(RegiInfoDto regiInfoDto) {
        RegiInfo regiInfo = regiInfoDto.getRegiInfo();
        if (regiInfo == null) {
            regiInfo = new RegiInfo();
        }
        CurrentUser userInfo = userManager.findUser();

//        regiInfo = getTestRegiInfo(regiInfo);
        // 校验房屋信息唯一性
        checkRegiInfoUnique(regiInfo);
        // 保存房屋信息和楼栋信息
        saveNewRegiInfo(regiInfo, userInfo);
        // 房屋主键ID
        String hcId = regiInfo.getId();
        // 人口信息
        List<EntInfo> entInfoList = regiInfoDto.getEntInfoList();
//        entInfoList = getTestEntInfoList();
        if(CollectionUtils.isNotEmpty(entInfoList)) {
            for(EntInfo entInfo: entInfoList) {
                saveNewEntInfo(entInfo, hcId, userInfo);
            }
        }
        // 附件信息
        List<File> fileList = regiInfoDto.getFileList();
//        fileList = getTestFileList();
        if(CollectionUtils.isNotEmpty(fileList)) {
            for(File file : fileList) {
                new SuperEntityUtils<>().buildCreateEntity(file, userInfo);
                buildFileInfo(file, regiInfo, userInfo);
                fileMapper.insert(file);
            }
        }
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
        // 编辑普查信息
        regiInfoMapper.updateByPrimaryKeySelective(regiInfo);
        // 编辑普查信息关联的入住人信息
        modifyEntInfoList(regiInfoDto.getEntInfoList(), houseId, user);
        // 编辑普查信息关联的附件信息
        modifyFileList(regiInfoDto.getFileList(), houseId, user);
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
    public void importRegiInfoList(MultipartFile uploadFile) throws IOException {
        List<ExcelRegiInfoVo> excelRegiInfoVoList = getRegiInfoByExcle(uploadFile);
        for(ExcelRegiInfoVo excelRegiInfoVo : excelRegiInfoVoList) {
            RegiInfo regiInfo = new RegiInfo();
            EntInfo entInfo = new EntInfo();
            // 拷贝房屋对象属性
            BeanUtils.copyProperties(excelRegiInfoVo, regiInfo);
            // 拷贝入住人员属性
            BeanUtils.copyProperties(excelRegiInfoVo, entInfo);
            CurrentUser userInfo = userManager.findUser();
            String hcId = null;
            // 因为房屋和入住人是一对多的关系，因此可能存在多条相同的房屋信息
            List<RegiInfo> regiInfoList = regiInfoMapper.selectRegiInfoByUniqueQuery(regiInfo);
            if(CollectionUtils.isNotEmpty(regiInfoList)) {
                RegiInfo regiInfoExist = regiInfoList.get(0);
                hcId = regiInfoExist.getId();
            } else {
                // 保存房屋信息
                saveNewRegiInfo(regiInfo, userInfo);
                regiInfo.getId();
            }
            // 保存入住人信息
            saveNewEntInfo(entInfo, hcId, userInfo);
        }
    }

    @Override
    public String checkRegiInfoList(MultipartFile uploadFile) throws IOException {
        List<ExcelRegiInfoVo> excelRegiInfoVoList = getRegiInfoByExcle(uploadFile);
        // 校验Excel数据有效性
        if(CollectionUtils.isEmpty(excelRegiInfoVoList)) {
            throw new RuntimeException("Excel中没有数据，请检查Excel格式是否正确或是否存在有效数据");
        }

        List<Integer> rowNoList = new ArrayList<>();
        for(int i = 0; i < excelRegiInfoVoList.size(); i ++) {
            // 获取行号
            int rowNo = i + CensusConstants.EXCEL_CONTENT_START_ROW_NO + 1;
            ExcelRegiInfoVo excelRegiInfoVo = excelRegiInfoVoList.get(i);
            RegiInfo regiInfo = new RegiInfo();
            // 拷贝房屋对象属性
            BeanUtils.copyProperties(excelRegiInfoVo, regiInfo);
            List<RegiInfo> regiInfoList = regiInfoMapper.selectRegiInfoByUniqueQuery(regiInfo);
            if(regiInfoList.size() > 0) {
                rowNoList.add(rowNo);
            }
        }
        if(CollectionUtils.isEmpty(rowNoList)) {
            return "普查信息校验通过";
        } else {
            return StringUtils.join(rowNoList, ",") + "行数据没有通过校验";
        }
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
        regiInfoBuilder.append("的房屋信息在数据库中存在多条数据，请核对房屋信息");
        return regiInfoBuilder.toString();
    }

    /**
     * 校验房屋唯一性
     * @param regiInfo
     */
    private void checkRegiInfoUnique(RegiInfo regiInfo) {
        List<RegiInfo> regiInfoList = regiInfoMapper.selectRegiInfoByUniqueQuery(regiInfo);
        if(CollectionUtils.isNotEmpty(regiInfoList)) {
            throw new RuntimeException("房屋信息重复，请再次确认房屋信息");
        }
    }

    /**
     * 保存新增的房屋信息，如果楼栋不存在则保存楼栋信息
     * @param regiInfo
     * @param userInfo
     */
    private void saveNewRegiInfo(RegiInfo regiInfo, CurrentUser userInfo) {
        new SuperEntityUtils<>().buildCreateEntity(regiInfo, userInfo);
        regiInfo.setHouseId(regiInfoMapper.generateHouseId());
        // 默认状态是录入
        regiInfo.setHouseStatus(CensusConstants.HOUSE_STATUS_INPUT);
        regiInfo.setOrgCode(userInfo.getOrganization().getDm());
        regiInfo.setOrgName(userInfo.getOrganization().getMc());
        regiInfo.setUnitName(userInfo.getOrganization().getMc());
        regiInfo.setApplyUser(userInfo.getName());
        regiInfo.setReportDate(new Date());
        // 保存房屋数据
        regiInfoMapper.insert(regiInfo);
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
    }

    private String getActualAddress(RegiInfo regiInfo) {
        StringBuilder addressBuilder = new StringBuilder();
        String addressRegion = regiInfo.getAddressRegion();
        if(StringUtils.isNotBlank(addressRegion)) {
            addressBuilder.append(addressRegion + "区");
        }
        String addressCounty = regiInfo.getAddressCounty();
        if(StringUtils.isNotBlank(addressCounty)) {
            addressBuilder.append(addressCounty + "乡");
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

    private void buildFileInfo(File file, RegiInfo regiInfo, CurrentUser userInfo) {
        file.setHcId(regiInfo.getId());
        file.setRightNo(regiInfo.getRightNo());
        file.setSubmitDate(new Date());
        file.setUserName(userInfo.getName());
        file.setUnitName(userInfo.getOrganization().getMc());
    }

    private RegiInfo getTestRegiInfo(RegiInfo regiInfo) {
        regiInfo.setRegion("高新区");
        regiInfo.setStreet("中和街道");
        regiInfo.setProjectName("龙湖九里晴川");
        regiInfo.setMapCode("map_code_0001");
        regiInfo.setEstateUnitName("九里晴川物业");
        regiInfo.setAddressCity("成都市");
        regiInfo.setAddressRegion("高新区");
        regiInfo.setAddressCounty("中和镇");
        regiInfo.setApt("11111111");
        regiInfo.setBuildNo("1");
        regiInfo.setUnitNo("2");
        regiInfo.setLayer("3");
        regiInfo.setRoomNo("4");
        regiInfo.setRightAddr("房产证实际地址");
        regiInfo.setHouseArea(new BigDecimal("101.01"));
        regiInfo.setInnerHouseArea(new BigDecimal("88.87"));
        regiInfo.setHouseRoom("一");
        regiInfo.setHouseHall("一");
        regiInfo.setHouseBathroom("一");
        regiInfo.setIsRegi("1");
        regiInfo.setRightNo("权123456");
        regiInfo.setBuildDate("2016-06");
        regiInfo.setHouseType("商品房");
        regiInfo.setHouseStructure("框架");
        regiInfo.setHouseUse("住宅");
        regiInfo.setLandStatus("国有");
        regiInfo.setPreSaleNo("预111");
        regiInfo.setFitStatus("简装");
        regiInfo.setIsCheckIn("1");
        regiInfo.setPersonNum(6);
        regiInfo.setIsRent("1");
        regiInfo.setRentStartDate("2017-01");
        regiInfo.setRentEndDate("2018-06");
        regiInfo.setCommon("扩展字段");
        return regiInfo;
    }

    private List<EntInfo> getTestEntInfoList() {
        List<EntInfo> entInfoList = new ArrayList<>();
        EntInfo entInfo = new EntInfo();
        entInfo.setEntName("张三三");
        entInfo.setSex("男");
        entInfo.setEntType("三无人员");
        entInfo.setEntNation("汉族");
        entInfo.setEntNative("四川成都");
        entInfo.setTel("13888888888");
        entInfo.setMarriageStatus("0");
        entInfo.setIdType("身份证");
        entInfo.setIdNo("510107123465977");
        entInfo.setCareer("无业");
        entInfoList.add(entInfo);
        return entInfoList;
    }

    private List<File> getTestFileList() {
        List<File> fileList = new ArrayList<>();
        File file = new File();
        file.setFileName("测试图片1");
        file.setFileSize(new BigDecimal("1"));
        file.setFileType("JPEG");
        file.setUrl("http://www.baidu.com");
        fileList.add(file);
        return fileList;
    }

}
