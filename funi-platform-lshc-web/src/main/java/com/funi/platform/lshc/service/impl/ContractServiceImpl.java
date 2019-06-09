package com.funi.platform.lshc.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.funi.framework.biz.BizException;
import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.framework.utils.StringUtils;
import com.funi.framework.workflow.eic.service.WorkFlowService;
import com.funi.platform.lshc.dto.ContractDto;
import com.funi.platform.lshc.dto.ContractFormDto;
import com.funi.platform.lshc.dto.ContractViewDto;
import com.funi.platform.lshc.dto.DocumentDto;
import com.funi.platform.lshc.dto.HouseDto;
import com.funi.platform.lshc.dto.JobDto;
import com.funi.platform.lshc.dto.JobLogDto;
import com.funi.platform.lshc.dto.LinkFileDto;
import com.funi.platform.lshc.entity.contr.Contr;
import com.funi.platform.lshc.entity.sys.LinkFile;
import com.funi.platform.lshc.enumatation.BusinessType;
import com.funi.platform.lshc.enumatation.ContractStatus;
import com.funi.platform.lshc.enumatation.Node;
import com.funi.platform.lshc.enumatation.RentStatus;
import com.funi.platform.lshc.enumatation.UploadBusinessType;
import com.funi.platform.lshc.mapper.contr.ContrHouseMapper;
import com.funi.platform.lshc.mapper.contr.ContrMapper;
import com.funi.platform.lshc.mapper.contr.LesseeMapper;
import com.funi.platform.lshc.mapper.contr.ResidentMapper;
import com.funi.platform.lshc.mapper.house.HouseInfoMapper;
import com.funi.platform.lshc.mapper.sys.LinkFileMapper;
import com.funi.platform.lshc.query.CheckQuery;
import com.funi.platform.lshc.query.ContractQuery;
import com.funi.platform.lshc.query.HouseQuery;
import com.funi.platform.lshc.service.ContractHouseRelService;
import com.funi.platform.lshc.service.ContractService;
import com.funi.platform.lshc.service.JobAcceptService;
import com.funi.platform.lshc.service.JobLogService;
import com.funi.platform.lshc.service.LesseeService;
import com.funi.platform.lshc.service.ResidentService;
import com.funi.platform.lshc.support.GhouseConstants;
import com.funi.platform.lshc.support.UserManager;
import com.funi.platform.lshc.utils.BaseEntityUtils;
import com.funi.platform.lshc.utils.ContractUtils;
import com.funi.platform.lshc.vo.LinkFileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 3
 */
public class ContractServiceImpl implements ContractService {

