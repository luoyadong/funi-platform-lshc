package com.funi.platform.lshc.controller;

import com.funi.framework.mvc.eic.controller.BaseController;
import com.funi.platform.lshc.entity.charge.BillTemplet;
import com.funi.platform.lshc.query.BillTempQuery;
import com.funi.platform.lshc.service.SettingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * @desc:系统设置controller
 * @author by yadong.luo on 2018/11/12
 */
@Controller
@RequestMapping("/SettingController")
public class SettingController extends BaseController {

        @Resource
        private SettingService settingService;

        /**
         * 查询模板列表
         * @param query
         * @return
         */
        @RequestMapping(value = "/findBillTempList",method = RequestMethod.POST)
        @ResponseBody
        public List<BillTemplet> findBillTempList(BillTempQuery query){

                List<BillTemplet> tempList = settingService.findBillTempList(query);
                return tempList;
        }
        /**
         * 创建模板
         * @param obj
         * @return
         */
        @RequestMapping(value = "/addBillTemp",method = RequestMethod.POST)
        @ResponseBody
        public int addBillTemp(BillTemplet obj){

                int addNum = settingService.addBillTemp(obj);
                return addNum;
        }
        /**
         * 更新模板
         * @param obj
         * @return
         */
        @RequestMapping(value = "/updateBillTemp",method = RequestMethod.POST)
        @ResponseBody
        public int updateBillTemp(BillTemplet obj){

                int updateNum = settingService.updateBillTemp(obj);
                return updateNum;
        }
        /**
         * 删除模板
         * @param id
         * @return
         */
        @RequestMapping(value = "/delBillTemp",method = RequestMethod.POST)
        @ResponseBody
        public int delBillTemp(String id){

                int delNum  = settingService.delBillTempById(id);
                return delNum;
        }
}
