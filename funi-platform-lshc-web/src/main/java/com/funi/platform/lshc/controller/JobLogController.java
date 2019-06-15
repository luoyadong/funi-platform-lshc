package com.funi.platform.lshc.controller;

import com.funi.platform.lshc.dto.WorkLogDto;
import com.funi.platform.lshc.service.JobLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 3
 */
@RequestMapping("/job/log")
@Controller
public class JobLogController {

    @Resource
    private JobLogService jobLogService;

    @ResponseBody
    @RequestMapping("/list")
    public List<WorkLogDto> find(String serviceNum){
        return jobLogService.findByServiceNum(serviceNum);
    }

}