    private static String GHOUSE_CONTR = "GHOUSE_CONTR";
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private ApplicationContext context;
    @Resource
    private HouseInfoMapper houseInfoMapper;
    private ContractService contractService;
    @Resource
    private ContractHouseRelService contractHouseRelService;
    @Resource
    private LesseeService lesseeService;
    @Resource
    private ResidentService residentService;
    @Resource
    private ContrMapper contrMapper;
    @Resource
    private UserManager userManager;
    @Resource
    private WorkFlowService workFlowService;
    @Resource
    private JobLogService jobLogService;
    @Resource
    private ContrHouseMapper contractHouseMapper;
    @Resource
    private LesseeMapper lesseeMapper;
    @Resource
    private ResidentMapper residentMapper;
    @Resource
    private JobAcceptService jobAcceptService;
    @Resource
    private LinkFileMapper linkFileMapper;
    private Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);
    @PostConstruct
    private void self(){
        contractService = context.getBean(ContractService.class);
    }

    @Override
    public String create(Contr contr) {
        contr.setCreateUnitId(
                userManager.findUser().getOrganization().getDm()
        );
        contrMapper.insert(contr);
        return contr.getId();
    }

    @Override
    public List<HouseDto> findHouseList(HouseQuery houseQuery) {
        return houseInfoMapper.selectByQuery(houseQuery);
    }

    @Override
    public void init(ContractFormDto contractFormDto) {
        //创建承租人
        String lesseeId = lesseeService.create(contractFormDto.getLessee());
        //创建合同
        Contr contr = ContractUtils.copyBaseInfo(contractFormDto.getContract(), contractFormDto.getDocument(), lesseeId);
        contr.setServiceNum(contractService.generateServiceNum());
        contr.setBusinessType(BusinessType.cnew);
        String contractId = contractService.create(
                contr
        );
        //创建合同与房屋关系
        contractHouseRelService.createBatch(contractFormDto.getHouseList(),contractId);
        //创建住户
        residentService.createBatch(contractFormDto.getResidentList(),contractId);
        //修改房屋租赁状态
        houseInfoMapper.modifyRentStatus(contractFormDto.getHouseList(), RentStatus.RENTING.getStatus());
        //创建受理信息
        jobLogService.createJobAccept(
                ContractUtils.createJobAccept(contr.getServiceNum(),BusinessType.cnew)
        );
        //附件
        if(CollectionUtils.isNotEmpty(contractFormDto.getLesseeAttachmentList())) {
            for(LinkFileVo linkFileVo : contractFormDto.getLesseeAttachmentList()) {
                LinkFile linkFile = new LinkFile(lesseeId, linkFileVo, UploadBusinessType.LESSEE_ATTACH);
                new BaseEntityUtils<LinkFile>().buildCreateEntity(linkFile, userManager.findUser());
                linkFileMapper.insert(linkFile);
            }
        }
        if(CollectionUtils.isNotEmpty(contractFormDto.getDocumentAttachmentList())) {
            for(LinkFileVo linkFileVo : contractFormDto.getDocumentAttachmentList()) {
                LinkFile linkFile = new LinkFile(contractId, linkFileVo, UploadBusinessType.DOCUMENT_ATTACH);
                new BaseEntityUtils<LinkFile>().buildCreateEntity(linkFile, userManager.findUser());
                linkFileMapper.insert(linkFile);
            }
        }
        //开启工作流
        workFlowService.start(BusinessType.cnew.getKey(),BusinessType.cnew.getName(),BusinessType.cnew.getVersion().toString(),
                contr.getServiceNum(),GHOUSE_CONTR,userManager.findRegionCode(),
                userManager.findUser().getId(),null);
    }

    @Override
    public void change(ContractFormDto contractFormDto) {
        Contr oldContract=contrMapper.selectByServiceNum(contractFormDto.getOldServiceNum(),1);
        if(!ContractStatus.VALID.name().equals(oldContract.getContractStatus())){
            throw new BizException("合同已失效");
        }
        //创建承租人
        String lesseeId = lesseeService.create(contractFormDto.getLessee());
        //创建合同
        Contr contr = ContractUtils.copyBaseInfo(contractFormDto.getContract(), contractFormDto.getDocument(), lesseeId);
        contr.setServiceNum(contractService.generateServiceNum());
        contr.setBusinessType(BusinessType.cchange);
        String contractId = contractService.create(
                contr
        );
        //创建合同与房屋关系
        contractHouseRelService.createBatch(contractFormDto.getHouseList(),contractId);
        //创建住户
        residentService.createBatch(contractFormDto.getResidentList(),contractId);
        //修改房屋租赁状态
       // houseInfoMapper.modifyRentStatus(contractFormDto.getHouseList(), RentStatus.RENTING.getStatus());
        //修改原合同状态
        contrMapper.modifyStatus(contractFormDto.getOldServiceNum(),1,ContractStatus.EXAMINING.name());
        //创建受理信息
        jobLogService.createJobAccept(
                ContractUtils.createJobAccept(contr.getServiceNum(),BusinessType.cchange)
        );
        //attachment
        LinkFileDto linkFileDto = contrMapper.selectLinkFileDto(contractFormDto.getOldServiceNum(),1);
        linkFileMapper.copyFile(linkFileDto.getLesseeId(),lesseeId);
        linkFileMapper.copyFile(linkFileDto.getContractId(),contractId);
        //开启工作流
        workFlowService.start(BusinessType.cchange.getKey(),BusinessType.cchange.getName(),BusinessType.cchange.getVersion().toString(),
                contr.getServiceNum(),GHOUSE_CONTR,userManager.findRegionCode(),
                userManager.findUser().getId(),null);
    }

    @Override
    public void renew(ContractFormDto contractFormDto) {
        Contr oldContract=contrMapper.selectByServiceNum(contractFormDto.getOldServiceNum(),1);
        if(!ContractStatus.VALID.name().equals(oldContract.getContractStatus())){
            throw new BizException("合同已失效!");
        }
        /*if(houseInfoMapper.isExistRent(contractFormDto.getHouseList())){
            throw new BizException("续租合同对应房屋已签约!");
        }*/
        //创建承租人
        String lesseeId = lesseeService.create(contractFormDto.getLessee());
        //创建合同
        Contr contr = ContractUtils.copyBaseInfo(contractFormDto.getContract(), contractFormDto.getDocument(), lesseeId);
        contr.setServiceNum(contractService.generateServiceNum());
        contr.setBusinessType(BusinessType.crenew);
        String contractId = contractService.create(
                contr
        );
        //创建合同与房屋关系
        contractHouseRelService.createBatch(contractFormDto.getHouseList(),contractId);
        //创建住户
        residentService.createBatch(contractFormDto.getResidentList(),contractId);
        //修改房屋租赁状态
         houseInfoMapper.modifyRentStatus(contractFormDto.getHouseList(), RentStatus.RENTING.getStatus());
        //修改原合同状态
        contrMapper.modifyStatus(contractFormDto.getOldServiceNum(),1,ContractStatus.EXAMINING.name());
        //创建受理信息
        jobLogService.createJobAccept(
                ContractUtils.createJobAccept(contr.getServiceNum(),BusinessType.crenew)
        );
        //开启工作流
        workFlowService.start(BusinessType.crenew.getKey(),BusinessType.crenew.getName(),BusinessType.crenew.getVersion().toString(),
                contr.getServiceNum(),GHOUSE_CONTR,userManager.findRegionCode(),
                userManager.findUser().getId(),null);
    }

    @Override
    public List<ContractDto> findByQuery(ContractQuery contractQuery) {
        return contrMapper.selectByQuery(contractQuery);
    }

    @Override
    public String findContractNo() {
        return ContractUtils.generateCode(GhouseConstants.BIZ_PREFIX_CONTRACT,
                contrMapper.generateContractNo()
                );
    }

    @Override
    public String generateServiceNum() {
        return ContractUtils.generateCode("",
                contrMapper.generateServiceNum()
        );
    }

    @Override
    public List<ContractDto> findDoneListByQuery(ContractQuery contractQuery) {
        contractQuery.setUserId(userManager.findUser().getUserId());
        return contrMapper.selectDoneListByQuery(contractQuery);
    }

    @Override
    public List<ContractDto> findUndoListByQuery(ContractQuery contractQuery) {
        contractQuery.setOwner(userManager.findUser().getRoles());
        return contrMapper.selectUndoListByQuery(contractQuery);
    }

    @Override
    public void commit(JobLogDto jobLogDto) {
        JobDto jobDto =jobAcceptService.findByServiceNum(jobLogDto.getJobAccept().getServiceNum());
        //修改当前状态
        jobLogDto.getJobAccept().setCurStatus(jobLogDto.getJobLog().getJobResult());
        jobLogDto.getJobAccept().setId(jobDto.getId());
        jobLogService.modifyCurStatus(jobLogDto.getJobAccept());
        //日志明细
        jobLogDto.getJobLog().setJobAcceptId(jobDto.getId());
        CurrentUser currentUser = userManager.findUser();
        jobLogDto.getJobLog().setAuditId(currentUser.getId());
        jobLogDto.getJobLog().setAuditName(currentUser.getName());
        jobLogService.createJobLog(jobLogDto.getJobLog());
        //流程
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("flag", jobLogDto.getJobResultId());
       // param.put("flag", jobLogDto.getJobLog().getJobResult());
        BusinessType businessType=BusinessType.findByWorkName(jobDto.getBusinessName());
        workFlowService.complete(jobLogDto.getJobAccept().getServiceNum(),GHOUSE_CONTR,userManager.findRegionCode(),
                userManager.findUser().getUserId(),
                StringUtils.hasText(jobLogDto.getJobLog().getNodeName())?jobLogDto.getJobLog().getNodeName(): Node.N001.getName()
                ,param,
                businessType.getKey(),jobLogDto.getJobLog().getJobResult());
    }

    @Override
    public ContractViewDto findById(String contractId, int flag) {
        ContractViewDto contractViewDto = new ContractViewDto();
        //查询合同
        Contr contract = contrMapper.selectById(contractId, flag);
        contractViewDto.setContract(contract);
        //档案
        DocumentDto documentDto = new DocumentDto();
        BeanUtils.copyProperties(contract,documentDto);
        contractViewDto.setDocument(documentDto);
        //查询房屋
        contractViewDto.setHouseDtoList(contractHouseMapper.selectByContractId(contractId, flag));
        //查询承租人
        contractViewDto.setLessee(lesseeMapper.selectByContractId(contractId, flag));
        //查询住户
        contractViewDto.setResidentList(residentMapper.selectByContractId(contractId, flag));
        return contractViewDto;
    }

    @Override
    public String createFinal(String serviceNum,String lesseeID,ContractStatus contractStatus,int isFinal,
                              int isFinalW,String newServiceNum) {
        String id = UUID.randomUUID().toString();
        contrMapper.insertFinal(serviceNum,userManager.findUser().getUserId(), id,lesseeID,contractStatus.name(),
        isFinal,isFinalW,newServiceNum);
        return id;
    }

    @Override
    public void cancel(String serviceNum) {
        isValidContract(serviceNum);
        //
        String cancelServiceNum = contractService.generateServiceNum();
        //承租人
        String lesseeId = lesseeService.createFinal(serviceNum,0,1);
        //合同
        String contractId = contractService.createFinal(serviceNum, lesseeId,
                ContractStatus.CANCEL,0,1,cancelServiceNum);
        //房屋合同关系
        contractHouseRelService.createFinal(serviceNum, contractId,0,1);
        //住户
        residentService.createFinal(serviceNum, contractId,0,1);
        //修改原合同状态
        contrMapper.modifyStatus(serviceNum,1,ContractStatus.EXAMINING.name());
        //创建受理信息
        jobLogService.createJobAccept(
                ContractUtils.createJobAccept(cancelServiceNum,BusinessType.ccanel)
        );
        //开启工作流
        workFlowService.start(BusinessType.ccanel.getKey(),BusinessType.ccanel.getName(),BusinessType.ccanel.getVersion().toString(),
                cancelServiceNum,GHOUSE_CONTR,userManager.findRegionCode(),
                userManager.findUser().getId(),null);
    }

    @Override
    public List<ContractDto> findCheckByQuery(CheckQuery checkQuery) {
        return contrMapper.selectCheckByQuery(checkQuery);
    }

    @Override
    public boolean isValidContract(String serviceNum) {
        Contr contr = contrMapper.selectByServiceNum(serviceNum,1);
        if(null==contr || (!ContractStatus.VALID.name().equals(contr.getContractStatus())
        &&!ContractStatus.OVERDUE.name().equals(contr.getContractStatus()))){
            throw new BizException("请在合同有效或已过期情况下注销合同");
        }
        try {
            if(null==contr.getRentalPaytoDate()){
                throw new BizException("请将租金交至"+contr.getRentEndDate());
            }
            Date endDate = df.parse(contr.getRentEndDate());
            Date moneyEndDate = df.parse(contr.getRentalPaytoDate());
            if(endDate.getTime()>moneyEndDate.getTime()){
                throw new BizException("请将租金交至"+contr.getRentEndDate());
            }
        }catch (ParseException e){
            logger.error(contr.getRentEndDate()+" "+contr.getRentalPaytoDate()+e.getMessage(),e);
            throw new BizException("日期格式不对!");
        }

        return true;
    }


    @Override
    public void modifyOldContract(ContractStatus contractStatus, String serviceNum,String invalidReason) {
         contrMapper.modifyOldContract(contractStatus.name(), serviceNum,invalidReason);
    }

    @Override
    public void remove(String serviceNum, int isFinal) {
        contrMapper.remove(serviceNum, isFinal);
    }

    @Override
    public void overdue() {
        contrMapper.overdue();
    }
}
