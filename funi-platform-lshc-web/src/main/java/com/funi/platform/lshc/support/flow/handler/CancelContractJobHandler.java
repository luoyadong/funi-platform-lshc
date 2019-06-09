package com.funi.platform.lshc.support.flow.handler;

import com.funi.platform.lshc.enumatation.*;
import com.funi.platform.lshc.mapper.contr.ContrHouseMapper;
import com.funi.platform.lshc.mapper.contr.ContrMapper;
import com.funi.platform.lshc.mapper.house.HouseInfoMapper;
import com.funi.platform.lshc.service.ContractService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 3
 */
@Component
public class CancelContractJobHandler implements JobHandler {

    @Resource
    private ContrMapper contrMapper;
    @Resource
    private HouseInfoMapper houseInfoMapper;
    @Resource
    private ContrHouseMapper contrHouseMapper;
    @Resource
    private ContractService contractService;

    @Override
    public void handle(String serviceNum, Conclusion conclusion) {
        if(Conclusion.TY==conclusion) {
            //修改原合同状态
            //contractService.remove(serviceNum,1);
            contrMapper.modifyStatusByContractNo(
                    contrMapper.selectContractNo(serviceNum, 0)
                    , 1, ContractStatus.CANCEL.name());
            //修改房屋租赁状态
            houseInfoMapper.modifyRentStatus(
                    contrHouseMapper.selectBoByServiceNum(serviceNum, 0),
                    RentStatus.N0RMAL.getStatus());
        }else{
            contrMapper.modifyStatusByContractNo(
                    contrMapper.selectContractNo(serviceNum, 0)
                    , 1, ContractStatus.VALID.name());
        }
    }

    @Override
    public boolean support(BusinessType businessType) {
        return BusinessType.ccanel==businessType;
    }
}
