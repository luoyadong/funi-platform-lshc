package com.funi.platform.lshc.controller;

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
     * 查询拉萨市全部区县信息
     * @return
     */
    @RequestMapping("getAllRegionList")
    @ResponseBody
    public List<ComboboxDto> getAllRegionList() {
        return basicService.findAllRegionList();
    }

    /**
     * 根据区县ID查询全部街道信息
     * @param regionId
     * @return
     */
    @RequestMapping("getAllBlockListByRegionId")
    @ResponseBody
    public List<ComboboxDto> getAllBlockListByRegionId(String regionId) {
        return basicService.findAllRegionListByCityId(regionId);
    }

    /**
     * 根据街道ID查询全部社区信息
     * @param blockId
     * @return
     */
    @RequestMapping("getAllStreetListByRegionId")
    @ResponseBody
    public List<ComboboxDto> getAllStreetListByRegionId(String blockId) {
        return basicService.findAllStreetListByRegionId(blockId);
    }
}
