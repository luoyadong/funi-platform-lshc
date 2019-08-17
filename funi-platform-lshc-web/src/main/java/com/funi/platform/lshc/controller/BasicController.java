package com.funi.platform.lshc.controller;

import com.funi.framework.mvc.eic.vo.ResultVo;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by sam on 2019/6/17.3:53 PM
 */
@Controller
@RequestMapping("/basic/")
public class BasicController {
    @Autowired
    private BasicService basicService;

    /**
     * 查询字典表，用于前端展示下拉列表
     * @param type 见常量类：CensusConstants的DICTIONARY_TYPE相关配置
     * @return
     */
    @RequestMapping("getDictionaryListName")
    @ResponseBody
    public List<ComboboxDto> getDictionaryListName(String type, String showALL) {
        return basicService.findDictionaryListName(type, showALL);
    }

    /**
     * 查询拉萨市全部社区信息
     * @return
     */
    @RequestMapping("getRegionList")
    @ResponseBody
    public List<ComboboxDto> getRegionList() {
        return basicService.findRegionList();
    }

    /**
     * 查询拉萨市全部区县信息
     * @return
     */
    @RequestMapping("getAllRegionList")
    @ResponseBody
    public List<ComboboxDto> getAllRegionList(String showALL) {
        return basicService.findAllRegionList(showALL);
    }

    /**
     * 根据区县ID查询全部街道信息
     * @param regionId
     * @return
     */
    @RequestMapping("getAllBlockListByRegionId")
    @ResponseBody
    public List<ComboboxDto> getAllBlockListByRegionId(String regionId, String showALL) {
        return basicService.findAllRegionListByCityId(regionId, showALL);
    }

    /**
     * 根据街道ID查询全部社区信息
     * @param blockId
     * @return
     */
    @RequestMapping("getAllStreetListByRegionId")
    @ResponseBody
    public List<ComboboxDto> getAllStreetListByRegionId(String blockId, String showALL) {
        return basicService.findAllStreetListByRegionId(blockId, showALL);
    }

    /**
     * 获取打印信息
     * @return
     */
    @RequestMapping("getPrintInfo")
    @ResponseBody
    public Object getPrintInfo() {
        return ResultVo.newResult(basicService.findCurrentUserRegionCodeString());
    }
}
