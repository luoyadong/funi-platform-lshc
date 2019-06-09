package com.funi.platform.lshc.controller;

import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用于处理区域相关业务逻辑
 * Created by sam on 2018/11/6.10:32 PM
 */
@Controller
@RequestMapping("/region/")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @ResponseBody
    @RequestMapping("getRegionList")
    public List<ComboboxDto> findContractStatus(Boolean isForm){
        return regionService.findRegionVoList(isForm);
    }
}
