package com.funi.platform.lshc.service.impl;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.dto.ChargeDepositDto;
import com.funi.platform.lshc.dto.ChargeDto;
import com.funi.platform.lshc.entity.charge.ArrearageHis;
import com.funi.platform.lshc.entity.charge.Bill;
import com.funi.platform.lshc.entity.charge.BillTemplet;
import com.funi.platform.lshc.entity.charge.ChargeHis;
import com.funi.platform.lshc.entity.charge.Deposit;
import com.funi.platform.lshc.entity.contr.Contr;
import com.funi.platform.lshc.mapper.charge.ArrearageHisMapper;
import com.funi.platform.lshc.mapper.charge.BillMapper;
import com.funi.platform.lshc.mapper.charge.BillTempletMapper;
import com.funi.platform.lshc.mapper.charge.ChargeHisMapper;
import com.funi.platform.lshc.mapper.charge.DepositMapper;
import com.funi.platform.lshc.mapper.contr.ContrMapper;
import com.funi.platform.lshc.query.charge.ArrearageQuery;
import com.funi.platform.lshc.query.charge.ChargeHistoryQuery;
import com.funi.platform.lshc.query.charge.ChargeQuery;
import com.funi.platform.lshc.query.charge.DepositQuery;
import com.funi.platform.lshc.service.ChargeService;
import com.funi.platform.lshc.utils.BaseEntityUtils;
import com.funi.platform.lshc.utils.DateUtils;
import com.funi.platform.lshc.utils.ExcelUtil;
import com.funi.platform.lshc.vo.ArrearageVo;
import com.funi.platform.lshc.vo.BillVo;
import com.funi.platform.lshc.vo.ChargeDetailVo;
import com.funi.platform.lshc.vo.ChargeHistoryVo;
import com.funi.platform.lshc.vo.ChargeHouseVo;
import com.funi.platform.lshc.vo.ChargeVo;
import com.funi.platform.lshc.vo.DepositVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sam on 2018/11/4.11:17 PM
 */
@Service("chargeService")
public class ChargeServiceImpl implements ChargeService {
    @Resource
    private DepositMapper depositMapper;
    @Resource
    private BillMapper billMapper;
    @Resource
    private ChargeHisMapper chargeHisMapper;
    @Resource
    private ContrMapper contrMapper;
    @Resource
    private ArrearageHisMapper arrearageHisMapper;
    @Resource
    private BillTempletMapper billTempletMapper;

    /**
     * 导出数据支持的最大数据条数
     */
    private static final int MAX_PAGE_SIZE = 1000;

    @Override
    public List<ChargeVo> findChargeList(ChargeQuery chargeQuery, CurrentUser userInfo) {
        List<ChargeVo> chargeVoList = depositMapper.selectChargeList(chargeQuery);
        // 进行数据权限处理
        if(CollectionUtils.isNotEmpty(chargeVoList)) {
            // 当前登录用户的机构
            for(ChargeVo chargeVo : chargeVoList) {
                Map<String, Boolean> authorityMap = new HashMap<>();
                authorityMap.put("AUTH_GHOUSE_CHARGE_CHARGE", false);
                // 合同截止日期
                String rentEndDate = chargeVo.getRentEndDate();
                // 租金交至日期
                String rentalPaytoDate = chargeVo.getRentalPaytoDate();
                if(canCharge(rentalPaytoDate, rentEndDate)) {
                    authorityMap.put("AUTH_GHOUSE_CHARGE_CHARGE", true);
                }
                chargeVo.setAuthorityMap(authorityMap);
            }
        }
        return chargeVoList;
    }

