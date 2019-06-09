package com.funi.platform.lshc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.framework.mvc.eic.controller.BaseController;

/**
 * @desc:资金拨付
 * @author by yadong.luo on 2017/6/2.
 */
@Controller
@RequestMapping("/FoundsApplyController")
public class FoundsApplyController extends BaseController {
    /**
     * 查询资金划拨申报分页列表
     * @param
     * @return
     */
    @RequestMapping(value = "/findListByPage",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String,Object>> findListByPage(){
        CurrentUser userInfo = this.getUserInfo();
        List<String> orgs = new ArrayList<String>();
        if(this.getUserInfo().getOrganization() != null){
            orgs.add(this.getUserInfo().getOrganization().getDm());
        }
        List<Map<String,Object>> rtMap = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("PERIOD_CODE", userInfo.getUserId());
        map.put("BIZ_NO", userInfo.getName());
        rtMap.add(map);
        return rtMap;
    }

}
