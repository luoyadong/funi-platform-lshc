package com.funi.platform.lshc.vo;

import com.funi.platform.lshc.utils.DateUtils;
import com.funi.platform.lshc.utils.NumberToCN;
import com.funi.platform.lshc.utils.NumberUtil;
import com.funi.platform.lshc.utils.RentUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 收费详情
 * Created by sam on 2018/11/12.10:08 AM
 */
public class ChargeDetailVo {
    /** 合同ID */
    private String contractId;
    /** 合同编号 */
    private String contractNo;
    /** 承租方，个人：lesseeName，集体：organizationName，票据：租户 */
    private String lesseeName;
    /** 计租面积 */
    private BigDecimal rentArea;
    /** 月租金 */
    private BigDecimal rentalMonthAmount;
    /** 租金结算方式 0月结，1年结*/
    private String rentalPaymentType;
    /** 租赁开始日期 */
    private String rentStartDate;
    /** 租赁结束日期 */
    private String rentEndDate;
    /** 租金交至日期 */
    private String rentalPaytoDate;

    /** 本次应交租开始日期 */
    private String thisRentDate;
    /** 本次至少应交租截止日期 */
    private String nextRentDate;
    /** 本次应交租的起止日期*/
    private String rentStartEndDesc;
    /** 本次应交起止日期下租金金额*/
    private BigDecimal defaultRentAmount;

    /** 累计欠租金额 */
    private BigDecimal backRentAmount;
    /** 欠租开始年月日 */
    private String backRentStartDate;
    /** 欠租终止年月日 */
    private String backRentEndDate;
    /** 累计欠租情况描述 */
    private String totalArrearsDesc;

    /** 是否收取滞纳金，0：否，1：是 */
    private String latefeePayment;
    /** 滞纳金收取设置类型，1：千 2：固定金额收取 */
    private String latefeePaymentType;
    /** 滞纳金收取设置数量1 */
    private BigDecimal latefeePaymentValue1;
    /** 滞纳金收取设置数量2 */
    private BigDecimal latefeePaymentValue2;
    /** 滞纳金金额，票据：滞纳金金额 */
    private BigDecimal lateFeeAmount;
    /** 滞纳金情况描述 */
    private String lateFeeDesc;

    /** 房屋地址集合 */
    private List<ChargeAddressVo> chargeAddressVoList;
    /** 房源位置 */
    private String houseAddress;
    /** 小区名称 */
    private String livingAreaName;
    /** 房号*/
    private String houseNo;

    /** 制表日期（票据：开票时间） */
    private String billPrintTime;
    /** 票据编号 */
    private String billNo;
    /** 开票人，票据：收款人 */
    private String billCreateUser;

    /** 应收金额 */
    private BigDecimal receivableAmount;
    /** 实收金额，票据：总缴费金额 */
    private BigDecimal actualAmount;
    /** 租金交至年月日，用户填写 */
    private String chargeEndDate;
    /** 实收金额大写 */
    private String actualAmountDesc;
    /** 上次预缴金额 */
    private BigDecimal lastPrepaidAmount;
    /** 本次预缴金额，票据：本次预交*/
    private BigDecimal prepaidAmount;
    /** 租金金额，票据：租金金额 */
    private BigDecimal rentAmount;

    /** 收费项目，租金*/
    private String rentAmountDesc = "租金";
    /** 租金金额摘要 */
    private String rentAmountSummary;
    /** 收费项目，滞纳金 */
    private String lateFeeAmountDesc = "滞纳金";
    /** 滞纳金金额摘要 */
    private String lateFeeAmountSummary;
    /** 收费项目，本次预缴*/
    private String prepaidAmountDesc = "预缴";
    /** 本次预缴金额摘要*/
    private String prepaidAmountSummary;

    /**催缴单信息*/
    /** 管理单位名称*/
    private String unitName;

