package com.funi.platform.lshc.controller;

import com.funi.framework.mvc.eic.controller.BaseController;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.dto.ManageOrgDto;
import com.funi.platform.lshc.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于处理公共业务
 * Created by sam on 2018/11/22.9:42 AM
 */
@Controller
@RequestMapping("/common/")
public class CommonController extends BaseController {
    @Autowired
    private CommonService commonService;

    /**
     * 根据字典类型获取该类型字典数据集合
     *
     * @param type 在枚举类中定义
     * @return
     */
    @RequestMapping("getDictionaryList")
    @ResponseBody
    public List<ComboboxDto> getDictionaryList(String type, Boolean isForm) {
        return commonService.findDictionaryList(type, isForm);
    }

    @RequestMapping("getDictionaryListName")
    @ResponseBody
    public List<ComboboxDto> getDictionaryListName(String type) {
        return commonService.findDictionaryListName(type);
    }

    /**
     * 获取全域管理机构、或者根据userId获取管理机构
     *
     * @param type 1:全域查询 0：根据当前userId查询，在枚举类中定义
     * @return
     */
    @RequestMapping("getManagerOrgList")
    @ResponseBody
    public List<ComboboxDto> getManagerOrgList(String type, Boolean isForm) {
        List<ComboboxDto> rtList = new ArrayList<ComboboxDto>();
        List<ManageOrgDto> tmpList = commonService.findManagerOrgList(type, this.getUserInfo());
        if(null != tmpList && tmpList.size() >0){
            if(isForm == null || ! isForm) {
                ComboboxDto allDto = new ComboboxDto("全部", "");
                rtList.add(allDto);
            }
            ComboboxDto tmpCom= null;
            for(ManageOrgDto obj:tmpList){
                if(null != obj){
                    tmpCom = new ComboboxDto();
                    tmpCom.setName(obj.getOrgName());
                    tmpCom.setValue(obj.getOrgCode());
                    rtList.add(tmpCom);
                }
            }
        }
        return rtList;
    }
}
