package com.funi.platform.lshc.controller;

import com.funi.framework.mvc.eic.vo.ResultVo;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.service.CompletedRegiService;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
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

    /**
     * 根据楼栋ID批量导出楼栋和房屋信息
     * @param ids
     * @param response
     */
    @RequestMapping("exportBuildInfoVoList")
    @ResponseBody
    public Object exportBuildInfoVoList(@RequestParam("ids") List<String> ids, HttpServletResponse response) {
        try {
            completedRegiService.exportBuildInfoVoList(ids, response);
            return ResultVo.newResult("导出普查信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            ResultVo resultVo = ResultVo.newResult("导出普查信息失败：" + e.getMessage());
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

    /**
     * 分页查询房屋列表
     * @param regiInfoQuery
     * @return
     */
    @RequestMapping("getRegiInfoVoList")
    @ResponseBody
    public List<ListRegiInfoVo> getRegiInfoVoList(RegiInfoQuery regiInfoQuery) {
        return completedRegiService.findRegiInfoVoList(regiInfoQuery);
    }
}
