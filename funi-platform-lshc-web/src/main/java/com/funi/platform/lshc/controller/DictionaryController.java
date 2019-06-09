package com.funi.platform.lshc.controller;

import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.service.BizParameterService;
import com.funi.platform.lshc.utils.ContractUtils;
import com.funi.platform.lshc.utils.RentTypeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典控制器
 * @author 3
 */
@RequestMapping("/dictionary")
@Controller
public class DictionaryController {

    @Resource
    private BizParameterService bizParameterService;

    /**
     * 获取房屋状态
     * @return
     */
    @ResponseBody
    @RequestMapping("/contract/status")
    public List<ComboboxDto> findContractStatus(){
        return ContractUtils.findContractStatus();
    }

    /**
     * 获取区域集合
     * @return
     */
    @ResponseBody
    @RequestMapping("/region")
    public List<ComboboxDto> findAllRegion(){
        return null;
    }

    @ResponseBody
     @RequestMapping("/rent/type")
     public List<ComboboxDto> findAllRentType(){
        return RentTypeUtils.findRentType();
    }

    @ResponseBody
    @RequestMapping("/parameter/type")
    public List<ComboboxDto> findAllParameterType(Boolean isForm){
        return bizParameterService.findAllType(isForm);
    }



}