    /**
     * 根据租金交至日期和合同截止日期计算该合同是否可以收费
     * @param rentalPaytoDate
     * @param rentEndDate
     * @return
     */
    private boolean canCharge(String rentalPaytoDate, String rentEndDate) {
        // 如果租金交至为空，可以执行收费操作
        if(StringUtils.isBlank(rentalPaytoDate)) {
            return true;
        } else {
            if(StringUtils.isNotBlank(rentEndDate)) {
                // 合同截止日期大约上次缴费日期，可以收费
                if(DateUtils.getDifferDay(rentalPaytoDate, rentEndDate) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<ChargeHouseVo> findChargeHouseVoList(String contractId) {
        return depositMapper.selectChargeHouseVoList(contractId);
    }

    @Override
    public ChargeDetailVo findChargeDetailVo(String contractId, String chargeEndDate) {
        return getChargeDetailVo(contractId, chargeEndDate);
    }

    @Override
    public List<ChargeDetailVo> findArrearsDetailList(CurrentUser userInfo, String[] contractIds) {
        if(contractIds != null && contractIds.length > 0) {
            List<String> contractIdList = Arrays.asList(contractIds);
            List<ChargeDetailVo> chargeDetailVoList = depositMapper.selectChargeDetailVo(contractIdList);
            if(CollectionUtils.isNotEmpty(chargeDetailVoList)) {
                for(ChargeDetailVo chargeDetailVo : chargeDetailVoList) {
                    chargeDetailVo.setUnitName(userInfo.getOrganization().getMc());
                }
            }
            return chargeDetailVoList;
        }
        return null;
    }

    /**
     * 构造合同ID集合
     * @param contractId
     * @return
     */
    private ChargeDetailVo getChargeDetailVo(String contractId, String chargeEndDate) {
        List<String> contractIdList = new ArrayList<>();
        contractIdList.add(contractId);
        List<ChargeDetailVo> chargeDetailVoList = depositMapper.selectChargeDetailVo(contractIdList);
        if(CollectionUtils.isNotEmpty(chargeDetailVoList)) {
            ChargeDetailVo chargeDetailVo = chargeDetailVoList.get(0);
            // 设置缴费截止日期
            chargeDetailVo.setChargeEndDate(chargeEndDate);
            // 设置上次预存金额
            BigDecimal lastPrepaidAmount = chargeHisMapper.selectPrepaidAmount(contractId);
            chargeDetailVo.setLastPrepaidAmount(lastPrepaidAmount);
            return chargeDetailVo;
        }
        return null;
    }

    @Override
    public List<ChargeHistoryVo> findChargeHistoryVoList(ChargeHistoryQuery chargeHistoryQuery, CurrentUser userInfo) {
        List<ChargeHistoryVo> chargeHistoryVoList = chargeHisMapper.selectChargeHistoryVoList(chargeHistoryQuery);
        // 进行数据权限处理
        if(CollectionUtils.isNotEmpty(chargeHistoryVoList)) {
            // 当前登录用户的机构
            for(ChargeHistoryVo chargeHistoryVo : chargeHistoryVoList) {
                Map<String, Boolean> authorityMap = new HashMap<>();
                authorityMap.put("AUTH_GHOUSE_CHARGE_CANCEL", false);
                String backFlag = chargeHistoryVo.getBackFlag();
                if("0".equals(backFlag)) {
                    authorityMap.put("AUTH_GHOUSE_CHARGE_CANCEL", true);
                }
                chargeHistoryVo.setAuthorityMap(authorityMap);
            }
        }
        return chargeHistoryVoList;
    }

    @Override
    public List<ArrearageVo> findArrearageVoList(ArrearageQuery arrearageQuery, CurrentUser userInfo) {
        return chargeHisMapper.selectArrearageVoList(arrearageQuery);
    }

    @Override
    public List<DepositVo> findDepositVoList(DepositQuery depositQuery, CurrentUser userInfo) {
        List<DepositVo> depositVoList = depositMapper.selectDepositVoList(depositQuery);
        // 进行数据权限处理
        if(CollectionUtils.isNotEmpty(depositVoList)) {
            // 当前登录用户的机构
            for (DepositVo depositVo : depositVoList) {
                Map<String, Boolean> authorityMap = new HashMap<>();
                authorityMap.put("AUTH_GHOUSE_DEPOSIT_CHARGE", false);
                authorityMap.put("AUTH_GHOUSE_DEPOSIT_BACK", false);
                // 保证金收取状态
                String depositStatus = depositVo.getDepositStatus();
                if(StringUtils.isBlank(depositStatus)) {
                    authorityMap.put("AUTH_GHOUSE_DEPOSIT_CHARGE", true);
                } else if("1".equals(depositStatus)) {
                    authorityMap.put("AUTH_GHOUSE_DEPOSIT_BACK", true);
                }
                depositVo.setAuthorityMap(authorityMap);
            }
        }

        return depositVoList;
    }

    @Override
    public void exportDepositVoList(DepositQuery depositQuery, HttpServletResponse response) throws Exception {
        // 设置查询条数
        depositQuery.setLimit(MAX_PAGE_SIZE);
        List<DepositVo> depositVoList = depositMapper.selectDepositVoList(depositQuery);
        if(CollectionUtils.isEmpty(depositVoList)) {
            throw new RuntimeException("没有满足条件的数据");
        }
        ExcelUtil.excelExport("保证金列表.xls","保证金", depositVoList, response);
    }

    @Override
    public void exportArrearageVoList(ArrearageQuery arrearageQuery, HttpServletResponse response) throws Exception {
        // 设置查询条数
        arrearageQuery.setLimit(MAX_PAGE_SIZE);
        List<ArrearageVo> arrearageVoList = chargeHisMapper.selectArrearageVoList(arrearageQuery);
        if(CollectionUtils.isEmpty(arrearageVoList)) {
            throw new RuntimeException("没有满足条件的数据");
        }
        ExcelUtil.excelExport("欠费催缴列表.xls","欠费催缴", arrearageVoList, response);
    }

    @Override
    public void chargeDeposit(ChargeDepositDto chargeDepositDto) {
        String contractId = chargeDepositDto.getContractId();
        if(StringUtils.isEmpty(contractId)) {
            throw new RuntimeException("合同ID不能为空");
        }
        // 查询合同对象
        Contr contr = contrMapper.selectByPrimaryKey(contractId);
        if (contr == null) {
            throw new RuntimeException("合同不存在");
        }
        depositMapper.updateContractDepositStatus(contractId, "1");
        // 创建保证金对象
        Deposit deposit = new Deposit(contr, chargeDepositDto);
        new BaseEntityUtils<Deposit>().buildCreateEntity(deposit, chargeDepositDto.getUserInfo());
        // 保存保证金收费记录
        depositMapper.insert(deposit);
    }

    @Override
    public void refundDeposit(CurrentUser userInfo, String contractId, String refundReason) {
        // 查询保证金对象
        Deposit deposit = depositMapper.selectByContractId(contractId);
        if (deposit == null) {
            throw new RuntimeException("保证金收费记录不存在");
        }
        new BaseEntityUtils<Deposit>().buildModifyEntity(deposit, userInfo);
        depositMapper.updateContractDepositStatus(contractId, "2");
        deposit.setRefundTime(new Date());
        deposit.setRefundUserId(userInfo.getName());
        deposit.setRefundReason(refundReason);
        // 更新保证金对象
        depositMapper.updateByPrimaryKeySelective(deposit);
    }

    @Override
    public void cancelCharge(CurrentUser userInfo, String historyId, String backReason) {
        // 查询收费记录
        ChargeHis chargeHis = chargeHisMapper.selectByPrimaryKey(historyId);
        if (chargeHis == null) {
            throw new RuntimeException("收费记录不存在");
        }
        if("1".equals(chargeHis.getBackFlag())) {
            throw new RuntimeException("该收费记录已退费");
        }
        // 根据合同ID查询合同
        Contr contr = contrMapper.selectByPrimaryKey(chargeHis.getContrId());
        // 获得租金交至日期
        String rentalPaytoDate = contr.getRentalPaytoDate();
        // 如果本次收费记录的租金截止日期不是当前合同的收费至日期，说明该收费记录非最后一次收费记录，需要依次撤销收费
        if(DateUtils.getDifferDay(chargeHis.getRentEndDate(), rentalPaytoDate) != 0) {
           throw new RuntimeException("当前记录非最后一次收费记录，请依次撤销收费");
        }
        new BaseEntityUtils<ChargeHis>().buildCreateEntity(chargeHis, userInfo);
        String username = userInfo.getName();
        String now = DateUtils.getNow();
        chargeHis.setBackOpeUserId(username);
        chargeHis.setBackDate(now);
        chargeHis.setBackReason(backReason);
        chargeHis.setBackFlag("1");
        // 本次收费的开始日期
        String rentStartDate = chargeHis.getRentStartDate();
        // 判断收费开始日期是否小于合同开始日期，如果本次收费的开始日期大于合同开始日期，退费至日期需要减一天
        String coutractStartDate = contr.getRentStartDate();
        if(DateUtils.getDifferDay(coutractStartDate, rentStartDate) > 1) {
            rentStartDate = DateUtils.addDay(rentStartDate, -1);
        }
        chargeHis.setBackAmount(chargeHis.getActualAmount());
        chargeHis.setBackToDate(rentStartDate);
        // 更新收费记录表
        chargeHisMapper.updateByPrimaryKeySelective(chargeHis);

        // 更新票据记录表
        Bill bill = billMapper.selectByPrimaryKey(chargeHis.getBillId());
        if (bill == null) {
            throw new RuntimeException("票据记录不存在");
        }
        if("1".equals(bill.getBillStatus())) {
            throw new RuntimeException("票据状态已作废");
        }
        new BaseEntityUtils<Bill>().buildCreateEntity(bill, userInfo);
        bill.setRevokeUserId(username);
        bill.setRevokeTime(now);
        bill.setBillStatus("1");

        // 更新合同最后缴费日期
        chargeHisMapper.updateLastRentDate(chargeHis.getContrId(), rentStartDate);
    }

    @Override
    public BillVo createBill(ChargeDto chargeDto, CurrentUser userInfo) {
        // 根据合同ID，收费状态，收费截止日期，实收金额判断是否存在收费记录
        ChargeHis oldChargeHis = chargeHisMapper.selectChargeHisByContractId(chargeDto);
        if (oldChargeHis != null) {
            throw new RuntimeException("已经收费，请勿重复操作，如需打印请移步收费历史记录打印");
        }
        String contractId = chargeDto.getContractId();
        // 设置缴费月份和合同ID
        ChargeDetailVo chargeDetailVo = getChargeDetailVo(contractId, chargeDto.getChargeEndDate());
        // 设置缴费金额
        chargeDetailVo.setActualAmount(chargeDto.getActualAmount());
        // 设置上次预存金额
        BigDecimal lastPrepaidAmount = chargeHisMapper.selectPrepaidAmount(contractId);
        chargeDetailVo.setLastPrepaidAmount(lastPrepaidAmount);
        // 设置开票人
        chargeDetailVo.setBillCreateUser(userInfo.getName());
        // 构造票据
        Bill bill = new Bill(userInfo, getbillNoSeq());

        new BaseEntityUtils<Bill>().buildCreateEntity(bill, userInfo);
        BillTemplet availableBillTemplet = getAvailableBillTemplet();
        bill.setBillTempletId(availableBillTemplet.getId());
        String tmpName = availableBillTemplet.getTmpName();
        bill.setBillTempletName(tmpName);
        // 保存票据
        billMapper.insertSelective(bill);
        chargeDetailVo.setBillNo(bill.getBillNo());
        // 构造收费记录
        ChargeHis chargeHis = new ChargeHis(chargeDetailVo, bill, userInfo);
        new BaseEntityUtils<ChargeHis>().buildCreateEntity(chargeHis, userInfo);
        // 保存收费单位信息
        chargeHis.setChargeUnit(userInfo.getOrganization().getMc());
        // 保存收费记录
        chargeHisMapper.insertSelective(chargeHis);
        // 修改合同租金交至日期
        chargeHisMapper.updateLastRentDate(contractId, chargeDto.getChargeEndDate());
        return new BillVo(chargeHis.getId(), tmpName);
    }

    /**
     * 获取可用的票据打印模板，如果不存在抛出异常，如果存在多个默认取第一个
     * @return
     */
    private BillTemplet getAvailableBillTemplet() {
        // 查询模板ID
        List<BillTemplet> billTempletList = billTempletMapper.selectEnabledBillTempletList();
        if(CollectionUtils.isEmpty(billTempletList)) {
            throw new RuntimeException("没有可用的票据模板，请到系统设置中选择模板");
        }
        // 选用第一个可用的模板
        return billTempletList.get(0);
    }


    @Override
    @Scheduled(cron= "0 0 1 * * ?")
    public void syncArrearageDetail() {
        // 查询欠租列表
        List<ChargeDetailVo> chargeDetailVoList = depositMapper.selectChargeDetailVo(null);
        saveOrUpdateArrearageHistory(chargeDetailVoList);
    }

    /**
     * 根据欠费机构，保存或更新欠费记录
     * @param chargeDetailVoList
     */
    private void saveOrUpdateArrearageHistory(List<ChargeDetailVo> chargeDetailVoList) {
        // 遍历列表
        if(CollectionUtils.isNotEmpty(chargeDetailVoList)) {
            for(ChargeDetailVo chargeDetailVo : chargeDetailVoList) {
                String contractId = chargeDetailVo.getContractId();
                ArrearageHis arrearageHis = arrearageHisMapper.selectArrearageHisByContractId(contractId);
                // 查询欠租合同是否存在
                if (arrearageHis == null) {
                    arrearageHis = new ArrearageHis(chargeDetailVo);
                    arrearageHis.setSysCreateTime(new Date());
                    // 不存在保存欠租记录
                    arrearageHisMapper.insert(arrearageHis);
                } else {
                    // 存在更新欠租记录
                    arrearageHis.build(chargeDetailVo, arrearageHis);
                    arrearageHisMapper.updateByPrimaryKeySelective(arrearageHis);
                }
            }
        }
    }

    @Override
    public void generateArrearageHistory(String contractIds) {
        List<String> contractIdList = Arrays.asList(contractIds.split(","));
        List<ChargeDetailVo> chargeDetailVoList = depositMapper.selectChargeDetailVo(contractIdList);
        saveOrUpdateArrearageHistory(chargeDetailVoList);
    }

    @Override
    public String findBillTemplateName() {
        BillTemplet availableBillTemplet = getAvailableBillTemplet();
        return availableBillTemplet.getTmpName();
    }

    /**
     * 获取票据编号流水号
     * @return
     */
    private String getbillNoSeq() {
        int num = billMapper.selectNextBillSerial();
        return DateUtils.format(new Date(), "yyyyMMdd") + com.funi.platform.lshc.utils.StringUtils.formatNumber(num, 4);
    }
}
