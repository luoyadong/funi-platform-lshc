package com.funi.platform.lshc.support;

import com.funi.platform.lshc.entity.census.EntInfo;
import com.funi.platform.lshc.entity.census.File;
import com.funi.platform.lshc.entity.census.RegiInfo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 2019/6/17.3:59 PM
 */
@Component
public class BasicHelper {

    /**
     * 构造测试普查信息对象
     * @param regiInfo
     * @return
     */
    public RegiInfo getTestRegiInfo(RegiInfo regiInfo) {
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

    /**
     * 构造测试入住人员对象
     * @return
     */
    public List<EntInfo> getTestEntInfoList() {
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

    /**
     * 构造测试附件信息对象
     * @return
     */
    public List<File> getTestFileList() {
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
