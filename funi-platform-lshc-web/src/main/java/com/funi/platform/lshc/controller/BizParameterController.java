package com.funi.platform.lshc.controller;

import com.funi.framework.mvc.eic.vo.MessageVo;
import com.funi.framework.mvc.eic.vo.ResultVo;
import com.funi.platform.lshc.dto.BizParameterDto;
import com.funi.platform.lshc.entity.sys.SysConfDeta;
import com.funi.platform.lshc.query.BizParameterQuery;
import com.funi.platform.lshc.service.BizParameterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 3
 */
@Controller
@RequestMapping("/biz/parameter")
public class BizParameterController {

    @Resource
    private BizParameterService parameterService;

    @ResponseBody
      @RequestMapping("/create")
      public MessageVo create(SysConfDeta sysConfDeta){
        parameterService.create(sysConfDeta);
        return new MessageVo("创建成功");
    }

    @ResponseBody
    @RequestMapping("/modify")
    public MessageVo modify(SysConfDeta sysConfDeta){
        parameterService.modify(sysConfDeta);
        return new MessageVo("创建成功");
    }

    @ResponseBody
    @RequestMapping("/remove")
    public MessageVo create(@RequestBody List<String> ids){
        parameterService.remove(ids);
        return new MessageVo("删除成功");
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<BizParameterDto> find(BizParameterQuery query){
        return parameterService.findByQuery(query);
    }

    @ResponseBody
    @RequestMapping("/view/{id}")
    public ResultVo find(@PathVariable("id")String id){
        return new ResultVo(parameterService.findById(id));
    }
}
