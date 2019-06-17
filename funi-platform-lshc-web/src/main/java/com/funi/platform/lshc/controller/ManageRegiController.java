package com.funi.platform.lshc.controller;

import com.funi.framework.mvc.eic.vo.ResultVo;
import com.funi.platform.lshc.dto.RegiInfoDto;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.service.ManageRegiInfoService;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 管理界面，处理新建和初审不通过的数据
 * Created by sam on 2019/6/17.10:20 PM
 */
@Controller
@RequestMapping("/manage/")
public class ManageRegiController {

    @Autowired
    private ManageRegiInfoService manageRegiInfoService;

    /**
     * 分页查询楼栋列表
     * @param buildInfoQuery
     * @return
     */
    @RequestMapping("getBuildInfoList")
    @ResponseBody
    public List<BuildInfoVo> getBuildInfoList(BuildInfoQuery buildInfoQuery) {
        return manageRegiInfoService.findBuildInfoList(buildInfoQuery);
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
            manageRegiInfoService.createRegiInfo(regiInfoDto);
            return ResultVo.newResult("添加普查信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("添加普查信息失败：" + e.getMessage());
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

    /**
     * 批量导入普查信息
     * @throws Exception
     */
    @RequestMapping("/importRegiInfoList")
    @ResponseBody
    public Object importRegiInfoList(MultipartFile uploadFile) {
        try {
            manageRegiInfoService.importRegiInfoList(uploadFile);
            return ResultVo.newResult("批量导入普查信息成功");
        } catch(Exception e) {
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("批量导入普查信息失败，原因：" + e.getMessage());
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

    /**
     * 根据楼栋ID集合删除楼栋以及普查信息
     * @param ids
     * @return
     */
    @RequestMapping("batchDeleteBuildInfo")
    @ResponseBody
    public Object batchDeleteBuildInfo(@RequestBody List<String> ids) {
        try {
            manageRegiInfoService.batchRemoveBuildInfo(ids);
            return ResultVo.newResult("批量删除楼栋信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("删除楼栋信息失败");
            resultVo.setSuccess(false);
            return resultVo;
        }
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
            manageRegiInfoService.batchRemoveRegiInfo(ids);
            return ResultVo.newResult("批量删除普查信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("删除普查信息失败");
            resultVo.setSuccess(false);
            return resultVo;
        }
    }
}
