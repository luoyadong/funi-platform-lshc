package com.funi.platform.lshc.controller;

import com.funi.framework.biz.BizException;
import com.funi.platform.lshc.dto.ResidentDto;
import com.funi.platform.lshc.query.ResidentQuery;
import com.funi.platform.lshc.service.ResidentService;
import com.funi.platform.lshc.utils.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 住户控制层
 * @author 3
 */
@RequestMapping("/resident")
@Controller
public class ResidentController
{
    @Resource
    private ResidentService residentService;
    Logger logger = LoggerFactory.getLogger(ResidentController.class);

    @ResponseBody
    @RequestMapping("/list")
    public List<ResidentDto> findList(ResidentQuery residentQuery){
        return residentService.findByQuery(residentQuery);
    }

    @ResponseBody
    @RequestMapping("/export")
    public void export(HttpServletResponse response,ResidentQuery residentQuery){
        try {
            residentQuery.setLimit(Integer.MAX_VALUE);
            ExcelUtil.excelExport("住户.xls", "住户", residentService.findByQuery(residentQuery), response);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new BizException("导出住户异常!");
        }
    }
}
