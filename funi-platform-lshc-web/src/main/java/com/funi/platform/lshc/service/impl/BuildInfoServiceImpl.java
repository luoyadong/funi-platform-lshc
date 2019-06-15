package com.funi.platform.lshc.service.impl;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.framework.biz.support.CurrentUserAccessor;
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
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.service.BuildInfoService;
import com.funi.platform.lshc.support.CensusConstants;
import com.funi.platform.lshc.utils.ExcelUtil;
import com.funi.platform.lshc.utils.NumberUtil;
import com.funi.platform.lshc.utils.SuperEntityUtils;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ExcelRegiInfoVo;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;
import com.funi.platform.lshc.vo.census.RegiInfoDetailVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sam on 2019/6/14.12:16 AM
 */
public class BuildInfoServiceImpl implements BuildInfoService {
    @Resource
    private RegiInfoMapper regiInfoMapper;
    @Resource
    private BuildInfoMapper buildInfoMapper;
    @Resource
    private CurrentUserAccessor currentUserAccessor;
    @Resource
    private EntInfoMapper entInfoMapper;
    @Resource
    private FileMapper fileMapper;

    @Override
    public List<BuildInfoVo> findBuildInfoList(BuildInfoQuery buildInfoQuery) {
        return buildInfoMapper.selectBuildInfoVoList(buildInfoQuery);
    }

    @Override
    public void createRegiInfo(RegiInfoDto regiInfoDto) {
        RegiInfo regiInfo = regiInfoDto.getRegiInfo();
        if (regiInfo == null) {
            regiInfo = new RegiInfo();
        }
        CurrentUser userInfo = getUserInfo();
        new SuperEntityUtils<>().buildCreateEntity(regiInfo, userInfo);
        regiInfo = getTestRegiInfo(regiInfo);
        // 校验房屋信息唯一性
        checkRegiInfoUnique(regiInfo);
        initRegiInfo(regiInfo, userInfo);
        // 保存房屋数据
        regiInfoMapper.insert(regiInfo);
        String hcId = regiInfo.getId();
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

        // 人口信息
        List<EntInfo> entInfoList = regiInfoDto.getEntInfoList();
        entInfoList = getTestEntInfoList();
        if(CollectionUtils.isNotEmpty(entInfoList)) {
            for(EntInfo entInfo : entInfoList) {
                new SuperEntityUtils<>().buildCreateEntity(entInfo, userInfo);
                entInfo.setHcId(hcId);
                entInfoMapper.insert(entInfo);
            }
        }
        // 附件信息
        List<File> fileList = regiInfoDto.getFileList();
        fileList = getTestFileList();
        if(CollectionUtils.isNotEmpty(fileList)) {
            for(File file : fileList) {
                new SuperEntityUtils<>().buildCreateEntity(file, userInfo);
                buildFileInfo(file, regiInfo, userInfo);
                fileMapper.insert(file);
            }
        }
    }

    private void checkRegiInfoUnique(RegiInfo regiInfo) {
        int houseCount = regiInfoMapper.checkRegiInfoUnique(regiInfo);
        if(houseCount > 0) {
            throw new RuntimeException("房屋信息重复，请再次确认房屋信息");
        }
    }

    private void initRegiInfo(RegiInfo regiInfo, CurrentUser userInfo) {
        regiInfo.setHouseId(regiInfoMapper.generateHouseId());
        // 默认状态是录入
        regiInfo.setHouseStatus(CensusConstants.HOUSE_STATUS_INPUT);
        regiInfo.setOrgCode(userInfo.getOrganization().getDm());
        regiInfo.setOrgName(userInfo.getOrganization().getMc());
        regiInfo.setUnitName(userInfo.getOrganization().getMc());
        regiInfo.setApplyUser(userInfo.getName());
        regiInfo.setReportDate(new Date());
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

    private void buildFileInfo(File file, RegiInfo regiInfo, CurrentUser userInfo) {
        file.setHcId(regiInfo.getId());
        file.setRightNo(regiInfo.getRightNo());
        file.setSubmitDate(new Date());
        file.setUserName(userInfo.getName());
        file.setUnitName(userInfo.getOrganization().getMc());
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
     * 获取当前登录用户
     * @return
     */
    private final CurrentUser getUserInfo() {
        return (CurrentUser)this.currentUserAccessor.getCurrentUser();
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

    @Override
    public List<ListRegiInfoVo> findRegiInfoVoList(RegiInfoQuery regiInfoQuery) {
        return regiInfoMapper.selectRegiInfoVoList(regiInfoQuery);
    }

    @Override
    public RegiInfoDetailVo findRegiInfoDetail(String hcId) {
        RegiInfoDetailVo regiInfoDetailVo = new RegiInfoDetailVo();
        RegiInfo regiInfo = regiInfoMapper.selectByPrimaryKey(hcId);
        regiInfoDetailVo.setRegiInfo(regiInfo);
        List<EntInfo> entInfoList = entInfoMapper.selectEntInfoListByHouseId(hcId);
        regiInfoDetailVo.setEntInfoList(entInfoList);
        List<File> fileList = fileMapper.selectFileListByHouseId(hcId);
        regiInfoDetailVo.setFileList(fileList);
        // TODO 查询处理过程信息
        // TODO 当前所处的环节
        return regiInfoDetailVo;
    }

    @Override
    public void batchRemoveRegiInfo(List<String> ids) {
        String userId = getUserInfo().getUserId();
        // 逻辑删除房屋信息
        regiInfoMapper.batchDeleteRegiInfo(ids, userId);
        // 逻辑删除房屋关联的入住人员信息
        entInfoMapper.batchDeleteEntInfo(ids, userId);
        // 逻辑删除房屋关联的附件信息
        fileMapper.batchDeleteFile(ids, userId);
    }

    @Override
    public void importRegiInfo(MultipartFile uploadFile) {

    }

    @Override
    public void exportRegiInfoVoList(List<String> ids, HttpServletResponse response) throws Exception {
        List<ExcelRegiInfoVo> excelRegiInfoVoList = regiInfoMapper.selectExcelRegiInfoVoList(ids);
        if(CollectionUtils.isEmpty(excelRegiInfoVoList)) {
            throw new RuntimeException("没有满足条件的数据");
        }
        ExcelUtil.excelExport("普查数据统计表.xls","普查数据", excelRegiInfoVoList, response);
    }

}
