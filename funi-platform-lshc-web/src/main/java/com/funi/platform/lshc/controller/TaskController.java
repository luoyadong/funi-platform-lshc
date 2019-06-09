package com.funi.platform.lshc.controller;

import com.funi.platform.lshc.service.ChargeService;
import com.funi.platform.lshc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 定时任务处理
 * Created by sam on 2018/11/29.9:52 PM
 */
@Controller
@RequestMapping("/task/")
public class TaskController {
    @Autowired
    private ChargeService chargeService;

    /**
     * 每天凌晨一点处理欠租合同
     */
    @RequestMapping(value = "sync")
    @ResponseBody
    public Object syncArrearageDetail(){
        try {
            chargeService.syncArrearageDetail();
            return ResultUtils.getSuccessResult("同步欠租信息成功");
        } catch (Exception e) {
            return ResultUtils.getFailResult("同步欠租信息失败:" + e.getMessage());
        }
    }
}
