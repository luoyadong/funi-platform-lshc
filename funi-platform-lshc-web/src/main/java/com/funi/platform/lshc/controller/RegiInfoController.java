package com.funi.platform.lshc.controller;

import com.funi.framework.mvc.eic.controller.BaseController;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.service.RegiInfoService;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;
import com.funi.platform.lshc.vo.census.RegiInfoDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 综合查询
 * Created by sam on 2019/6/17.3:44 PM
 */
@Controller
@RequestMapping("/regiInfo/")
public class RegiInfoController extends BaseController {
    @Autowired
    private RegiInfoService regiInfoService;

    /**
     * 分页查询楼栋列表
     * @param buildInfoQuery
     * @return
     */
    @RequestMapping("getBuildInfoList")
    @ResponseBody
    public List<BuildInfoVo> getBuildInfoList(BuildInfoQuery buildInfoQuery) {
        return regiInfoService.findBuildInfoList(buildInfoQuery);
    }

    /**
     * 分页查询房屋列表
     * @param regiInfoQuery
     * @return
     */
    @RequestMapping("getRegiInfoVoList")
    @ResponseBody
    public List<ListRegiInfoVo> getRegiInfoVoList(RegiInfoQuery regiInfoQuery) {
        return regiInfoService.findRegiInfoVoList(regiInfoQuery);
    }

    /**
     * 根据房屋编号查询房屋详情
     * @param hcId
     * @return
     */
    @RequestMapping("getRegiInfoDetail")
    @ResponseBody
    public RegiInfoDetailVo getRegiInfoDetail(String hcId) {
        return regiInfoService.findRegiInfoDetail(hcId);
    }

    /**
     * 根据房屋编号导出普查信息列表
     * @param ids
     * @param response
     */
    @RequestMapping("exportRegiInfoVoList")
    public void exportRegiInfoVoList(@RequestBody List<String> ids, HttpServletResponse response) {
        try {
            regiInfoService.exportRegiInfoVoList(ids, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
