package com.funi.platform.lshc.controller;

import com.funi.framework.biz.BizException;
import com.funi.platform.lshc.dto.ContractDto;
import com.funi.platform.lshc.query.CheckQuery;
import com.funi.platform.lshc.service.ContractService;
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
 * 年审控制层
 * @author 3
 */
@RequestMapping("/check")
@Controller
public class CheckController {

    @Resource
    private ContractService contractService;
    Logger logger = LoggerFactory.getLogger(ResidentController.class);

    /**
     * 年审列表
     * @param checkQuery
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public List<ContractDto> findList(CheckQuery checkQuery){
        return contractService.findCheckByQuery(checkQuery);
    }

    @ResponseBody
    @RequestMapping("/export")
    public void export(HttpServletResponse response,CheckQuery checkQuery){
        try {
            checkQuery.setLimit(Integer.MAX_VALUE);
            ExcelUtil.excelExport("年审.xls", "年审", contractService.findCheckByQuery(checkQuery), response);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new BizException("导出年审异常!");
        }
    }
}
