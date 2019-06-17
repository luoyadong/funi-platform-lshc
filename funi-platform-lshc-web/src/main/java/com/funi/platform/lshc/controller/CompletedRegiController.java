package com.funi.platform.lshc.controller;

import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.service.CompletedRegiService;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 已办件
 * Created by sam on 2019/6/17.4:34 PM
 */
@Controller
@RequestMapping("/completed/")
public class CompletedRegiController {
    @Autowired
    private CompletedRegiService completedRegiService;

    /**
     * 分页查询楼栋列表
     * @param buildInfoQuery
     * @return
     */
    @RequestMapping("getBuildInfoList")
    @ResponseBody
    public List<BuildInfoVo> getBuildInfoList(BuildInfoQuery buildInfoQuery) {
        return completedRegiService.findBuildInfoList(buildInfoQuery);
    }
}
