package com.funi.platform.lshc.controller;

import com.funi.framework.mvc.eic.controller.BaseController;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.entity.sys.Archives;
import com.funi.platform.lshc.entity.sys.HouseFile;
import com.funi.platform.lshc.query.ArchivesQuery;
import com.funi.platform.lshc.query.HouseFileQuery;
import com.funi.platform.lshc.service.ArchivesService;
import com.funi.platform.lshc.utils.ResultUtils;
import com.funi.platform.lshc.vo.ArchivesVo;
import com.funi.platform.lshc.vo.HouseFileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户档案室档案柜管理
 * Created by sam on 2018/11/11.6:37 PM
 */
@Controller
@RequestMapping("/archives/")
public class ArchivesController extends BaseController {
    @Autowired
    private ArchivesService archivesService;

    /**
     * 分页查询档案室列表
     * @param archivesQuery
     * @return
     */
    @RequestMapping("getArchivesVoList")
    @ResponseBody
    public List<ArchivesVo> getArchivesVoList(ArchivesQuery archivesQuery) {
        return archivesService.findArchivesVoList(archivesQuery, getUserInfo());
    }

    /***
     * 添加档案室
     * @param archives
     * @return
     */
    @RequestMapping("addArchives")
    @ResponseBody
    public Object addArchives(Archives archives) {
        try {
            archivesService.createArchives(archives, getUserInfo());
            return ResultUtils.getSuccessResult("档案室添加成功");
        } catch (Exception e) {
            return ResultUtils.getFailResult("档案室添加失败:" + e.getMessage());
        }
    }

    /**
     * 修改档案室名称
     *
     * @param archives
     * @return
     */
    @RequestMapping("editArchives")
    @ResponseBody
    public Object editArchives(Archives archives) {
        try {
            archivesService.updateArchives(archives, getUserInfo());
            return ResultUtils.getSuccessResult("档案室修改成功");
        } catch (Exception e) {
            return ResultUtils.getFailResult("档案室修改失败:" + e.getMessage());
        }
    }

    /**
     * 删除档案室
     *
     * @param archives
     * @return
     */
    @RequestMapping("deleteArchives")
    @ResponseBody
    public Object deleteArchives(Archives archives) {
        try {
            archivesService.removeArchives(archives, getUserInfo().getUserId());
            return ResultUtils.getSuccessResult("档案室删除成功");
        } catch (Exception e) {
            return ResultUtils.getFailResult("档案室删除失败:" + e.getMessage());
        }
    }

    /**
     * 根据档案室ID查询档案柜列表
     * @param houseFileQuery
     * @return
     */
    @RequestMapping("getHouseFileListDetail")
    @ResponseBody
    public List<HouseFileVo> getHouseFileListDetail(HouseFileQuery houseFileQuery) {
        return archivesService.findHouseFileDetailByArchiveId(houseFileQuery, getUserInfo());
    }

    /**
     * 添加档案柜
     * @param houseFile
     * @return
     */
    @RequestMapping("addHouseFile")
    @ResponseBody
    public Object addHouseFile(HouseFile houseFile) {
        try {
            archivesService.createHouseFile(houseFile, getUserInfo());
            return ResultUtils.getSuccessResult("档案柜添加成功");
        } catch (Exception e) {
            return ResultUtils.getFailResult("档案柜添加失败:" + e.getMessage());
        }
    }

    /**
     * 更新档案柜
     * @param houseFile
     * @return
     */
    @RequestMapping("editHouseFile")
    @ResponseBody
    public Object editHouseFile(HouseFile houseFile) {
        try {
            archivesService.updateHouseFile(houseFile, getUserInfo());
            return ResultUtils.getSuccessResult("档案柜修改成功");
        } catch (Exception e) {
            return ResultUtils.getFailResult("档案柜修改失败:" + e.getMessage());
        }
    }

    /**
     * 删除档案柜
     * @param houseFile
     * @return
     */
    @RequestMapping("deleteHouseFile")
    @ResponseBody
    public Object deleteHouseFile(HouseFile houseFile) {
        try {
            archivesService.removeHouseFile(houseFile, getUserInfo());
            return ResultUtils.getSuccessResult("档案柜删除成功");
        } catch (Exception e) {
            return ResultUtils.getFailResult("档案柜删除失败:" + e.getMessage());
        }
    }

    /**
     * 查询全部档案室列表
     * 用于下拉列表
     * @return
     */
    @RequestMapping("getArchivesList")
    @ResponseBody
    public List<ComboboxDto> getArchivesList(Boolean isForm) {
        return archivesService.findArchivesList(isForm);
    }

    /**
     * 根据档案室ID查询档案柜列表
     * 用于下拉列表
     * @param id
     * @return
     */
    @RequestMapping("getHouseFileList")
    @ResponseBody
    public List<ComboboxDto> getHouseFileList(String id, Boolean isForm) {
        return archivesService.findByArchiveId(id, isForm);
    }

}
