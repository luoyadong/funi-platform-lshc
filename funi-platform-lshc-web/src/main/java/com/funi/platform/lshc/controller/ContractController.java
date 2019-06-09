package com.funi.platform.lshc.controller;

import com.funi.framework.biz.BizException;
import com.funi.framework.mvc.eic.vo.MessageVo;
import com.funi.framework.mvc.eic.vo.ResultVo;
import com.funi.framework.workflow.eic.po.AuditConclusions;
import com.funi.framework.workflow.eic.service.WorkFlowService;
import com.funi.platform.lshc.dto.*;
import com.funi.platform.lshc.enumatation.BusinessType;
import com.funi.platform.lshc.enumatation.Node;
import com.funi.platform.lshc.query.ContractQuery;
import com.funi.platform.lshc.query.HouseQuery;
import com.funi.platform.lshc.service.ContractService;
import com.funi.platform.lshc.service.JobAcceptService;
import com.funi.platform.lshc.support.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 3
 */
@RequestMapping("/contract")
@Controller
public class ContractController {

    @Resource
    private ContractService contractService;
    @Resource
    private WorkFlowService workFlowService;
    @Resource
    private UserManager userManager;
    @Resource
    private JobAcceptService jobAcceptService;
    Logger logger = LoggerFactory.getLogger(ContractController.class);

    /**
     * 合同集合
     * @param contractQuery
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public List<ContractDto> findContractList(ContractQuery contractQuery){
        return contractService.findByQuery(contractQuery);
    }

    /**
     * 已办件
     * @param contractQuery
     * @return
     */
    @ResponseBody
    @RequestMapping("/done/list")
    public List<ContractDto> findDoneList(ContractQuery contractQuery){
        return contractService.findDoneListByQuery(contractQuery);
    }

    /**
     * 待办件
     * @param contractQuery
     * @return
     */
    @ResponseBody
    @RequestMapping("/undo/list")
    public List<ContractDto> findUndoList(ContractQuery contractQuery){
        return contractService.findUndoListByQuery(contractQuery);
    }

    /**
     * 房屋列表
     * @param houseQuery
     * @return
     */
    @ResponseBody
    @RequestMapping("/house/list")
    public List<HouseDto> findHouseList(HouseQuery houseQuery){
        return contractService.findHouseList(houseQuery);
    }

    /**
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/init")
    public MessageVo start(@RequestBody ContractFormDto contractFormDto){
        contractService.init(contractFormDto);
        return new MessageVo("申请成功!");
    }

    /**
     * 合同号
     * @return
     */
    @ResponseBody
     @RequestMapping("/generate/code")
     public ResultVo generateContractCode(){
        return ResultVo.newResult(contractService.findContractNo());
    }

    /**
     * 工作流提交
     * @param jobLogDto
     */
    @ResponseBody
    @RequestMapping("/commit")
    public void Commit(@RequestBody JobLogDto jobLogDto){
        contractService.commit(jobLogDto);
    }

    @ResponseBody
    @RequestMapping("/view/{contractId}/{final}")
    public ResultVo findContract(@PathVariable("contractId")String contractId,@PathVariable("final")int flag){
        return ResultVo.newResult(contractService.findById(contractId, flag));
    }

    @ResponseBody
    @RequestMapping("/conclusions")
    public List<AuditConclusions> findAuditConclusions(String serviceNum) {
        JobDto jobDto = jobAcceptService.findByServiceNum(serviceNum);
        BusinessType businessType;
        String nodeName;
        if(null!=jobDto) {
            businessType=BusinessType.findByWorkName(jobDto.getBusinessName());
            nodeName= StringUtils.hasText(jobDto.getNodeName())?jobDto.getNodeName():Node.N001.getName();
        }else{
            businessType=BusinessType.cnew;
            nodeName= Node.N001.getName();
        }
        return workFlowService.findAuditConclusions(businessType.getKey(),businessType.getName(),businessType.getVersion().toString(),nodeName,userManager.findRegionCode());
    }

    @RequestMapping("/cancel")
    @ResponseBody
    public MessageVo cancel(String serviceNum){
        try {
            contractService.cancel(serviceNum);
            return new MessageVo("申请成功!");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            if(e instanceof BizException){
                return new MessageVo(e.getMessage());
            }
            return new MessageVo("申请失败!");
        }
    }

    @RequestMapping("/change")
    @ResponseBody
    public MessageVo change(@RequestBody ContractFormDto contractFormDto){
        try {
            contractService.change(contractFormDto);
            return new MessageVo("申请成功!");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new MessageVo("申请失败!");
        }
    }

    @RequestMapping("/renew")
    @ResponseBody
    public MessageVo renew(@RequestBody ContractFormDto contractFormDto){
        try {
            contractService.renew(contractFormDto);
            return new MessageVo("申请成功!");
        }catch (Exception e){
            if(e instanceof BizException){
                throw e;
            }
            logger.error(e.getMessage(),e);
            return new MessageVo("申请失败!");
        }
    }

}
