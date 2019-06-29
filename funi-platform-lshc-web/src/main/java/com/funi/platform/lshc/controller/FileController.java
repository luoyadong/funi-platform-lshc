package com.funi.platform.lshc.controller;

import com.alibaba.fastjson.JSONArray;
import com.funi.framework.mvc.eic.controller.BaseController;
import com.funi.platform.lshc.entity.sys.LinkFile;
import com.funi.platform.lshc.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 要件信息
 * @author by yadong on 2018/11/22.
 */
@Controller
@RequestMapping("/FileController")
public class FileController extends BaseController{

    @Resource
    private FileService fileService;


    /**
     * 上传要件，返回url
     * @param uploadFile 附件文件
     * @return 执行消息
     */
    @RequestMapping("/createFile")
    @ResponseBody
    public LinkFile createFile(MultipartFile uploadFile){
        LinkFile file = fileService.createFile(uploadFile);
        return file;
    }

}