    public String getThisRentDate() {
        // 获得合同开始日期
        String rentStartDate = getRentStartDate();
        // 获得租金交至日期
        String rentalPaytoDate = getRentalPaytoDate();
        if(StringUtils.isNotBlank(rentStartDate) && StringUtils.isNotBlank(rentalPaytoDate)) {
            // 如果合同开始日期与租金交至日期相同，则该合同从未缴费，本次缴费开始日期就是合同开始日期
            if(rentStartDate.equals(rentalPaytoDate)) {
                return rentStartDate;
            } else {
                // 获得合同截止日期
                String rentEndDate = getRentEndDate();
                String addDate = DateUtils.addDay(rentalPaytoDate, 1);
                if(DateUtils.getDifferDay(rentEndDate, addDate) > 0) {
                    return rentEndDate;
                } else {
                    return addDate;
                }
            }
        }
        return thisRentDate;
    }

    public void setThisRentDate(String thisRentDate) {
        this.thisRentDate = thisRentDate;
    }

    public BigDecimal getDefaultRentAmount() {
        String rentalPaytoDate = getThisRentDate();
        String chargeEndDate = getChargeEndDate();
        if(StringUtils.isBlank(chargeEndDate)) {
            chargeEndDate = getNextRentDate();
        }
        return RentUtils.calcRent(rentalPaytoDate, chargeEndDate, getRentalMonthAmount());
    }

    public void setDefaultRentAmount(BigDecimal defaultRentAmount) {
        this.defaultRentAmount = defaultRentAmount;
    }

    /**
     * 获得本次交租的租金起止日期
     * @return
     */
    public String getRentStartEndDesc() {
        String rentalPaytoDate = getThisRentDate();
        // 看前端是否传租金截止日期
        String chargeEndDate = getChargeEndDate();
        if(StringUtils.isBlank(chargeEndDate)) {
            chargeEndDate = getNextRentDate();
        }
        return "(" + rentalPaytoDate + " ~ " + chargeEndDate + ")";
    }

    public void setRentStartEndDesc(String rentStartEndDesc) {
        this.rentStartEndDesc = rentStartEndDesc;
    }

    public String getNextRentDate() {
        // 获得上次交租日期
        String rentalPaytoDate = getThisRentDate();
        // 获得交租方式，年付还是月付
        // 租金结算方式 0月结，1年结，默认为年结
        String rentalPaymentType = getRentalPaymentType();
        if(StringUtils.isBlank(rentalPaymentType)) {
            rentalPaymentType = "1";
        }
        // 合同截止日期
        String rentEndDate = getRentEndDate();
        // 计算本次至少交租日期，如果超出合同截止日期，使用合同截止日期
        if("1".equals(rentalPaymentType)) {
            String addOneYearDate = DateUtils.addYear(rentalPaytoDate, 1);
            // 如果添加一年后，日期大于租赁截止日期，欠租截止日期为租赁截止日期
            if(DateUtils.getDifferDay(rentEndDate, addOneYearDate) > 0) {
                return rentEndDate;
            } else {
                return addOneYearDate;
            }
        } else {
            // 如果是月结
            String addOneMonthDate = DateUtils.addMonth(rentalPaytoDate, 1);
            if(DateUtils.getDifferDay(rentEndDate, addOneMonthDate) > 0) {
                return rentEndDate;
            } else {
                return addOneMonthDate;
            }
        }
    }

