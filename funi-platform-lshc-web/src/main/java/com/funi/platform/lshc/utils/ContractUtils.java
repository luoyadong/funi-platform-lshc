package com.funi.platform.lshc.utils;

import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.dto.DocumentDto;
import com.funi.platform.lshc.entity.contr.Contr;
import com.funi.platform.lshc.entity.sys.JobAccept;
import com.funi.platform.lshc.enumatation.BusinessType;
import com.funi.platform.lshc.enumatation.ContractStatus;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 3
 */
public class ContractUtils {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    static List<ComboboxDto> contractStatusList = new ArrayList<>();
    static {
        contractStatusList.add(new ComboboxDto("全部",""));
        contractStatusList.add(new ComboboxDto(ContractStatus.VALID.getDescription(),ContractStatus.VALID.name()));
        contractStatusList.add(new ComboboxDto(ContractStatus.EXAMINING.getDescription(),ContractStatus.EXAMINING.name()));
        contractStatusList.add(new ComboboxDto(ContractStatus.INVALID.getDescription(),ContractStatus.INVALID.name()));
        contractStatusList.add(new ComboboxDto(ContractStatus.CANCEL.getDescription(),ContractStatus.CANCEL.name()));
        contractStatusList.add(new ComboboxDto(ContractStatus.OVERDUE.getDescription(),ContractStatus.OVERDUE.name()));
    }

    /**
     * 获取合同状态
     * @return
     */
    public static List<ComboboxDto> findContractStatus(){
        return  contractStatusList;
    }

    /**
     * 为合同复制档案信息
     * @param contr
     * @param document
     * @param lesseeId 承租人id
     * @return
     */
    public static Contr copyBaseInfo(Contr contr,DocumentDto document,String lesseeId){
        BeanUtils.copyProperties(document, contr);
        contr.setLesseeId(lesseeId);
        return contr;
    }

    public static JobAccept createJobAccept(String serviceNum,BusinessType businessType){
        JobAccept jobAccept = new JobAccept();
        jobAccept.setServiceNum(serviceNum);
        jobAccept.setAcceptTime(new Date());
        jobAccept.setTypeName(businessType.getWorkName());
        return jobAccept;
    }

    public static String generateCode(String pre,String seq){
        String code=pre + sdf.format(new Date());
        switch (seq.length()){
            case 1:code = code + "000" + seq ;break;
            case 2:code = code + "00" + seq;break;
            case 3:code = code +  "0" + seq;break;
            case 4:code = code + seq;break;
        }
        return code;
    }
}
