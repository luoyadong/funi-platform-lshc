package com.funi.platform.lshc.controller;

import com.funi.framework.mvc.eic.controller.BaseController;
import com.funi.platform.lshc.entity.contr.ContrTemplet;
import com.funi.platform.lshc.enumatation.CommonBizType;
import com.funi.platform.lshc.query.ContrTempQuery;
import com.funi.platform.lshc.service.SettingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 3
 */
@RequestMapping("/template/")
@Controller
public class TemplateController extends BaseController {
    @Resource
    private SettingService settingService;

    @ResponseBody
    @RequestMapping("/create")
    public void create(){

    }

    @ResponseBody
    @RequestMapping("/findValidTemplet")
    public ContrTemplet findValidTemplet(ContrTempQuery query){
        if(null == query){
            query = new ContrTempQuery();
        }
        //一定是近期启动的模板
        query.setValidFlg(String.valueOf(CommonBizType.BIZ_OPEN.getBizType()));
        List<ContrTemplet> tempList = settingService.findContrTempList(query);
        if(null != tempList && tempList.size() >0){
            return tempList.get(0);
        }
        return new ContrTemplet();
    }

    /**
     * 上传合同模板，返回url
     * @param uploadFile 附件文件
     * @return 执行消息
     */
    @RequestMapping("/addTemplet")
    @ResponseBody
    public ContrTemplet addTemplet(MultipartFile uploadFile){
        ContrTemplet rtObj = settingService.createFile(uploadFile,this.getUserInfo());
        return rtObj;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<ContrTemplet> findList(ContrTempQuery query){
        List<ContrTemplet> tempList = settingService.findContrTempList(query);
        return tempList;
    }

    /**
     * 更新模板
     * @param obj
     * @return
     */
    @RequestMapping(value = "/updateContrTemp",method = RequestMethod.POST)
    @ResponseBody
    public int updateContrTemp(ContrTemplet obj){

        int updateNum = settingService.updateContrTemp(obj);
        return updateNum;
    }
    /**
     * 删除模板
     * @param id
     * @return
     */
    @RequestMapping(value = "/delContrTemp",method = RequestMethod.POST)
    @ResponseBody
    public int delContrTemp(String id){

        int delNum  = settingService.delContrTempById(id);
        return delNum;
    }
}
