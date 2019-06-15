package com.funi.platform.lshc.controller;

import com.funi.framework.mvc.eic.controller.BaseController;
import com.funi.framework.mvc.eic.vo.ResultVo;
import com.funi.platform.lshc.dto.RegiInfoDto;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.service.BuildInfoService;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;
import com.funi.platform.lshc.vo.census.RegiInfoDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sam on 2019/6/14.12:06 AM
 */
@Controller
@RequestMapping("/build/")
public class BuildInfoController extends BaseController {

    @Autowired
    private BuildInfoService buildInfoService;

    /**
     * 分页查询楼栋列表
     * @param buildInfoQuery
     * @return
     */
    @RequestMapping("getBuildInfoList")
    @ResponseBody
    public List<BuildInfoVo> getBuildInfoList(BuildInfoQuery buildInfoQuery) {
        return buildInfoService.findBuildInfoList(buildInfoQuery);
    }

    /**
     * 分页查询房屋列表
     * @param regiInfoQuery
     * @return
     */
    @RequestMapping("getRegiInfoVoList")
    @ResponseBody
    public List<ListRegiInfoVo> getRegiInfoVoList(RegiInfoQuery regiInfoQuery) {
        return buildInfoService.findRegiInfoVoList(regiInfoQuery);
    }

    /**
     * 添加普查信息
     * @param regiInfoDto
     * @return
     */
    @RequestMapping("addRegiInfo")
    @ResponseBody
    public Object addRegiInfo(RegiInfoDto regiInfoDto) {
        try {
            buildInfoService.createRegiInfo(regiInfoDto);
            return ResultVo.newResult("添加普查信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("添加普查信息失败");
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

    /**
     * 根据房屋编号查询房屋详情
     * @param houseId
     * @return
     */
    @RequestMapping("getRegiInfoDetail")
    @ResponseBody
    public RegiInfoDetailVo getRegiInfoDetail(String houseId) {
        return buildInfoService.findRegiInfoDetail(houseId);
    }

    /**
     * 根据房屋编号删除房屋信息
     * @param ids
     * @return
     */
    @RequestMapping("batchDeleteRegiInfo")
    @ResponseBody
    public Object batchDeleteRegiInfo(@RequestBody List<String> ids) {
        try {
            buildInfoService.batchRemoveRegiInfo(ids);
            return ResultVo.newResult("批量删除普查信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("删除普查信息失败");
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

    /**
     * 批量导入普查信息
     * @throws Exception
     */
    @RequestMapping("/importRegiInfo")
    @ResponseBody
    public Object importRegiInfo(MultipartFile uploadFile) {
        try {
            importRegiInfo(uploadFile);
            return ResultVo.newResult("批量导入普查信息成功");
        } catch(Exception e) {
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("批量导入普查信息失败，原因：" + e.getMessage());
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

    /**
     * 根据查询条件导出普查信息列表
     * @param regiInfoQuery
     * @param response
     */
    @RequestMapping("exportRegiInfoVoList")
    public void exportRegiInfoVoList(RegiInfoQuery regiInfoQuery, HttpServletResponse response) {
        try {
            buildInfoService.exportRegiInfoVoList(regiInfoQuery, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
