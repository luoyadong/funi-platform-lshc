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
import com.funi.platform.lshc.service.ManageRegiInfoService;
import com.funi.platform.lshc.support.CensusConstants;
import com.funi.platform.lshc.support.UserManager;
import com.funi.platform.lshc.utils.ExcelUtil;
import com.funi.platform.lshc.utils.SuperEntityUtils;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ExcelRegiInfoVo;
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
    public void importRegiInfoList(MultipartFile uploadFile) throws IOException {
        List<ExcelRegiInfoVo> queryList = null;
        ExcelImporter<ExcelRegiInfoVo> importer = new ExcelImporter<>();
        queryList = importer.setStartRows(4).setHeadRows(3).setItemClass(ExcelRegiInfoVo.class).execute(uploadFile.getInputStream());
        // 校验Excel数据有效性
        checkExcelRegiInfoVoList(queryList);
        for(ExcelRegiInfoVo excelRegiInfoVo : queryList) {
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
    public void batchRemoveBuildInfo(List<String> ids) {

    }

    @Override
    public void batchRemoveRegiInfo(List<String> ids) {
        String userId = userManager.findUser().getUserId();
        // 逻辑删除房屋信息
        regiInfoMapper.batchDeleteRegiInfo(ids, userId);
        // 逻辑删除房屋关联的入住人员信息
        entInfoMapper.batchDeleteEntInfo(ids, userId);
        // 逻辑删除房屋关联的附件信息
        fileMapper.batchDeleteFile(ids, userId);
    }

    /**
     * 检查Excel批量导入的房屋数据是否已经在数据库中存在
     * @param excelRegiInfoVoList
     */
    private void checkExcelRegiInfoVoList(List<ExcelRegiInfoVo> excelRegiInfoVoList) {
        if(CollectionUtils.isEmpty(excelRegiInfoVoList)) {
            throw new RuntimeException("Excel中没有数据，请检查Excel格式是否正确或是否存在有效数据");
        }
        for(ExcelRegiInfoVo excelRegiInfoVo :excelRegiInfoVoList) {
            RegiInfo regiInfo = new RegiInfo();
            // 拷贝房屋对象属性
            BeanUtils.copyProperties(excelRegiInfoVo, regiInfo);
            List<RegiInfo> regiInfoList = regiInfoMapper.selectRegiInfoByUniqueQuery(regiInfo);
            if(regiInfoList.size() > 0) {
                throw new RuntimeException(getRegiInfoDesc(regiInfo));
            }
        }
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