    public void setNextRentDate(String nextRentDate) {
        this.nextRentDate = nextRentDate;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * 构造租金摘要
     * @return
     */
    public String getRentAmountSummary() {
        String rentalPaytoDate = getThisRentDate();
        String chargeEndDate = getChargeEndDate();
        if(StringUtils.isNotBlank(rentalPaytoDate) && StringUtils.isNotBlank(chargeEndDate)) {
            return getBaseSummary(rentalPaytoDate, chargeEndDate);
        }
        return rentAmountSummary;
    }

    public void setRentAmountSummary(String rentAmountSummary) {
        this.rentAmountSummary = rentAmountSummary;
    }

    /**
     * 构造滞纳金摘要
     * @return
     */
    public String getLateFeeAmountSummary() {
        String backRentStartDate = getBackRentStartDate();
        String backRentEndDate = getBackRentEndDate();
        if(StringUtils.isNotBlank(backRentStartDate) && StringUtils.isNotBlank(backRentEndDate)) {
            return getBaseSummary(backRentStartDate, backRentEndDate);
        }
        return lateFeeAmountSummary;
    }

    public void setLateFeeAmountSummary(String lateFeeAmountSummary) {
        this.lateFeeAmountSummary = lateFeeAmountSummary;
    }

    public String getPrepaidAmountSummary() {
        return prepaidAmountSummary;
    }

    public void setPrepaidAmountSummary(String prepaidAmountSummary) {
        this.prepaidAmountSummary = prepaidAmountSummary;
    }

    /**
     * 拼接摘要
     * @param startDate
     * @param endDate
     * @return
     */
    private String getBaseSummary(String startDate, String endDate) {
        return startDate + " 至 " + endDate;
    }

    public BigDecimal getRentAmount() {
        String rentalPaytoDate = getThisRentDate();
        String chargeEndDate = getChargeEndDate();
        return RentUtils.calcRent(rentalPaytoDate, chargeEndDate, getRentalMonthAmount());
    }

    public void setRentAmount(BigDecimal rentAmount) {
        this.rentAmount = rentAmount;
    }

    /**
     * 拼接收据中的小区信息
     * @return
     */
    public String getLivingAreaName() {
        List<ChargeAddressVo> chargeAddressVoList = getChargeAddressVoList();
        if (CollectionUtils.isNotEmpty(chargeAddressVoList)) {
            List<String> livingAreaNameList = new ArrayList<>();
            for (ChargeAddressVo chargeAddressVo : chargeAddressVoList) {
                livingAreaNameList.add(chargeAddressVo.getLivingAreaName());
            }
            return getDesc(livingAreaNameList);
        }
        return livingAreaName;
    }

    /**
     * 根据需要拼接的字符串集合，使用换行符分隔
     * @param descList
     * @return
     */
    private String getDesc(List<String> descList) {
        return StringUtils.join(descList, "</br>");
    }

    /**
     * 拼接数据中的房屋户号
     * @return
     */
    public String getHouseNo() {
        List<ChargeAddressVo> chargeAddressVoList = getChargeAddressVoList();
        if (CollectionUtils.isNotEmpty(chargeAddressVoList)) {
            List<String> houesNoList = new ArrayList<>();
            for (ChargeAddressVo chargeAddressVo : chargeAddressVoList) {
                houesNoList.add(chargeAddressVo.getHouseNo());
            }
            return getDesc(houesNoList);
        }
        return houseNo;
    }

    public BigDecimal getPrepaidAmount() {
        // 获取实收金额
        BigDecimal actualAmount = getActualAmount();
        // 获取应收金额
        BigDecimal receivableAmount = getReceivableAmount();
        BigDecimal subtract = actualAmount.subtract(receivableAmount);
        return subtract.compareTo(BigDecimal.ZERO) <=0 ? BigDecimal.ZERO : subtract;
    }

    /**
     * 计算应收金额
     * @return
     */
    public BigDecimal getReceivableAmount() {
        // 获得上次交租日期
        String rentalPaytoDate = getThisRentDate();
        // 租金交至月份
        String chargeEndDate = getChargeEndDate();
        if(StringUtils.isBlank(chargeEndDate)) {
            chargeEndDate = getNextRentDate();
        }
        BigDecimal receivableAmount = BigDecimal.ZERO;
        // 计算租金
        BigDecimal rentAmount = RentUtils.calcRent(rentalPaytoDate, chargeEndDate, getRentalMonthAmount());
        receivableAmount = receivableAmount.add(rentAmount);
        // 计算滞纳金
        BigDecimal lateFeeAmount = getLateFeeAmount();
        receivableAmount = receivableAmount.add(lateFeeAmount);
        // 获得历史预存金额
        BigDecimal prepaidAmount = getLastPrepaidAmount();
        if (prepaidAmount == null) {
            prepaidAmount = BigDecimal.ZERO;
        }
        receivableAmount = receivableAmount.subtract(prepaidAmount);
        return receivableAmount;
    }

    /**
     * 实收金额转大写
     * @return
     */
    public String getActualAmountDesc() {
        BigDecimal actualAmount = getActualAmount();
        return NumberToCN.number2CNMontrayUnit(actualAmount);
    }

    /**
     * 计算滞纳金
     * @return
     */
    public BigDecimal getLateFeeAmount() {
        BigDecimal lateFeeAmount = BigDecimal.ZERO;
        String latefeePayment = getLatefeePayment();
        // 滞纳金展示格式：0.00 元 （按逾期未缴金额日‰收取）
        if (StringUtils.isNotBlank(latefeePayment) && "1".equals(latefeePayment)) {
            String latefeePaymentType = getLatefeePaymentType();
            // 1：千分比收取 2：固定金额收取
            if (StringUtils.isNotBlank(latefeePaymentType)) {
                // 获取欠租天数
                BigDecimal arrearsDaysCount = getArrearsDaysCount();
                if (NumberUtil.isVaildDecimal(arrearsDaysCount)) {
                    BigDecimal latefeePaymentValue1 = getLatefeePaymentValue1();
                    BigDecimal latefeePaymentValue2 = getLatefeePaymentValue2();
                    if ("1".equals(latefeePaymentType) && NumberUtil.isVaildDecimal(latefeePaymentValue1)) {
                        BigDecimal baseLateFee = getBaseLateFee();
                        if (NumberUtil.isVaildDecimal(baseLateFee)) {
                            return NumberUtil.getAccuracyDecimal(baseLateFee.multiply(arrearsDaysCount).multiply(latefeePaymentValue1).divide(new BigDecimal(1000)));
                        }
                    } else if ("2".equals(latefeePaymentType) && NumberUtil.isVaildDecimal(latefeePaymentValue2)) {
                        return NumberUtil.getAccuracyDecimal(latefeePaymentValue2.multiply(arrearsDaysCount));
                    }
                }
            }
        }
        return lateFeeAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 获取千分比模式滞纳金的计算基数
     *
     * @return
     */
    private BigDecimal getBaseLateFee() {
        // 第一种方案直接使用月租金
        return getRentalMonthAmount();
    }

    /**
     * 是否欠租
     *
     * @return
     */
    private boolean isArrearsRent() {
        int differDay = DateUtils.getDifferDay(getRentalPaytoDate(), DateUtils.getNow());
        return differDay > 0;
    }


    /**
     * 获取欠租天数
     * 如果不欠租，返回0
     *
     * @return
     */
    private BigDecimal getArrearsDaysCount() {
        // 获取租金交至时间
        String rentalPaytoDate = getRentalPaytoDate();
        String backRentEndDate = getBackRentEndDate();
        if(StringUtils.isNotBlank(rentalPaytoDate) && StringUtils.isNotBlank(backRentEndDate)) {
            int differDay = DateUtils.getDifferDay(rentalPaytoDate, backRentEndDate);
            return new BigDecimal(differDay <= 0 ? 0 : differDay);
        }
        return BigDecimal.ZERO;
    }

    /**
     * 获得欠租截止日期
     * @return
     */
    public String getBackRentEndDate() {
        // 如果欠租
        if (isArrearsRent()) {
            // 租金结算方式 0月结，1年结，默认为年结
            String rentalPaymentType = getRentalPaymentType();
            if(StringUtils.isBlank(rentalPaymentType)) {
                rentalPaymentType = "1";
            }
            // 获取欠租开始年月日
            String backRentStartDate = getBackRentStartDate();
            // 合同截止日期
            String rentEndDate = getRentEndDate();
            // 如果是年结
            if("1".equals(rentalPaymentType)) {
                String addOneYearDate = DateUtils.addYear(backRentStartDate, 1);
                // 如果添加一年后，日期大于租赁截止日期，欠租截止日期为租赁截止日期
                if(DateUtils.getDifferDay(rentEndDate, addOneYearDate) > 0) {
                    return rentEndDate;
                } else {
                    return addOneYearDate;
                }
            } else {
                // 如果是月结
                String addOneMonthDate = DateUtils.addMonth(backRentStartDate, 1);
                if(DateUtils.getDifferDay(rentEndDate, addOneMonthDate) > 0) {
                    return rentEndDate;
                } else {
                    return addOneMonthDate;
                }
            }
        }
        return backRentEndDate;
    }

    /**
     * 获取欠租开始日期
     * @return
     */
    public String getBackRentStartDate() {
        // 如果欠租，默认欠租时间为租金交至日期
        if (isArrearsRent()) {
            return getRentalPaytoDate();
        }
        return null;
    }

    /**
     * 计算累计欠租金额
     * @return
     */
    public BigDecimal getBackRentAmount() {
        if (isArrearsRent()) {
            // 获得欠租开始日期
            String backRentStartDate = getBackRentStartDate();
            // 获得欠租结束日期
            String backRentEndDate = getBackRentEndDate();
            return RentUtils.calcRent(backRentStartDate, backRentEndDate, getRentalMonthAmount());
        }
        return BigDecimal.ZERO;
    }

    /**
     * 租金交至日期默认为租赁开始日期
     *
     * @return
     */
    public String getRentalPaytoDate() {
        if (StringUtils.isBlank(rentalPaytoDate)) {
            return getRentStartDate();
        }
        return rentalPaytoDate;
    }

    /**
     * 获取滞纳金描述
     * @return
     */
    public String getLateFeeDesc() {
        StringBuilder lateFeeBuilder = new StringBuilder();
        BigDecimal lateFeeAmount = getLateFeeAmount();
        lateFeeBuilder.append(lateFeeAmount);
        lateFeeBuilder.append(" 元 ");
        String latefeePayment = getLatefeePayment();
        // 滞纳金展示格式：0.00 元 （按逾期未缴金额日‰收取）
        if (StringUtils.isNotBlank(latefeePayment) && "1".equals(latefeePayment)) {
            String latefeePaymentType = getLatefeePaymentType();
            if (StringUtils.isNotBlank(latefeePaymentType)) {
                BigDecimal latefeePaymentValue1 = getLatefeePaymentValue1();
                BigDecimal latefeePaymentValue2 = getLatefeePaymentValue2();
                // 1：千分比收取 2：固定金额收取
                if ("1".equals(latefeePaymentType) && latefeePaymentValue1 != null) {
                    lateFeeBuilder.append("按欠费千分比收取");
                    lateFeeBuilder.append(latefeePaymentValue1);
                    lateFeeBuilder.append("‰（滞纳金/日）");
                } else if ("2".equals(latefeePaymentType) && latefeePaymentValue2 != null) {
                    lateFeeBuilder.append("按固定金额收取");
                    lateFeeBuilder.append(latefeePaymentValue2);
                    lateFeeBuilder.append("元（滞纳金/日）");
                }
            }
        }
        return lateFeeBuilder.toString();
    }

    /**
     * 获取累计欠租描述
     * @return
     */
    public String getTotalArrearsDesc() {
        // 累计欠租：	7200.00元 （2018-07-01 ~ 2018-12-31）
        StringBuilder totalArrears = new StringBuilder();
        if (isArrearsRent()) {
            totalArrears.append(getBackRentAmount());
            totalArrears.append(" 元");
            totalArrears.append("（" + getBackRentStartDate());
            totalArrears.append(" ~ " + getBackRentEndDate());
            totalArrears.append("）");
            return totalArrears.toString();
        } else {
            return "0.00 元";
        }
    }

    /**
     * 获取房屋地址描述
     * @return
     */
    public String getHouseAddress() {
        List<ChargeAddressVo> chargeAddressVoList = getChargeAddressVoList();
        if (CollectionUtils.isNotEmpty(chargeAddressVoList)) {
            List<String> houseAddressList = new ArrayList<>();
            for (ChargeAddressVo chargeAddressVo : chargeAddressVoList) {
                houseAddressList.add(getChargeAddressInfo(chargeAddressVo));
            }
            return StringUtils.join(houseAddressList, "</br>");
        }
        return houseAddress;
    }

    /**
     * 构造房屋地址
     *
     * @param chargeAddressVo
     * @return
     */
    private String getChargeAddressInfo(ChargeAddressVo chargeAddressVo) {
        if (chargeAddressVo != null) {
            StringBuilder addressBuilder = new StringBuilder();
            String regionName = chargeAddressVo.getRegionName();
            if (StringUtils.isNotBlank(regionName)) {
                addressBuilder.append(regionName);
            }
            String addressDetail = chargeAddressVo.getAddressDetail();
            if (StringUtils.isNotBlank(addressDetail)) {
                addressBuilder.append(addressDetail);
            }
            String addressBuilding = chargeAddressVo.getAddressBuilding();
            if (StringUtils.isNotBlank(addressBuilding)) {
                addressBuilder.append(addressBuilding);
                addressBuilder.append("栋");
            }
            String addressUnit = chargeAddressVo.getAddressUnit();
            if (StringUtils.isNotBlank(addressUnit)) {
                addressBuilder.append(addressUnit);
                addressBuilder.append("单元");
            }
            String addressFloor = chargeAddressVo.getAddressFloor();
            if (StringUtils.isNotBlank(addressFloor)) {
                addressBuilder.append(addressFloor);
                addressBuilder.append("层");
            }
            String addressRoomNo = chargeAddressVo.getAddressRoomNo();
            if (StringUtils.isNotBlank(addressRoomNo)) {
                addressBuilder.append(addressRoomNo);
                addressBuilder.append("号");
            }
            return addressBuilder.toString();
        }
        return "";
    }

    public String getRentalPaymentType() {
        return rentalPaymentType;
    }

    public void setRentalPaymentType(String rentalPaymentType) {
        this.rentalPaymentType = rentalPaymentType;
    }

    public String getBillPrintTime() {
        return DateUtils.getNow();
    }

    public void setBillPrintTime(String billPrintTime) {
        this.billPrintTime = billPrintTime;
    }

    public String getChargeEndDate() {
        // 缴费截止日期默认为欠费结束日期
        if(chargeEndDate == null) {
            return getBackRentEndDate();
        } else {
            // 合同截止日期
            String rentEndDate = getRentEndDate();
            if(DateUtils.getDifferDay(chargeEndDate, rentEndDate) < 0) {
                throw new RuntimeException("收费日期不能大于合同截止日期");
            }
            // 获得租金交至日期
            String rentalPaytoDate = getRentalPaytoDate();
            if(DateUtils.getDifferDay(rentalPaytoDate, chargeEndDate) <= 0) {
                throw new RuntimeException("收费日期必须大于上次缴费日期");
            }
        }
        return chargeEndDate;
    }

    public void setLivingAreaName(String livingAreaName) {
        this.livingAreaName = livingAreaName;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public void setChargeEndDate(String chargeEndDate) {
        this.chargeEndDate = chargeEndDate;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    public BigDecimal getActualAmount() {
        // 实收金额默认为0
        if(actualAmount == null) {
            return BigDecimal.ZERO;
        }
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public void setActualAmountDesc(String actualAmountDesc) {
        this.actualAmountDesc = actualAmountDesc;
    }

    public void setPrepaidAmount(BigDecimal prepaidAmount) {
        this.prepaidAmount = prepaidAmount;
    }

    public String getBillCreateUser() {
        return billCreateUser;
    }

    public void setBillCreateUser(String billCreateUser) {
        this.billCreateUser = billCreateUser;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public void setTotalArrearsDesc(String totalArrearsDesc) {
        this.totalArrearsDesc = totalArrearsDesc;
    }

    public void setLateFeeDesc(String lateFeeDesc) {
        this.lateFeeDesc = lateFeeDesc;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getLesseeName() {
        return lesseeName;
    }

    public void setLesseeName(String lesseeName) {
        this.lesseeName = lesseeName;
    }

    public BigDecimal getRentArea() {
        return rentArea;
    }

    public void setRentArea(BigDecimal rentArea) {
        this.rentArea = rentArea;
    }

    public BigDecimal getRentalMonthAmount() {
        if(rentalMonthAmount == null) {
            return BigDecimal.ZERO;
        }
        return rentalMonthAmount;
    }

    public void setRentalMonthAmount(BigDecimal rentalMonthAmount) {
        this.rentalMonthAmount = rentalMonthAmount;
    }

    public String getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(String rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public String getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(String rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public void setRentalPaytoDate(String rentalPaytoDate) {
        this.rentalPaytoDate = rentalPaytoDate;
    }

    public void setBackRentAmount(BigDecimal backRentAmount) {
        this.backRentAmount = backRentAmount;
    }

    public void setBackRentStartDate(String backRentStartDate) {
        this.backRentStartDate = backRentStartDate;
    }

    public void setBackRentEndDate(String backRentEndDate) {
        this.backRentEndDate = backRentEndDate;
    }

    public String getLatefeePayment() {
        return latefeePayment;
    }

    public void setLatefeePayment(String latefeePayment) {
        this.latefeePayment = latefeePayment;
    }

    public String getLatefeePaymentType() {
        return latefeePaymentType;
    }

    public void setLatefeePaymentType(String latefeePaymentType) {
        this.latefeePaymentType = latefeePaymentType;
    }

    public BigDecimal getLatefeePaymentValue1() {
        return latefeePaymentValue1;
    }

    public void setLatefeePaymentValue1(BigDecimal latefeePaymentValue1) {
        this.latefeePaymentValue1 = latefeePaymentValue1;
    }

    public BigDecimal getLatefeePaymentValue2() {
        return latefeePaymentValue2;
    }

    public void setLatefeePaymentValue2(BigDecimal latefeePaymentValue2) {
        this.latefeePaymentValue2 = latefeePaymentValue2;
    }

    public void setLateFeeAmount(BigDecimal lateFeeAmount) {
        this.lateFeeAmount = lateFeeAmount;
    }

    public List<ChargeAddressVo> getChargeAddressVoList() {
        return chargeAddressVoList;
    }

    public void setChargeAddressVoList(List<ChargeAddressVo> chargeAddressVoList) {
        this.chargeAddressVoList = chargeAddressVoList;
    }

    public BigDecimal getLastPrepaidAmount() {
        if(lastPrepaidAmount == null) {
            return BigDecimal.ZERO;
        }
        return lastPrepaidAmount;
    }

    public void setLastPrepaidAmount(BigDecimal lastPrepaidAmount) {
        this.lastPrepaidAmount = lastPrepaidAmount;
    }

    public String getRentAmountDesc() {
        return rentAmountDesc;
    }

    public void setRentAmountDesc(String rentAmountDesc) {
        this.rentAmountDesc = rentAmountDesc;
    }

    public String getLateFeeAmountDesc() {
        return lateFeeAmountDesc;
    }

    public void setLateFeeAmountDesc(String lateFeeAmountDesc) {
        this.lateFeeAmountDesc = lateFeeAmountDesc;
    }

    public String getPrepaidAmountDesc() {
        return prepaidAmountDesc;
    }

    public void setPrepaidAmountDesc(String prepaidAmountDesc) {
        this.prepaidAmountDesc = prepaidAmountDesc;
    }
}
