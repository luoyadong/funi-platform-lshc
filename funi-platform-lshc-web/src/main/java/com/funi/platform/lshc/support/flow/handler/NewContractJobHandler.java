package com.funi.platform.lshc.support.flow.handler;

import com.funi.platform.lshc.dto.LinkFileDto;
import com.funi.platform.lshc.enumatation.*;
import com.funi.platform.lshc.mapper.contr.ContrHouseMapper;
import com.funi.platform.lshc.mapper.contr.ContrMapper;
import com.funi.platform.lshc.mapper.house.HouseInfoMapper;
import com.funi.platform.lshc.mapper.sys.LinkFileMapper;
import com.funi.platform.lshc.service.ContractHouseRelService;
import com.funi.platform.lshc.service.ContractService;
import com.funi.platform.lshc.service.LesseeService;
import com.funi.platform.lshc.service.ResidentService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 3
 */
@Component
public class NewContractJobHandler implements JobHandler {

    @Resource
    private LesseeService lesseeService;
    @Resource
    private ContractService contractService;
    @Resource
    private ResidentService residentService;
    @Resource
    private ContractHouseRelService contractHouseRelService;
    @Resource
    private HouseInfoMapper houseInfoMapper;
    @Resource
    private ContrHouseMapper contrHouseMapper;
    @Resource
    private ContrMapper contrMapper;
    @Resource
    private LinkFileMapper linkFileMapper;

    @Override
    public void handle(String serviceNum,Conclusion conclusion) {
        boolean success=(Conclusion.TY==conclusion);
        //承租人
        String lesseeId = lesseeService.createFinal(serviceNum,1,0);
        //合同
        String contractId = contractService.createFinal(serviceNum, lesseeId,
                success?ContractStatus.VALID:ContractStatus.INVALID,1,0,serviceNum);
        //房屋合同关系
        contractHouseRelService.createFinal(serviceNum, contractId,1,0);
        //住户
        residentService.createFinal(serviceNum, contractId,1,0);
        //修改原合同状态
        contrMapper.invalid(
                contrMapper.selectContractNo(serviceNum, 0)
                , 0, ContractStatus.INVALID.name(), InvalidReason.CANCEL.getMessage());
        //attachment
        LinkFileDto linkFileDto = contrMapper.selectLinkFileDto(serviceNum,0);
        linkFileMapper.copyFile(linkFileDto.getLesseeId(),lesseeId);
        linkFileMapper.copyFile(linkFileDto.getContractId(),contractId);
        if(!success){
            //还原房屋状态
            //修改房屋租赁状态
            houseInfoMapper.modifyRentStatus(
                    contrHouseMapper.selectBoByContractId(contractId,1),
                    RentStatus.N0RMAL.getStatus());
        }
    }

    @Override
    public boolean support(BusinessType businessType) {
        return BusinessType.cnew==businessType;
    }
}
