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
     * 查询西藏全部市信息
     * @return
     */
    @RequestMapping("getAllCityList")
    @ResponseBody
    public List<ComboboxDto> getAllCityList() {
        return basicService.findAllCityList();
    }

    /**
     * 根据市ID查询全部区县信息
     * @param cityId
     * @return
     */
    @RequestMapping("getAllRegionListByCityId")
    @ResponseBody
    public List<ComboboxDto> getAllRegionListByCityId(String cityId) {
        return basicService.findAllRegionListByCityId(cityId);
    }

    /**
     * 根据区县ID查询全部街道信息
     * @param regionId
     * @return
     */
    @RequestMapping("getAllBlockListByRegionId")
    @ResponseBody
    public List<ComboboxDto> getAllBlockListByRegionId(String regionId) {
        return basicService.findAllBlockListByRegionId(regionId);
    }
}
