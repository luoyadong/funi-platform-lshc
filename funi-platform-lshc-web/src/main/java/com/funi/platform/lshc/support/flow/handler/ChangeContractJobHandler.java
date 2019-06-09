package com.funi.platform.lshc.support.flow.handler;

import com.funi.platform.lshc.dto.LinkFileDto;
import com.funi.platform.lshc.enumatation.*;
import com.funi.platform.lshc.mapper.contr.ContrMapper;
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
public class ChangeContractJobHandler implements JobHandler {

    @Resource
    private LesseeService lesseeService;
    @Resource
    private ContractService contractService;
    @Resource
    private ResidentService residentService;
    @Resource
    private ContractHouseRelService contractHouseRelService;
    @Resource
    private LinkFileMapper linkFileMapper;
    @Resource
    private ContrMapper contrMapper;

    @Override
    public void handle(String serviceNum, Conclusion conclusion) {
        boolean success=(Conclusion.TY==conclusion);
        if(success){
            //承租人
            String lesseeId = lesseeService.createFinal(serviceNum,1,0);
            //合同
            String contractId = contractService.createFinal(serviceNum, lesseeId,
                    success? ContractStatus.VALID:ContractStatus.INVALID,1,0,serviceNum);
            //房屋合同关系
            contractHouseRelService.createFinal(serviceNum, contractId,1,0);
            //住户
            residentService.createFinal(serviceNum, contractId,1,0);
            //attachment
            LinkFileDto linkFileDto = contrMapper.selectLinkFileDto(serviceNum,0);
            linkFileMapper.copyFile(linkFileDto.getLesseeId(),lesseeId);
            linkFileMapper.copyFile(linkFileDto.getContractId(),contractId);
            contractService.remove(serviceNum,1);
        }else {
            //修改原合同状态
            contractService.modifyOldContract(ContractStatus.VALID, serviceNum, null);
        }
    }

    @Override
    public boolean support(BusinessType businessType) {
        return BusinessType.cchange==businessType;
    }
}
