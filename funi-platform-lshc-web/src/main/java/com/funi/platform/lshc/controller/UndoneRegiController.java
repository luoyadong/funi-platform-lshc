package com.funi.platform.lshc.controller;

import com.funi.framework.mvc.eic.vo.ResultVo;
import com.funi.platform.lshc.dto.RegiInfoAuditDto;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.service.UndoneRegiInfoService;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 待办件
 * Created by sam on 2019/6/17.4:33 PM
 */
@Controller
@RequestMapping("/undone/")
public class UndoneRegiController {

    @Autowired
    private UndoneRegiInfoService undoneRegiInfoService;

    /**
     * 分页查询楼栋列表
     * @param buildInfoQuery
     * @return
     */
    @RequestMapping("getBuildInfoList")
    @ResponseBody
    public List<BuildInfoVo> getBuildInfoList(BuildInfoQuery buildInfoQuery) {
        return undoneRegiInfoService.findBuildInfoList(buildInfoQuery);
    }

    /**
     * 根据楼栋ID批量导出楼栋和房屋信息
     * @param ids
     * @param response
     */
    @RequestMapping("exportBuildInfoVoList")
    public void exportBuildInfoVoList(@RequestParam("ids") List<String> ids, HttpServletResponse response) {
        try {
            undoneRegiInfoService.exportBuildInfoVoList(ids, response);
        } catch (Exception e) {
            e.printStackTrace();
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
        return undoneRegiInfoService.findRegiInfoVoList(regiInfoQuery);
    }

    /**
     * 根据普查信息ID集合执行批量审批操作
     * @param regiInfoAuditDto 批量审核普查信息数据传输对象
     */
    @RequestMapping("batchAuditRegiInfoList")
    @ResponseBody
    public Object batchAuditRegiInfoList(@RequestBody RegiInfoAuditDto regiInfoAuditDto) {
        try {
            undoneRegiInfoService.batchAuditRegiInfoList(regiInfoAuditDto);
            return ResultVo.newResult("审批成功!");
        } catch (Exception e) {
            e.printStackTrace();
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("审批失败：" + e.getMessage());
            resultVo.setSuccess(false);
            return resultVo;
        }
    }
}
