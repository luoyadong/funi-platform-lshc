package com.funi.platform.lshc.controller;

import com.funi.framework.mvc.eic.vo.ResultVo;
import com.funi.framework.workflow.eic.po.AuditConclusions;
import com.funi.platform.lshc.dto.RegiInfoDto;
import com.funi.platform.lshc.entity.census.RegiInfo;
import com.funi.platform.lshc.enumatation.BusinessType;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.service.LshcWorkFlowService;
import com.funi.platform.lshc.service.ManageRegiInfoService;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ExcelRegiInfoVo;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private LshcWorkFlowService lshcWorkFlowService;

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
     * 根据楼栋ID批量导出楼栋和房屋信息
     * @param ids
     * @param response
     */
    @RequestMapping("exportBuildInfoVoList")
    public void exportBuildInfoVoList(@RequestBody List<String> ids, HttpServletResponse response) {
        try {
            manageRegiInfoService.exportBuildInfoVoList(ids, response);
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
    public List<ListRegiInfoVo> getRegiInfoVoList( RegiInfoQuery regiInfoQuery) {
        return manageRegiInfoService.findRegiInfoVoList(regiInfoQuery);
    }

    /**
     * 预留接口：校验普查信息是否存在重复数据
     * @param regiInfo
     * @return
     */
    @RequestMapping("/checkRegiInfo")
    @ResponseBody
    public Object checkRegiInfo(@RequestBody RegiInfo regiInfo) {
        try {
            return ResultVo.newResult(manageRegiInfoService.checkRegiInfo(regiInfo));
        } catch(Exception e) {
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("普查信息校验失败，原因：" + e.getMessage());
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

    /**
     * 添加普查信息
     * @param regiInfoDto
     * @return
     */
    @RequestMapping("addRegiInfo")
    @ResponseBody
    public Object addRegiInfo(@RequestBody RegiInfoDto regiInfoDto) {
        try {
            return ResultVo.newResult(manageRegiInfoService.createRegiInfo(regiInfoDto, false));
        } catch (Exception e) {
            e.printStackTrace();
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("添加普查信息失败：" + e.getMessage());
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

    /**
     * 提交普查信息
     * @param regiInfoDto
     * @return
     */
    @RequestMapping(value="submitRegiInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object submitRegiInfo(@RequestBody RegiInfoDto regiInfoDto) {
        try {
            return ResultVo.newResult(manageRegiInfoService.createRegiInfo(regiInfoDto, true));
        } catch (Exception e) {
            e.printStackTrace();
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("提交普查信息失败：" + e.getMessage());
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

    /**
     * 批量提交普查信息
     * @param ids
     */
    @RequestMapping(value = "batchSubmitRegiInfo", method = RequestMethod.POST)
    @ResponseBody
    public void batchSubmitRegiInfo(@RequestBody List<String> ids) {
        try {
            manageRegiInfoService.batchSubmitRegiInfo(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 编辑普查信息
     * @param regiInfoDto
     * @return
     */
    @RequestMapping("editRegiInfo")
    @ResponseBody
    public Object editRegiInfo(@RequestBody RegiInfoDto regiInfoDto) {
        try {
            manageRegiInfoService.modifyRegiInfo(regiInfoDto);
            return ResultVo.newResult("编辑普查信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("编辑普查信息失败：" + e.getMessage());
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

    /**
     * 校验批量导入的普查信息是否有效，是否与已存在普查信息重复
     * @param uploadFile
     * @return
     */
    @RequestMapping("/checkRegiInfoList")
    @ResponseBody
    public Object checkRegiInfoList(MultipartFile uploadFile) {
        try {
            return ResultVo.newResult(manageRegiInfoService.checkRegiInfoList(uploadFile));
        } catch(Exception e) {
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("普查信息校验失败，原因：" + e.getMessage());
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
            return ResultVo.newResult("删除楼栋信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("删除楼栋信息失败");
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

    /**
     * 根据普查信息ID集合逻辑删除普查信息以及关联数据
     * @param ids
     * @return
     */
    @RequestMapping("batchDeleteRegiInfo")
    @ResponseBody
    public Object batchDeleteRegiInfo(@RequestBody List<String> ids) {
        try {
            manageRegiInfoService.batchRemoveRegiInfo(ids);
            return ResultVo.newResult("删除普查信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("删除普查信息失败");
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

    @ResponseBody
    @RequestMapping("/conclusions")
    public Object findAuditConclusions(String serviceNum,String nodeName) {
        try {
            List<AuditConclusions> conList = lshcWorkFlowService.findWorkFlowConclusions(serviceNum, nodeName,BusinessType.pnew);
            return ResultVo.newResult(conList);
        } catch (Exception e) {
            e.printStackTrace();
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("审批结论失败");
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

    /**
     * 校验批量导入的普查信息是否有效，是否与已存在普查信息重复,并返回数据
     * @param uploadFile
     * @return
     */
    @RequestMapping("/checkReturnRegiInfoList")
    @ResponseBody
    public Object checkReturnRegiInfoList(MultipartFile uploadFile) {
        try {
            return ResultVo.newResult(manageRegiInfoService.checkReturnRegiInfoList(uploadFile));
        } catch(Exception e) {
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("普查信息校验失败，原因：" + e.getMessage());
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

    /**
     * 批量导入普查信息
     * @throws Exception
     */
    @RequestMapping("/importDataRegiInfoList")
    @ResponseBody
    public Object importDataRegiInfoList(@RequestBody List<ExcelRegiInfoVo> excelRegiInfoVoList) {
        try {
            manageRegiInfoService.importRegiInfoList(excelRegiInfoVoList);
            return ResultVo.newResult("批量导入普查信息成功");
        } catch(Exception e) {
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("批量导入普查信息失败，原因：" + e.getMessage());
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

}
